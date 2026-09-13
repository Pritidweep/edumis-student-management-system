package com.edumis.edumis.mapper;

import com.edumis.edumis.dto.StudentDto;
import com.edumis.edumis.model.Student;

public class StudentMapper {

    // Student Entity → StudentDto
    public static StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDepartment()
        );
    }

    // StudentDto → Student Entity
    public static Student mapToStudent(StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail(),
                studentDto.getDepartment()
        );
    }
}