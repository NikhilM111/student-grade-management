package com.nikhil.studentgrade.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SubjectRequestDto {

    @NotBlank(message = "Subject name is required")
    private String subjectName;

    @NotBlank(message = "Subject code is required")
    private String subjectCode;

    @Positive(message = "Max marks is required")
    private Integer maxMarks;

    private Long createdBy;
}
