package com.security.poc.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
}
