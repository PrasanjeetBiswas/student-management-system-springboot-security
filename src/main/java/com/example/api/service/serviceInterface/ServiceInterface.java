package com.example.api.service.serviceInterface;

import java.util.List;

import com.example.api.dtos.requestDtos.ReqStudentDto;
import com.example.api.dtos.responceDtos.ResStudentDto;
import com.example.api.model.StudentModel;


public interface ServiceInterface {

    void addStudents(ReqStudentDto dto);

    List<ResStudentDto> veiwAllStudents();

    List<ResStudentDto> findStudentByName(String name);

    ResStudentDto findStudentById(Long id);

    void updateStudent(Long id,StudentModel student);

    void deleteStudent(Long id);

    List<ResStudentDto> fliterByCourse(String course);

    List<ResStudentDto> filterByBatch(String batch);
}
