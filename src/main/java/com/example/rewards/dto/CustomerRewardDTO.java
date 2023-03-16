package com.example.rewards.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerRewardDTO {
    private Long customerId;
    private Long month1Reward;
    private Long month2Reward;
    private Long month3Reward;
    private Long totalReward;
}
