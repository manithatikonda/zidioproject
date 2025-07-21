package com.zidioconnect.backend.service;

import com.zidioconnect.backend.dto.StudentDto;
import com.zidioconnect.backend.model.Student;
import com.zidioconnect.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    //save or update student profile
    public String saveOrUpdateStudent(StudentDto dto){
        Optional<Student> existing = studentRepository.findByEmail(dto.getEmail());

        Student student = existing.orElse(new Student());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        student.setUniversity(dto.getUniversity());
        student.setResumeUrl(dto.getResumeUrl());

        studentRepository.save(student);
        return existing.isPresent() ? "Student updated sucessfully" : "Student profile saved!";
    }

    //Get student profile by email
    public StudentDto getProfileByEmail(String email){
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Students not found"));

        return new StudentDto(
                student.getName(),
                student.getEmail(),
                student.getCourse(),
                student.getUniversity(),
                student.getResumeUrl()
        );

    }
}
