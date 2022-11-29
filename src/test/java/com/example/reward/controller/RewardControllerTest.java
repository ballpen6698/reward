package com.example.reward.controller;

import com.example.reward.constant.ResponseCode;
import com.example.reward.service.RewardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RewardController.class)
public class RewardControllerTest {
    @MockBean
    private RewardService rewardService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public static final String GET_CUSTOMER_POINT = "/customer/reward/view/points";
    public static final String CALCULATE_POINT = "/customer/reward/process/transaction";

    @Test
    public void getCustomerPointTest_missingCustomerId() throws Exception {
        when(rewardService.findByCustomerId(any())).thenReturn(null);
        mockMvc.perform(get(GET_CUSTOMER_POINT)).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void getCustomerPointTest() throws Exception {
        when(rewardService.findByCustomerId(any())).thenReturn(null);
        String url = GET_CUSTOMER_POINT + "?customerId=121";
        mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void processCustomerPointTest() throws Exception {
        when(rewardService.processPoint(any())).thenReturn(ResponseCode.Ok);
        mockMvc.perform(post(CALCULATE_POINT).contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void processCustomerPointTest_missingBody() throws Exception {
        when(rewardService.processPoint(any())).thenReturn(ResponseCode.Ok);
        mockMvc.perform(post(CALCULATE_POINT)).andDo(print()).andExpect(status().isBadRequest());
    }
}
