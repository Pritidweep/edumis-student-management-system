package com.edumis.edumis.repository;

import com.edumis.edumis.model.Attendance;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

            boolean existsByStudentIdAndCourseIdAndAttendanceDate(
                Long studentId,
                Long courseId,
                LocalDate attendanceDate
            );

            boolean existsByStudentIdAndCourseIdAndAttendanceDateAndIdNot(
                Long studentId,
                Long courseId,
                LocalDate attendanceDate,
                Long id);

            Page<Attendance> findByStudentId(
                Long studentId,
                Pageable pageable
            );

            Page<Attendance> findByCourseId(
                Long courseId,
                Pageable pageable
            );

            Page<Attendance> findByAttendanceDate(
                LocalDate attendanceDate,
                Pageable pageable
            );

            Page<Attendance> findByStatusContainingIgnoreCase(
                String status,
                Pageable pageable
            );

            


}