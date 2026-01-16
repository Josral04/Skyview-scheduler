package com.skyview.scheduler.dto;

public class RegisterResponse {
    public Long staffId;
    public String username;

    public RegisterResponse(Long staffId, String username) {
        this.staffId = staffId;
        this.username = username;
    }
}
