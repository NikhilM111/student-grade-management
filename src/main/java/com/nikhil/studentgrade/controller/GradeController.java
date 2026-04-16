package com.nikhil.studentgrade.controller;

import com.nikhil.studentgrade.Dto.GradeRequestDto;
import com.nikhil.studentgrade.Dto.GradeResponseDto;
import com.nikhil.studentgrade.service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grade")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("save")
    public ResponseEntity<GradeResponseDto> createGrade(@Valid @RequestBody GradeRequestDto payLoad) {
        return ResponseEntity.ok().body(gradeService.createGrade(payLoad));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<GradeResponseDto>> getAllGrade() {
        return ResponseEntity.ok(gradeService.getAllGrade());
    }

    @GetMapping("get-by-id/{GradeId}")
    public ResponseEntity<GradeResponseDto> getGradeById(@PathVariable Long GradeId) {
        return ResponseEntity.ok(gradeService.getByGradeId(GradeId));
    }

    @PatchMapping("update-by-id/{GradeId}")
    public ResponseEntity<GradeResponseDto> patchGradeUpdate(@PathVariable Long GradeId, @Valid @RequestBody GradeRequestDto payLoad) {
        return ResponseEntity.ok(gradeService.patchGradeById(GradeId, payLoad));
    }
}
