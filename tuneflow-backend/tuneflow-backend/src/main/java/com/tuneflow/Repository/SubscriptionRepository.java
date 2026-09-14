package com.tuneflow.Repository;

import com.tuneflow.Entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}