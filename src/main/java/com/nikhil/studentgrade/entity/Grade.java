package com.nikhil.studentgrade.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "grades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private Double marksObtained;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    private String remarks;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private int createdBy;

    private LocalDateTime updatedOn;
    private int updatedBy;

    private void calculateGrade() {
        if (marksObtained == null || subject == null) return;
        double percentage = (marksObtained / subject.getMaxMarks()) * 100;
        if (percentage >= 90)      { grade = "A+"; remarks = "Outstanding"; }
        else if (percentage >= 80) { grade = "A";  remarks = "Excellent"; }
        else if (percentage >= 70) { grade = "B";  remarks = "Good"; }
        else if (percentage >= 60) { grade = "C";  remarks = "Average"; }
        else if (percentage >= 50) { grade = "D";  remarks = "Below Average"; }
        else                       { grade = "F";  remarks = "Fail"; }
    }
}
