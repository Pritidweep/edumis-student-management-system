package com.edumis.edumis.mapper;

import com.edumis.edumis.dto.AttendanceDto;
import com.edumis.edumis.model.Attendance;
import com.edumis.edumis.model.Course;
import com.edumis.edumis.model.Student;

public class AttendanceMapper {

    // ENTITY -> DTO
    public static AttendanceDto mapToAttendanceDto(Attendance attendance) {

        return new AttendanceDto(
                attendance.getId(),
                attendance.getStudent().getId(),
                attendance.getCourse().getId(),
                attendance.getAttendanceDate(),
                attendance.getStatus()
        );
    }

    // DTO -> ENTITY
    public static Attendance mapToAttendance(
            AttendanceDto attendanceDto,
            Student student,
            Course course) {

        return new Attendance(
                attendanceDto.getId(),
                student,
                course,
                attendanceDto.getAttendanceDate(),
                attendanceDto.getStatus()
        );
    }
}