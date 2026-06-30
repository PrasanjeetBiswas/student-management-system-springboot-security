package com.example.api.service.serviceInterface;

import java.util.List;

import com.example.api.dtos.ResponseDtos.ResStudentDto;
import com.example.api.dtos.requestDtos.ReqStudentDto;
import com.example.api.dtos.updateDtos.StudentUpdateDto;


public interface ServiceInterface {

    void addStudents(ReqStudentDto dto);

    List<ResStudentDto> viewAllStudents();

    List<ResStudentDto> findStudentByName(String name);

    ResStudentDto findStudentById(Long id);

    void updateStudent(Long id,StudentUpdateDto student);

    void deleteStudent(Long id);

    List<ResStudentDto> filterByCourse(String course);

    List<ResStudentDto> filterByBatch(String batch);
}
