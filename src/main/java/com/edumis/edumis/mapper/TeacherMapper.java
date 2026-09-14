package com.edumis.edumis.mapper;

import com.edumis.edumis.dto.TeacherDto;
import com.edumis.edumis.model.Teacher;

public class TeacherMapper {

    public static TeacherDto mapToTeacherDto(Teacher teacher) {
        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getDepartment(),
                teacher.getPhone()
        );
    }

    public static Teacher mapToTeacher(TeacherDto teacherDto) {
        return new Teacher(
                teacherDto.getId(),
                teacherDto.getFirstName(),
                teacherDto.getLastName(),
                teacherDto.getEmail(),
                teacherDto.getDepartment(),
                teacherDto.getPhone()
        );
    }
}