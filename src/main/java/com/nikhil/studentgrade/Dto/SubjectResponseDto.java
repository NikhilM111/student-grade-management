package com.nikhil.studentgrade.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponseDto {
    private Long subjectId;

    @NotBlank(message = "Subject name is required")
    private String subjectName;

    @NotBlank(message = "Subject code is required")
    private String subjectCode;

    @Positive(message = "Max marks is required")
    private Integer maxMarks;

    private Long createdBy;
    private LocalDateTime createdAt;
}
