package com.example.rewards.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class CustomerRewardAppError {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
