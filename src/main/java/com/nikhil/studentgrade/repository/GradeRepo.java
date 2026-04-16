package com.nikhil.studentgrade.repository;

import com.nikhil.studentgrade.entity.Grade;
import com.nikhil.studentgrade.entity.Student;
import com.nikhil.studentgrade.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepo extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(Student studentName);
    List<Grade> findBySubject(Subject studentName);
    boolean existsBySubjectAndGrade(String subjectName, String grade);
    boolean existsByStudentAndSubject(String subjectName, String grade);
    List<Grade> findByStudent_StudentId(Long studentId);
    List<Grade> findBySubject_SubjectId(Long subjectId);
}
