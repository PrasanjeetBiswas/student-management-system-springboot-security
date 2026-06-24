package com.example.api.service.serviceLogic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api.dtos.requestDtos.ReqStudentDto;
import com.example.api.dtos.responceDtos.ResStudentDto;
import com.example.api.exceptions.DuplicateEmailException;
import com.example.api.exceptions.DuplicatePhoneException;
import com.example.api.exceptions.StudentNotFoundException;
import com.example.api.model.StudentModel;
import com.example.api.repository.StudentRepository;
import com.example.api.service.serviceInterface.ServiceInterface;

@Service
public class StudentServiceImpl implements ServiceInterface {

        public final StudentRepository repo;

        StudentServiceImpl(StudentRepository repo) {
                this.repo = repo;
        }

        @Override
        public void addStudents(ReqStudentDto dto) {

                if (repo.existsByEmail(dto.getEmail())) {
                        throw new DuplicateEmailException("Email already exists");
                }

                if (repo.existsByNum(dto.getNum())) {
                        throw new DuplicatePhoneException(
                                        "Phone number already exists");
                }

                StudentModel st = StudentModel.builder()
                                .name(dto.getName())
                                .email(dto.getEmail())
                                .password(dto.getPassword())
                                .num(dto.getNum())
                                .status(dto.getStatus())
                                .course(dto.getCourse())
                                .batch(dto.getBatch())
                                .build();

                repo.save(st);
        }

        @Override
        public List<ResStudentDto> veiwAllStudents() {
                List<StudentModel> students = repo.findAll();

                return students.stream()
                                .map(student -> {
                                        ResStudentDto dto = new ResStudentDto();
                                        dto.setName(student.getName());
                                        dto.setCourse(student.getCourse());
                                        dto.setBatch(student.getBatch());
                                        dto.setStatus(student.getStatus());
                                        dto.setCreateDate(student.getCreateDate());

                                        return dto;
                                })
                                .toList();
        }

        @Override
        public List<ResStudentDto> findStudentByName(String name) {
                return repo.findByName(name)
                                .stream()
                                .map(student -> ResStudentDto.builder()
                                                .name(student.getName())
                                                .status(student.getStatus())
                                                .course(student.getCourse())
                                                .batch(student.getBatch())
                                                .createDate(student.getCreateDate())
                                                .build())
                                .toList();
        }

        @Override
        public ResStudentDto findStudentById(Long id) {

                return repo.findById(id)
                                .map(student -> ResStudentDto.builder()
                                                .name(student.getName())
                                                .status(student.getStatus())
                                                .course(student.getCourse())
                                                .batch(student.getBatch())
                                                .createDate(student.getCreateDate())
                                                .build())
                                .orElseThrow(() -> new StudentNotFoundException("Student Not Found With Id :- " + id));
        }

        @Override
        public void updateStudent(Long id, StudentModel student) {
                StudentModel St = repo.findById(id).orElse(null);
                if (student != null) {
                        if (student.getName() != null) {
                                St.setName(student.getName());
                        }
                        if (student.getEmail() != null) {
                                boolean emailExists = repo.existsByEmail(student.getEmail());
                                if (emailExists && !student.getEmail().equals(St.getEmail())) {
                                        throw new DuplicateEmailException("Email already exists");
                                }
                                St.setEmail(student.getEmail());
                        }
                        if (student.getPassword() != null) {
                                St.setPassword(student.getPassword());
                        }
                        if (student.getNum() != null) {

                                boolean phoneExists = repo.existsByNum(student.getNum());

                                if (phoneExists &&
                                                !student.getNum().equals(St.getNum())) {

                                        throw new DuplicatePhoneException(
                                                        "Phone number already exists");
                                }

                                St.setNum(student.getNum());
                        }
                        if (student.getBatch() != null) {
                                St.setBatch(student.getBatch());
                        }
                        if (student.getCourse() != null) {
                                St.setCourse(student.getCourse());
                        }
                        if (student.getCreateDate() != null) {
                                St.setCreateDate(student.getCreateDate());
                        }
                        if (student.getStatus() != null) {
                                St.setStatus(student.getStatus());
                        }
                }

                repo.save(St);

        }

        @Override
        public void deleteStudent(Long id) {
                repo.deleteById(id);
        }

        @Override
        public List<ResStudentDto> fliterByCourse(String course) {
                return repo.findByCourse(course)
                                .stream()
                                .map(student -> ResStudentDto.builder()
                                                .name(student.getName())
                                                .status(student.getStatus())
                                                .course(student.getCourse())
                                                .batch(student.getBatch())
                                                .createDate(student.getCreateDate())
                                                .build())
                                .toList();

        }

        @Override
        public List<ResStudentDto> filterByBatch(String batch) {
                return repo.findByBatch(batch)
                                .stream()
                                .map(student -> ResStudentDto.builder()
                                                .name(student.getName())
                                                .batch(student.getBatch())
                                                .course(student.getCourse())
                                                .createDate(student.getCreateDate())
                                                .status(student.getStatus())
                                                .build())
                                .toList();
        }

}
