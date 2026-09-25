package com.edumis.edumis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edumis.edumis.model.Enrollment;

@Repository 
public interface EnrollmentRepository extends JpaRepository <Enrollment, Long>{

    boolean existsByStudentIdAndCourseId(
        Long studentId,
        Long courseId
    );

    boolean existsByStudentIdAndCourseIdAndIdNot(
        Long studentId,
        Long courseId,
        Long id
    );

    Page<Enrollment> findByStudentId(
        Long studentId,
        Pageable pageable
    );

    Page<Enrollment> findByCourseId(
        Long courseId,
        Pageable pageable
    );

    Page<Enrollment> findByAcademicYearContainingIgnoreCase(
        String academicYear,
        Pageable pageable
    );

    Page<Enrollment> findByStatusContainingIgnoreCase(
        String status,
        Pageable pageable
    );

    Page<Enrollment> findBySemester(
        Integer semester,
        Pageable pageable
    );

    

} 
