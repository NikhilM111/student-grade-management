package com.nikhil.studentgrade.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeResponseDto {

    private Long id;
    private String studentName;
    private String subjectName;
    private String subjectCode;
    private Double marksObtained;
    private Integer maxMarks;
    private String grade;
    private String remarks;
    private LocalDateTime createdAt;
}