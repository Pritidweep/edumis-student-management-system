package com.edumis.edumis.repository;

import com.edumis.edumis.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    boolean existsByCodeIgnoreCaseAndCourseIdAndSemester(
            String code,
            Long courseId,
            Integer semester);

    boolean existsByCodeIgnoreCaseAndCourseIdAndSemesterAndIdNot(
            String code,
            Long courseId,
            Integer semester,
            Long id);

    Page<Subject> findByCourseId(
            Long courseId,
            Pageable pageable);

    Page<Subject> findBySemester(
            Integer semester,
            Pageable pageable);

    Page<Subject> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable);

    Page<Subject> findByCodeContainingIgnoreCase(
            String code,
            Pageable pageable);

    Page<Subject> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(
            String name,
            String code,
            Pageable pageable);
}