package com.nikhil.studentgrade.controller;

import com.nikhil.studentgrade.Dto.StudentRequestDTO;
import com.nikhil.studentgrade.Dto.StudentResponseDTO;
import com.nikhil.studentgrade.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("save")
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO payLoad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(payLoad));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping("get-by-id/{StudentId}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long StudentId) {
        return ResponseEntity.ok(studentService.getStudentById(StudentId));
    }

    @PatchMapping("update-by-id/{StudentId}")
    public ResponseEntity<StudentResponseDTO> patchStudentById(@PathVariable Long StudentId,
                                                           @Valid @RequestBody StudentRequestDTO payLoad) {
        return ResponseEntity.ok(studentService.patchStudentById(StudentId, payLoad));
    }
}
