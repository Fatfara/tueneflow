package com.tuneflow.dto;

import com.tuneflow.Entity.SubscriptionPlan;
import com.tuneflow.Entity.SubscriptionStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionResponse {

    private Long id;

    private Long userId;

    private SubscriptionPlan plan;

    private SubscriptionStatus status;

    private LocalDate startDate;

    private LocalDate endDate;
}
