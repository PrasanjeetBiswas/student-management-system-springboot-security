package com.example.api.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder




@Entity
@Table(name = "students")
public class StudentModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "student_id")
private Long id;   

@NotBlank(message = "Name cannot be empty")
@Size(min = 3,max = 50)
@Column(name="student_name")
private String name;

@Email(message = "Invalid email format")
@NotBlank
@Column(unique = true, nullable = false)
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
@Column(name = "phone_no",unique = true)
private String num;

@NotBlank
@Column(name = "course_name")
private String course;

@NotBlank
@Column(name = "batch_name")
private String batch;

@CreationTimestamp
@Column(name = "admission_date")
private LocalDate date;

@Enumerated(EnumType.STRING)
private StudentStatus status;

}
