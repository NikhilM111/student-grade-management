package com.nikhil.studentgrade.service;

import com.nikhil.studentgrade.Dto.GradeRequestDto;
import com.nikhil.studentgrade.Dto.GradeResponseDto;
import com.nikhil.studentgrade.entity.Grade;
import com.nikhil.studentgrade.entity.Student;
import com.nikhil.studentgrade.entity.Subject;
import com.nikhil.studentgrade.repository.GradeRepo;
import com.nikhil.studentgrade.repository.StudentRepo;
import com.nikhil.studentgrade.repository.SubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepo gradeRepo;
    private final StudentRepo studentRepo;
    private final SubjectRepo subjectRepo;

    public GradeResponseDto createGrade(GradeRequestDto payLoad) {

        Student stududentDetails = studentRepo.findById(payLoad.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found at: " +payLoad.getStudentId()));
        Subject subjectDetails = subjectRepo.findById(payLoad.getSubjectId()).orElseThrow(() -> new RuntimeException("Subject not found at: " +payLoad.getSubjectId()));

        if (gradeRepo.existsByStudentAndSubject(stududentDetails.getName(), subjectDetails.getSubjectName()))  throw new RuntimeException("Grade already exists for this student and subject");

        Grade newRegistry = new Grade();

        newRegistry.setStudent(stududentDetails);
        newRegistry.setSubject(subjectDetails);
        newRegistry.setMarksObtained(payLoad.getMarksObtained());

        Grade res = gradeRepo.save(newRegistry);

        return mappedResponse(res);
    }

    public List<GradeResponseDto> getAllGrade() {
        return gradeRepo.findAll().stream().map(this::mappedResponse).toList();
    }

    public GradeResponseDto getByGradeId(Long GradeId) {
        Grade res = gradeRepo.findById(GradeId).orElseThrow(() -> new RuntimeException("Grade not found at: " +GradeId));

        return mappedResponse(res);
    }

    public GradeResponseDto patchGradeById(Long gradeId, GradeRequestDto payload) {
        Grade existingGrade = gradeRepo.findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Grade not found: " + gradeId));

        if (payload.getMarksObtained() != null) {
            existingGrade.setMarksObtained(payload.getMarksObtained());

            double percentage = (payload.getMarksObtained() /
                    existingGrade.getSubject().getMaxMarks()) * 100;

            if (percentage >= 90)      { existingGrade.setGrade("A+"); existingGrade.setRemarks("Outstanding"); }
            else if (percentage >= 80) { existingGrade.setGrade("A");  existingGrade.setRemarks("Excellent"); }
            else if (percentage >= 70) { existingGrade.setGrade("B");  existingGrade.setRemarks("Good"); }
            else if (percentage >= 60) { existingGrade.setGrade("C");  existingGrade.setRemarks("Average"); }
            else if (percentage >= 50) { existingGrade.setGrade("D");  existingGrade.setRemarks("Below Average"); }
            else                       { existingGrade.setGrade("F");  existingGrade.setRemarks("Fail"); }
        }
        Grade updated = gradeRepo.save(existingGrade);
        return mappedResponse(updated);
    }

//    Helper
    private GradeResponseDto mappedResponse(Grade payLoad) {
        return new GradeResponseDto(
                payLoad.getGradeId(),
                payLoad.getStudent().getName(),
                payLoad.getSubject().getSubjectName(),
                payLoad.getSubject().getSubjectCode(),
                payLoad.getMarksObtained(),
                payLoad.getSubject().getMaxMarks(),
                payLoad.getGrade(),
                payLoad.getRemarks(),
                payLoad.getCreatedOn()
                );
    }
}
