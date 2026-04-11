package com.nikhil.studentgrade.controller;

import com.nikhil.studentgrade.Dto.SubjectRequestDto;
import com.nikhil.studentgrade.Dto.SubjectResponseDto;
import com.nikhil.studentgrade.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("save")
    public ResponseEntity<SubjectResponseDto> createSubject(@Valid @RequestBody SubjectRequestDto payLoad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(payLoad));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<SubjectResponseDto>> getAllSubject() {
        return ResponseEntity.ok(subjectService.getAllStudent());
    }

    @GetMapping("get-by-id/{SubjectId}")
    public ResponseEntity<SubjectResponseDto> getAllSubject(@PathVariable Long SubjectId) {
        return ResponseEntity.ok(subjectService.getSubjectById(SubjectId));
    }

    @PatchMapping("update-by-id/{SubjectId}")
    public ResponseEntity<SubjectResponseDto> patchSubject(@PathVariable Long SubjectId, @RequestBody SubjectRequestDto payLoad) {
        return ResponseEntity.ok(subjectService.patchSubjectById(SubjectId, payLoad));
    }
}
