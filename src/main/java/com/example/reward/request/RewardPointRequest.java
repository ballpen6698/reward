package com.example.reward.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class RewardPointRequest {
    List<Transaction> transactions;

    @Data
    @Builder
    public static class Transaction {
        String customerId;
        String monthOfYear;
        List<Long> transaction;
    }
}
