package com.example.api.dtos.requestDtos;

import java.time.LocalDate;

import com.example.api.model.StudentStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Name cannot be empty")
@Size(min = 3,max = 50)
    private String name;

    @Email(message = "Invalid email format")
@NotBlank
    private String email;

    @NotBlank(message = "Password is required")
@Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
@Pattern(
    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
    message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
)
    private String password;

    @NotBlank
@Pattern(regexp = "^[0-9]{10}$",message = "Phone number must be 10 digits")
    private String num;

    @NotBlank
    private String course;

    @NotBlank
    private String batch;


    private LocalDate createDate;
    private StudentStatus status;
}
