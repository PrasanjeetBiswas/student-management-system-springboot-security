package com.example.api.dtos.responceDtos;

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

public class ResStudentDto {
    private String name;
    private String course;
    private String batch;
    private LocalDate date;
    private StudentStatus status;
}
