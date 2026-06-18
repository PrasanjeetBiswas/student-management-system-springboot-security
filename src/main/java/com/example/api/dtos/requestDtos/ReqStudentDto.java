package com.example.api.dtos.requestDtos;

import java.time.LocalDate;

import com.example.api.model.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReqStudentDto {
    private String name;
    private String email;
    private String password;
    private String num;
    private String course;
    private String batch;
    private LocalDate date;
    private StudentStatus status;
}
