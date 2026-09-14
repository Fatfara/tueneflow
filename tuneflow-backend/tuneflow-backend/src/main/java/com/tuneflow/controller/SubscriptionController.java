package com.tuneflow.controller;

import com.tuneflow.dto.SubscriptionResponse;
import com.tuneflow.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/free")
    public ResponseEntity<SubscriptionResponse> createFreeSubscription(
            Authentication authentication) {

        SubscriptionResponse response =
                subscriptionService.createFreeSubscription(
                        authentication.getName()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<SubscriptionResponse> getMySubscription(
            Authentication authentication) {

        SubscriptionResponse response =
                subscriptionService.getMySubscription(
                        authentication.getName()
                );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/premium")
    public ResponseEntity<SubscriptionResponse> upgradeToPremium(
            @RequestParam(defaultValue = "1") int months,
            Authentication authentication) {

        SubscriptionResponse response =
                subscriptionService.upgradeToPremium(
                        authentication.getName(),
                        months
                );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancel")
    public ResponseEntity<SubscriptionResponse> cancelSubscription(
            Authentication authentication) {

        SubscriptionResponse response =
                subscriptionService.cancelSubscription(
                        authentication.getName()
                );

        return ResponseEntity.ok(response);
    }
}
