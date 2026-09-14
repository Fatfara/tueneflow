package com.tuneflow.service;

import com.tuneflow.Entity.Subscription;
import com.tuneflow.Entity.SubscriptionPlan;
import com.tuneflow.Entity.SubscriptionStatus;
import com.tuneflow.Entity.User;
import com.tuneflow.Repository.SubscriptionRepository;
import com.tuneflow.Repository.UserRepository;
import com.tuneflow.dto.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Transactional
    public SubscriptionResponse createFreeSubscription(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (subscriptionRepository.existsByUserId(user.getId())) {
            throw new RuntimeException(
                    "User already has a subscription"
            );
        }

        Subscription subscription = Subscription.builder()
                .user(user)
                .plan(SubscriptionPlan.FREE)
                .status(SubscriptionStatus.ACTIVE)
                .startDate(LocalDate.now())
                .endDate(null)
                .build();

        return mapToResponse(
                subscriptionRepository.save(subscription)
        );
    }

    @Transactional(readOnly = true)
    public SubscriptionResponse getMySubscription(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Subscription subscription =
                subscriptionRepository.findByUserId(user.getId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Subscription not found"
                                ));

        return mapToResponse(subscription);
    }

    @Transactional
    public SubscriptionResponse upgradeToPremium(
            String email,
            int months) {

        if (months <= 0) {
            throw new RuntimeException(
                    "Months must be greater than zero"
            );
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Subscription subscription =
                subscriptionRepository.findByUserId(user.getId())
                        .orElseGet(() ->
                                Subscription.builder()
                                        .user(user)
                                        .build()
                        );

        LocalDate today = LocalDate.now();

        LocalDate startDate = today;

        if (subscription.getEndDate() != null
                && subscription.getEndDate().isAfter(today)
                && subscription.getPlan()
                == SubscriptionPlan.PREMIUM) {

            startDate = subscription.getEndDate();
        }

        subscription.setPlan(SubscriptionPlan.PREMIUM);
        subscription.setStatus(SubscriptionStatus.ACTIVE);

        if (subscription.getStartDate() == null) {
            subscription.setStartDate(today);
        }

        LocalDate currentEndDate =
                subscription.getEndDate();

        LocalDate baseDate =
                currentEndDate != null
                        && currentEndDate.isAfter(today)
                        ? currentEndDate
                        : today;

        subscription.setEndDate(
                baseDate.plusMonths(months)
        );

        return mapToResponse(
                subscriptionRepository.save(subscription)
        );
    }

    @Transactional
    public SubscriptionResponse cancelSubscription(
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Subscription subscription =
                subscriptionRepository.findByUserId(user.getId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Subscription not found"
                                ));

        subscription.setStatus(
                SubscriptionStatus.CANCELLED
        );

        return mapToResponse(
                subscriptionRepository.save(subscription)
        );
    }

    private SubscriptionResponse mapToResponse(
            Subscription subscription) {

        return SubscriptionResponse.builder()
                .id(subscription.getId())
                .userId(subscription.getUser().getId())
                .plan(subscription.getPlan())
                .status(subscription.getStatus())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .build();
    }
}
