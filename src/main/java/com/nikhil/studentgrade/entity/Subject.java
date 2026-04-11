package com.nikhil.studentgrade.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Subject name is required")
    private String subjectName;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Subject code is required")
    private String subjectCode;

    @Column(nullable = false)
    @Positive(message = "Max marks is required")
    private Integer maxMarks;

    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;

}
