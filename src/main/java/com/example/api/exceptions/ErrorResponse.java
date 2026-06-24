package com.example.api.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ErrorResponse {
    private LocalDateTime timeStamp;
    private int status;
    private String message;
}
