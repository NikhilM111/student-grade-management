package com.nikhil.studentgrade.service;


import com.nikhil.studentgrade.Dto.SubjectRequestDto;
import com.nikhil.studentgrade.Dto.SubjectResponseDto;
import com.nikhil.studentgrade.entity.Subject;
import com.nikhil.studentgrade.repository.SubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepo subjectRepo;

    public SubjectResponseDto createSubject(SubjectRequestDto payLoad) {
        if(subjectRepo.existsBySubjectName(payLoad.getSubjectName()))
            throw new RuntimeException("Subject already exist: " +payLoad.getSubjectName());

        if(subjectRepo.existsBySubjectCode(payLoad.getSubjectCode()))
            throw new RuntimeException("Subject code already exist: " +payLoad.getSubjectCode());

        if(payLoad.getMaxMarks() == null || payLoad.getMaxMarks() <= 0)
            throw new RuntimeException("Invalid Max marks: " +payLoad.getMaxMarks());

        Subject newSubject = new Subject();

        newSubject.setSubjectName(payLoad.getSubjectName());
        newSubject.setSubjectCode(payLoad.getSubjectCode());
        newSubject.setMaxMarks(payLoad.getMaxMarks());
        newSubject.setCreatedBy(payLoad.getCreatedBy());
        newSubject.setCreatedAt(LocalDateTime.now());

        Subject save = subjectRepo.save(newSubject);

        return mappedResponse(save);
    }

    public List<SubjectResponseDto> getAllStudent() {
        return subjectRepo.findAll()
                .stream()
                .map(this::mappedResponse)
                .collect(Collectors.toList());
    }

    public SubjectResponseDto getSubjectById(Long SubjectId) {
        Subject subject = subjectRepo.findById(SubjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found: " +SubjectId));

        return mappedResponse(subject);
    }

    public SubjectResponseDto patchSubjectById(Long SubjectId, SubjectRequestDto payLoad) {
        Subject existingRecord = subjectRepo.findById(SubjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found at: " +SubjectId));

        if(payLoad.getSubjectName() != null)    existingRecord.setSubjectName(payLoad.getSubjectName());
        if(payLoad.getSubjectCode() != null)    existingRecord.setSubjectCode(payLoad.getSubjectCode());
        if(payLoad.getMaxMarks() != null)   existingRecord.setMaxMarks(payLoad.getMaxMarks());

        Subject patchRecord = subjectRepo.save(existingRecord);

        return mappedResponse(patchRecord);
    }

//    Helper
    private SubjectResponseDto mappedResponse(Subject subject) {
        return new SubjectResponseDto(subject.getSubjectId(),
                subject.getSubjectName(),
                subject.getSubjectCode(),
                subject.getMaxMarks(),
                subject.getCreatedBy(),
                subject.getCreatedAt() );
    }
}
