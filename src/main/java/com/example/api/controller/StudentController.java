package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.api.dtos.requestDtos.ReqStudentDto;
import com.example.api.dtos.responceDtos.ResStudentDto;
import com.example.api.model.StudentModel;
import com.example.api.repository.Repository;
import com.example.api.service.serviceInterface.ServiceInerface;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class StudentController {

    private final ServiceInerface ser;
    // private final Repository repo;

    StudentController(ServiceInerface ser){
        this.ser = ser;
    }
    
    @PostMapping("/student/addStudent")
    public String postMethodName(@Valid @RequestBody ReqStudentDto dto) {
        //TODO: process POST request

        ser.addStudents(dto);
        
        return "New Student Added";
    }

    @GetMapping("/student/veiwAllStudents")
    public List<ResStudentDto> getMethodName() {
        return ser.veiwAllStudents();
    }

    @GetMapping("/student/name/{name}")
public List<ResStudentDto> getStudentByName(@PathVariable String name) {
    return ser.findStudentByName(name);
}

    @GetMapping("/student/id/{id}")
public ResStudentDto getStudentById(@PathVariable Long id) {
    return ser.findStudentById(id);
}

@PutMapping("/student/updateStudent/{id}")
public String putMethodName(@PathVariable Long id, @RequestBody StudentModel student) {
    //TODO: process PUT request

    ser.updateStudent(id,student);
    
    return "Student Has Been Updated";
}
    
@DeleteMapping("student/delate/{id}")
public String deleteMethod(@PathVariable Long id){
    ser.deleteStudent(id);
    return "Student Has Been Deleted";
}

@GetMapping("student/courseFilter/{course}")
public List<ResStudentDto> getMethodName(@PathVariable String course) {
    return ser.fliterByCourse(course);
}

    @GetMapping("/student/batchfilter/{batch}")
    public List<ResStudentDto> getMethodBatch(@PathVariable String batch) {
        return ser.filterByBatch(batch);
    }
    
    
}
