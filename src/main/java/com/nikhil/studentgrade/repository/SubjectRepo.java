package com.nikhil.studentgrade.repository;

import com.nikhil.studentgrade.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepo extends JpaRepository<Subject, Long> {

    Optional<Subject> findBySubjectName(String subjectName);
    Optional<Subject> findBySubjectCode(String subjectCode);

    boolean existsBySubjectName(String subjectName);
    boolean existsBySubjectCode(String subjectCode);
}
