package com.nikhil.studentgrade.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class StudentRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Subject name is required")
    private String subjectName;

    @NotBlank(message = "Subject code is required")
    private String subjectCode;

    @Email(message = "Invalid E-Mail")
    @NotBlank(message = "E-Mail is required")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "Phone is required")
    private String phone;

    private Long createdBy;
    private Date createdAt;
}
