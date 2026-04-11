package com.nikhil.studentgrade.service;

import com.nikhil.studentgrade.Dto.StudentRequestDTO;
import com.nikhil.studentgrade.Dto.StudentResponseDTO;
import com.nikhil.studentgrade.entity.Student;
import com.nikhil.studentgrade.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    public StudentResponseDTO createStudent(StudentRequestDTO payLoad) {
        if(studentRepo.existsByEmail(payLoad.getEmail())) {
            throw new RuntimeException("E-mail already present: " +payLoad.getEmail());
        }

        if(studentRepo.existsByPhone(payLoad.getPhone())) {
            throw new RuntimeException("Phone already present: " +payLoad.getPhone());
        }

        Student createRecord = new Student();
        createRecord.setName(payLoad.getName());
        createRecord.setPhone(payLoad.getPhone());
        createRecord.setEmail(payLoad.getEmail());
        createRecord.setCreatedBy(payLoad.getCreatedBy());
        createRecord.setCreatedOn(new Date());

        Student saved = studentRepo.save(createRecord);

        return mapToResponse(saved);
    }

    public List<StudentResponseDTO> getAllStudent() {

        return studentRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(Long StudentId) {
        Student student = studentRepo.findById(StudentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " +StudentId));
        return mapToResponse(student);
    }

    public StudentResponseDTO patchStudentById(Long StudentId, StudentRequestDTO payLoad) {

        Student existingStudent = studentRepo.findById(StudentId)
                .orElseThrow(() -> new RuntimeException("Student not found at: " +StudentId));

        if (payLoad.getName() != null) {
            existingStudent.setName(payLoad.getName());
        }
        if (payLoad.getEmail() != null) {
            existingStudent.setEmail(payLoad.getEmail());
        }
        if (payLoad.getPhone() != null) {
            existingStudent.setPhone(payLoad.getPhone());
        }
        return mapToResponse(studentRepo.save(existingStudent));
    }

//    Helper
private StudentResponseDTO mapToResponse(Student student) {
    return new StudentResponseDTO(
            student.getStudentId(),
            student.getName(),
            student.getEmail(),
            student.getPhone(),
            student.getCreatedBy(),
            student.getCreatedOn()
    );
}
}
