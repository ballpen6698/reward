package com.example.reward.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@Entity
public class CustomerReward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String customerId;

    private long points;

    private String monthOfYear;
}
