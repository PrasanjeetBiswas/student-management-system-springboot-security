package com.example.api.dtos.apiResponseDtos;

public class ApiResponse {
        private String message;

    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
