package com.edumis.edumis.mapper;

import com.edumis.edumis.dto.EnrollmentDto;
import com.edumis.edumis.model.Course;
import com.edumis.edumis.model.Enrollment;
import com.edumis.edumis.model.Student;

public class EnrollmentMapper {

    public static EnrollmentDto mapToEnrollmentDto(Enrollment enrollment) {

        return new EnrollmentDto(
                enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId(),
                enrollment.getEnrollmentDate(),
                enrollment.getAcademicYear(),
                enrollment.getSemester(),
                enrollment.getStatus()
        );
    }

    public static Enrollment mapToEnrollment(
            EnrollmentDto enrollmentDto,
            Student student,
            Course course) {

        return new Enrollment(
                enrollmentDto.getId(),
                student,
                course,
                enrollmentDto.getEnrollmentDate(),
                enrollmentDto.getAcademicYear(),
                enrollmentDto.getSemester(),
                enrollmentDto.getStatus()
        );
    }
}