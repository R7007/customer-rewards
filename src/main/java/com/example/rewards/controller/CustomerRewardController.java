package com.example.rewards.controller;

import com.example.rewards.dto.CustomerRewardDTO;
import com.example.rewards.service.RewardCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rewards")
public class CustomerRewardController {
    @Autowired
    private RewardCalculatorService rewardCalculatorService;

    /**
     * Computes the Rewards for a given customer
     * @param customerId Customer Id
     * @return Reward Info for last three months and total rewards
     */

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerRewardDTO> getRewardsForCustomerId(@PathVariable Long customerId) {
        CustomerRewardDTO rewards = rewardCalculatorService.getRewardsForCustomerId(customerId);
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }
}
