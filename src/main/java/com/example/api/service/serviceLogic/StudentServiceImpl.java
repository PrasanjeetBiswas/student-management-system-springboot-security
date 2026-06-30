package com.example.api.service.serviceLogic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api.dtos.ResponseDtos.ResStudentDto;
import com.example.api.dtos.requestDtos.ReqStudentDto;
import com.example.api.dtos.updateDtos.StudentUpdateDto;
import com.example.api.exceptions.DuplicateEmailException;
import com.example.api.exceptions.DuplicatePhoneException;
import com.example.api.exceptions.StudentNotFoundException;
import com.example.api.model.StudentModel;
import com.example.api.repository.StudentRepository;
import com.example.api.service.serviceInterface.ServiceInterface;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements ServiceInterface {

        public final StudentRepository repo;

        // StudentServiceImpl(StudentRepository repo) {
        // this.repo = repo;
        // }

        @Override
        public void addStudents(ReqStudentDto dto) {

                log.info("Creating student. Name: {}, Email: {}", dto.getName(), dto.getEmail());

                if (repo.existsByEmail(dto.getEmail())) {
                        log.warn("Student creation failed. Email already exists: {}", dto.getEmail());
                        throw new DuplicateEmailException("Email already exists");
                }

                if (repo.existsByNum(dto.getNum())) {
                        log.warn("Student creation failed. Phone number already exists: {}", dto.getNum());
                        throw new DuplicatePhoneException("Phone number already exists");
                }

                StudentModel student = StudentModel.builder()
                                .name(dto.getName())
                                .email(dto.getEmail())
                                .password(dto.getPassword())
                                .num(dto.getNum())
                                .status(dto.getStatus())
                                .course(dto.getCourse())
                                .batch(dto.getBatch())
                                .build();

                try {

                        if (student == null) {
                                log.error("Student database is null");
                                throw new StudentNotFoundException("Student database is null");
                        }
                        repo.save(student);

                        log.info(
                                        "Student created successfully. ID: {}, Name: {}, Email: {}",
                                        student.getId(),
                                        student.getName(),
                                        student.getEmail());

                } catch (Exception ex) {
                        log.error("Failed to create student. Email: {}", dto.getEmail(), ex);
                        throw ex;
                }
        }

        @Override
        public List<ResStudentDto> viewAllStudents() {

                log.info("Fetching all students from the database.");

                List<StudentModel> students = repo.findAll();

                log.info("Retrieved {} student(s) from the database.", students.size());

                List<ResStudentDto> response = students.stream()
                                .map(student -> {
                                        log.debug("Mapping student with ID: {}", student.getId());

                                        ResStudentDto dto = new ResStudentDto();
                                        dto.setName(student.getName());
                                        dto.setCourse(student.getCourse());
                                        dto.setBatch(student.getBatch());
                                        dto.setStatus(student.getStatus());
                                        dto.setCreateDate(student.getCreateDate());

                                        return dto;
                                })
                                .toList();

                log.info("Student retrieval completed successfully. Total records returned: {}.", response.size());

                return response;
        }

        @Override
        public List<ResStudentDto> findStudentByName(String name) {

                log.info("Searching for student(s) with name: {}", name);

                List<StudentModel> students = repo.findByName(name);

                if (students.isEmpty()) {
                        log.warn("No students found with name: {}", name);
                        throw new StudentNotFoundException("No students found with name" + name);
                }

                log.info("Found {} student(s) with name: {}", students.size(), name);

                List<ResStudentDto> response = students.stream()
                                .map(student -> ResStudentDto.builder()
                                                .name(student.getName())
                                                .status(student.getStatus())
                                                .course(student.getCourse())
                                                .batch(student.getBatch())
                                                .createDate(student.getCreateDate())
                                                .build())
                                .toList();

                log.info("Successfully mapped {} student(s) to response DTOs.", response.size());

                return response;
        }

        @Override
        public ResStudentDto findStudentById(Long id) {

                log.info("Fetching student with id: {}", id);

                if (id == null) {
                        log.error("Student id cannot be null");
                        throw new IllegalArgumentException("Student id cannot be null");
                }

                ResStudentDto student = repo.findById(id)
                                .map(entity -> ResStudentDto.builder()
                                                .name(entity.getName())
                                                .status(entity.getStatus())
                                                .course(entity.getCourse())
                                                .batch(entity.getBatch())
                                                .createDate(entity.getCreateDate())
                                                .build())
                                .orElseThrow(() -> {
                                        log.error("Student not found with id: {}", id);
                                        return new StudentNotFoundException("Student not found with id: " + id);
                                });

                log.info("Student fetched successfully with id: {}", id);

                return student;
        }

        @Override
        public void updateStudent(Long id, StudentUpdateDto studentDto) {

                log.info("Starting update process for student id: {}", id);

                if (id == null) {
                        log.error("Student id is null");
                        throw new IllegalArgumentException("Id cannot be null");
                }

                if (studentDto == null) {
                        log.error("Student data is null");
                        throw new IllegalArgumentException("Student data cannot be null");
                }

                StudentModel student = repo.findById(id)
                                .orElseThrow(() -> {
                                        log.error("Student not found with id: {}", id);
                                        return new StudentNotFoundException("Student not found with id: " + id);
                                });

                if (student == null) {
                        log.error("Student database is null");
                        throw new StudentNotFoundException("Student database is null");
                }

                if (studentDto.getName() != null) {
                        student.setName(studentDto.getName());
                }

                if (studentDto.getEmail() != null) {

                        if (repo.existsByEmailAndIdNot(studentDto.getEmail(), id)) {

                                log.error("Email already exists: {}", studentDto.getEmail());
                                throw new DuplicateEmailException("Email already exists");
                        }

                        student.setEmail(studentDto.getEmail());
                }

                if (studentDto.getPassword() != null) {
                        student.setPassword(studentDto.getPassword());
                }

                if (studentDto.getNum() != null) {

                        if (repo.existsByNumAndIdNot(studentDto.getNum(), id)) {

                                log.error("Phone number already exists: {}", studentDto.getNum());
                                throw new DuplicatePhoneException("Phone number already exists");
                        }

                        student.setNum(studentDto.getNum());
                }

                if (studentDto.getBatch() != null) {
                        student.setBatch(studentDto.getBatch());
                }

                if (studentDto.getCourse() != null) {
                        student.setCourse(studentDto.getCourse());
                }

                if (studentDto.getStatus() != null) {
                        student.setStatus(studentDto.getStatus());
                }

                repo.save(student);

                log.info("Student updated successfully with id: {}", id);
        }

        @Override
        public void deleteStudent(Long id) {

                log.info("Deleting student with ID: {}", id);

                if (id == null) {
                        log.warn("Student deletion failed. ID cannot be null.");
                        throw new IllegalArgumentException("ID cannot be null.");
                }

                if (!repo.existsById(id)) {
                        log.warn("Student deletion failed. No student found with ID: {}", id);
                        throw new StudentNotFoundException("Student not found with ID: " + id);
                }

                try {
                        repo.deleteById(id);
                        log.info("Student deleted successfully. ID: {}", id);
                } catch (Exception ex) {
                        log.error("Failed to delete student. ID: {}", id, ex);
                        throw ex;
                }
        }

        @Override
        public List<ResStudentDto> filterByCourse(String course) {

                log.info("Fetching students enrolled in course: {}", course);

                List<StudentModel> students = repo.findByCourse(course);

                if (students.isEmpty()) {
                        log.warn("No students found for course: {}", course);
                        throw new StudentNotFoundException("No students found for course: " + course);
                }

                log.info("Found {} student(s) enrolled in course: {}", students.size(), course);

                List<ResStudentDto> response = students.stream()
                                .map(student -> ResStudentDto.builder()
                                                .name(student.getName())
                                                .status(student.getStatus())
                                                .course(student.getCourse())
                                                .batch(student.getBatch())
                                                .createDate(student.getCreateDate())
                                                .build())
                                .toList();

                log.info("Successfully mapped {} student(s) to response DTOs for course: {}",
                                response.size(), course);

                return response;
        }

        @Override
        public List<ResStudentDto> filterByBatch(String batch) {

                log.info("Fetching students for batch: {}", batch);

                List<StudentModel> students = repo.findByBatch(batch);

                if (students.isEmpty()) {
                        log.warn("No students found for batch: {}", batch);
                        throw new StudentNotFoundException("No students found for batch: " + batch);
                }

                log.info("Found {} student(s) for batch: {}", students.size(), batch);

                List<ResStudentDto> response = students.stream()
                                .map(student -> ResStudentDto.builder()
                                                .name(student.getName())
                                                .batch(student.getBatch())
                                                .course(student.getCourse())
                                                .createDate(student.getCreateDate())
                                                .status(student.getStatus())
                                                .build())
                                .toList();

                log.info("Successfully mapped {} student(s) to response DTOs for batch: {}",
                                response.size(), batch);

                return response;
        }

}
