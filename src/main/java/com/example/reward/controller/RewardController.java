package com.example.reward.controller;

import com.example.reward.constant.ResponseCode;
import com.example.reward.model.CustomerReward;
import com.example.reward.request.RewardPointRequest;
import com.example.reward.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/customer/reward")
@Slf4j
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping("/view/points")
    public ResponseEntity<List<CustomerReward>> getCustomerPoints(@RequestParam String customerId) {
        log.info("View reward points for {}", customerId);
        return new ResponseEntity<>(rewardService.findByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping("/process/transaction")
    public ResponseEntity<String> calculatePoints(@RequestBody RewardPointRequest rewardPointRequest) {
        log.info("process reward points");
        ResponseCode responseCode = rewardService.processPoint(rewardPointRequest);
        return new ResponseEntity<>(responseCode.message, responseCode.code);
    }
}
