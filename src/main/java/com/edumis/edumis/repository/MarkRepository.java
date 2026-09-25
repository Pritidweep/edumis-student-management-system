package com.edumis.edumis.repository;

import com.edumis.edumis.model.Mark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    boolean existsByStudentIdAndCourseIdAndExamTypeIgnoreCaseAndAcademicYearAndSemester(
            Long studentId,
            Long courseId,
            String examType,
            String academicYear,
            Integer semester);

    boolean existsByStudentIdAndCourseIdAndExamTypeIgnoreCaseAndAcademicYearAndSemesterAndIdNot(
            Long studentId,
            Long courseId,
            String examType,
            String academicYear,
            Integer semester,
            Long id);

    Page<Mark> findByStudentId(
            Long studentId,
            Pageable pageable);

    Page<Mark> findByCourseId(
            Long courseId,
            Pageable pageable);

    Page<Mark> findByExamTypeContainingIgnoreCase(
            String examType,
            Pageable pageable);

    Page<Mark> findByAcademicYearContainingIgnoreCase(
            String academicYear,
            Pageable pageable);

    Page<Mark> findBySemester(
            Integer semester,
            Pageable pageable);

}