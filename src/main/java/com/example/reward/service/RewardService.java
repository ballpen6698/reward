package com.example.reward.service;

import com.example.reward.constant.ResponseCode;
import com.example.reward.model.CustomerReward;
import com.example.reward.repository.RewardRepository;
import com.example.reward.request.RewardPointRequest;
import com.example.reward.util.RewardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.reward.constant.RewardConstant.REWARD_MULTIPLIER_OVER_100;
import static com.example.reward.constant.RewardConstant.REWARD_MULTIPLIER_OVER_50;

@Service
@Slf4j
public class RewardService {
    @Autowired
    private RewardRepository rewardRepository;

    public ResponseCode processPoint(RewardPointRequest rewardPointRequest) {
        // Before each process previous record will be cleaned up
        // @Todo implement upsert to update point for existing records
        rewardRepository.deleteAll();

        if (ObjectUtils.isEmpty(rewardPointRequest) || CollectionUtils.isEmpty(rewardPointRequest.getTransactions())) {
            log.warn("Incoming request is empty");
            return ResponseCode.INVALID_REQUEST;
        }
        List<CustomerReward> customerRewards = new ArrayList<>();
        Map<String, List<Long>> customerTransMapPerMonth = new ConcurrentReferenceHashMap<>();
        // Merge customer with same month into single list
        rewardPointRequest.getTransactions().forEach(transaction -> {
            String key = transaction.getCustomerId() + ":" + transaction.getMonthOfYear();
                    if (customerTransMapPerMonth.get(key) == null) {
                        customerTransMapPerMonth.put(key, transaction.getTransaction());
                    } else {
                        customerTransMapPerMonth.get(key).addAll(transaction.getTransaction());
                    }
                }
        );

        // start calculate reward point each moth of the customer
        customerTransMapPerMonth.keySet().forEach(key -> {
            String customerId = key.split(":")[0];
            long totalPoints = RewardUtil.calculatePoint(customerTransMapPerMonth.get(key));
            CustomerReward customerReward = CustomerReward.builder().customerId(customerId).points(totalPoints).monthOfYear(key).build();
            customerRewards.add(customerReward);
        });

        rewardRepository.saveAll(customerRewards);
        log.info("Reward points are process for {} customers", customerTransMapPerMonth.keySet().size());
        return ResponseCode.Ok;
    }

    public List<CustomerReward> findByCustomerId(String customerId) {
        log.info("View reward points for {} ", customerId);
        return rewardRepository.findByCustomerId(customerId);
    }


}
