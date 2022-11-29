package com.example.reward.util;

import java.util.List;

import static com.example.reward.constant.RewardConstant.REWARD_MULTIPLIER_OVER_100;
import static com.example.reward.constant.RewardConstant.REWARD_MULTIPLIER_OVER_50;

public class RewardUtil {
    public static long calculatePoint(List<Long> points) {
        long totalPoint = 0;
        for (Long point : points) {
            if (point > 50) {
                long remaining = point - 50;
                if (remaining > 50) {
                    totalPoint += (remaining - 50) * REWARD_MULTIPLIER_OVER_100 + 50 * REWARD_MULTIPLIER_OVER_50;
                } else {
                    totalPoint += remaining * REWARD_MULTIPLIER_OVER_50;
                }

            }
        }
        ;
        return totalPoint;
    }
}
