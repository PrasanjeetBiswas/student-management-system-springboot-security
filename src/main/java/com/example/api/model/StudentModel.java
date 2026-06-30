package com.example.api.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.UpdateTimestamp;

// import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@NotNull
public class StudentModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "student_id")
private Long id;   


@Column(name="student_name")
private String name;


@Column(unique = true, nullable = false)
private String email;


private String password;

@Column(name = "phone_no",unique = true)
private String num;


@Column(name = "course_name")
private String course;


@Column(name = "batch_name")
private String batch;

@CreationTimestamp
@Column(name = "admission_date")
private LocalDate createDate;

// @UpdateTimestamp
// @Column(name = "update_date")
// private LocalDate updateDate;

@Enumerated(EnumType.STRING)
private StudentStatus status;



}
