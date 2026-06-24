package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.api.dtos.requestDtos.ReqStudentDto;
import com.example.api.dtos.responceDtos.ResStudentDto;
import com.example.api.model.StudentModel;
import com.example.api.service.serviceInterface.ServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@Tag(name="Student Apis")
@SecurityRequirement(name = "bearerAuth")
@RestController
public class StudentController {

    private final ServiceInterface ser;
    // private final Repository repo;

    StudentController(ServiceInterface ser) {
        this.ser = ser;
    }

    @Operation(summary = "Add a new Student")
    @PostMapping("/v1/addStudent")
    public String postMethodName(@Valid @RequestBody ReqStudentDto dto) {

        ser.addStudents(dto);

        return "New Student Added";
    }

    @Operation(summary = "View all students")
    @GetMapping("/v1/viewAllStudents")
    public List<ResStudentDto> getMethodName() {
        return ser.veiwAllStudents();
    }

    @Operation(summary = "Find student by name")
    @GetMapping("/v1/name/{name}")
    public List<ResStudentDto> getStudentByName(@PathVariable String name) {
        return ser.findStudentByName(name);
    }

    @Operation(summary = "Find student by Id")
    @GetMapping("/v1/id/{id}")
    public ResStudentDto getStudentById(@PathVariable Long id) {
        return ser.findStudentById(id);
    }

    @Operation(summary = "Update student with there student id")
    @PutMapping("/v1/updateStudent/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody StudentModel student) {

        ser.updateStudent(id, student);

        return "Student Has Been Updated";
    }

    @Operation(summary = "Hard Delete student with there student id")
    @DeleteMapping("v1/delete/{id}")
    public String deleteMethod(@PathVariable Long id) {
        ser.deleteStudent(id);
        return "Student Has Been Deleted";
    }

    @Operation(summary = "Find students according to course")
    @GetMapping("v1/filterByCourse/{course}")
    public List<ResStudentDto> getMethodName(@PathVariable String course) {
        return ser.fliterByCourse(course);
    }

    @Operation(summary="Find students according to batch")
    @GetMapping("/v1/filterByBatch/{batch}")
    public List<ResStudentDto> getMethodBatch(@PathVariable String batch) {
        return ser.filterByBatch(batch);
    }

}
