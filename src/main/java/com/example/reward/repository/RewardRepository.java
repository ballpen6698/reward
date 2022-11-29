package com.example.reward.repository;

import com.example.reward.model.CustomerReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<CustomerReward, Integer> {
    public List<CustomerReward> findByCustomerId(String customerId);

    public List<CustomerReward> findByCustomerIdAndMonthOfYear(String customerId, String monthOfYear);
}
