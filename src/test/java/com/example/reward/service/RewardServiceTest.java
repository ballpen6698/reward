package com.example.reward.service;

import com.example.reward.constant.ResponseCode;
import com.example.reward.repository.RewardRepository;
import com.example.reward.request.RewardPointRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RewardServiceTest {

    @Mock
    private RewardRepository rewardRepository;

    @InjectMocks
    private RewardService rewardService;

    @Test
    void testProcessPoint_emptyRequest() {
        RewardPointRequest rewardPointRequest = null;
        ResponseCode responseCode = rewardService.processPoint(rewardPointRequest);
        assertEquals(responseCode.code, HttpStatus.BAD_REQUEST);

        rewardPointRequest = RewardPointRequest.builder().build();
        responseCode = rewardService.processPoint(rewardPointRequest);
        assertEquals(responseCode.code, HttpStatus.BAD_REQUEST);
    }

    @Test
    void testProcessPoint() {
        when(rewardRepository.saveAll(any())).thenReturn(null);
        RewardPointRequest.Transaction transaction = RewardPointRequest.Transaction.builder().customerId("cid").transaction(Arrays.asList(1l)).build();
        RewardPointRequest rewardPointRequest = RewardPointRequest.builder().transactions(Arrays.asList(transaction)).build();
        ResponseCode responseCode = rewardService.processPoint(rewardPointRequest);
        assertEquals(responseCode.code, HttpStatus.OK);
    }
}
