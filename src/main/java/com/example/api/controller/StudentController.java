package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.api.dtos.ResponseDtos.ResStudentDto;
import com.example.api.dtos.requestDtos.ReqStudentDto;
import com.example.api.dtos.updateDtos.StudentUpdateDto;
import com.example.api.service.serviceInterface.ServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@Tag(name="Student Apis")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {


    private final ServiceInterface studentService;
    // private final Repository repo;

    // StudentController(studentServiceviceInterface studentService) {
    //     this.studentService = studentService;
    // }

    @Operation(summary = "Add a new Student")
    @PostMapping
    public ResponseEntity<String> addStudent(@Valid @RequestBody ReqStudentDto dto) {

        studentService.addStudents(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("New Student Added");
    }

    @Operation(summary = "View all students")
    @GetMapping
    public List<ResStudentDto> getAllStudents() {
        return studentService.viewAllStudents();
    }

    @Operation(summary = "Find student by name")
    @GetMapping("/name/{name}")
    public List<ResStudentDto> findStudentWithName(@PathVariable String name) {
        return studentService.findStudentByName(name);
    }

    @Operation(summary = "Find student by Id")
    @GetMapping("/{id}")
    public ResStudentDto findStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }

    @Operation(summary = "Update student by ID")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id,@Valid @RequestBody StudentUpdateDto student) {
       
        studentService.updateStudent(id, student);
        
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Student By ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully. ID: "+id);
    }

    @Operation(summary = "Find students according to course")
    @GetMapping("/course/{course}")
    public List<ResStudentDto> filterByCourse(@PathVariable String course) {
        return studentService.filterByCourse(course);
    }

    @Operation(summary="Find students according to batch")
    @GetMapping("/batch/{batch}")
    public List<ResStudentDto> filterByBatch(@PathVariable String batch) {
        return studentService.filterByBatch(batch);
    }

}
