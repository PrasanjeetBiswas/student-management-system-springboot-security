package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.StudentModel;

public interface Repository extends JpaRepository<StudentModel,Long>{
List<StudentModel> findByName(String name);
List<StudentModel> findByCourse(String course);

List<StudentModel> findByBatch(String batch);
}
