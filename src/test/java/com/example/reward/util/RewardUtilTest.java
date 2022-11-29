package com.example.reward.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RewardUtilTest {

    @Test
    public void calculatePointTest() {
        List<Long> transaction = Arrays.asList(0l, 10l, 30l, 40l, 50l, 51l, 70l, 99l, 110l);
        assertEquals(RewardUtil.calculatePoint(transaction), 140l);
        transaction = Arrays.asList(0l, -1l);
        assertEquals(RewardUtil.calculatePoint(transaction), 0l);
    }
}
