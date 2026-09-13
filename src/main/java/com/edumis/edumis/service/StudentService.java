package com.edumis.edumis.service;

import com.edumis.edumis.dto.StudentDto;
import com.edumis.edumis.exception.DuplicateEmailException;
import com.edumis.edumis.exception.StudentNotFoundException;
import com.edumis.edumis.mapper.StudentMapper;
import com.edumis.edumis.model.Student;
import com.edumis.edumis.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE
    public StudentDto createStudent(StudentDto studentDto) {

    studentRepository.findByEmail(studentDto.getEmail())
            .ifPresent(student -> {
                throw new DuplicateEmailException(studentDto.getEmail());
            });

    Student student = StudentMapper.mapToStudent(studentDto);

    Student savedStudent = studentRepository.save(student);

    return StudentMapper.mapToStudentDto(savedStudent);
}

    // READ ALL
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::mapToStudentDto)
                .toList();
    }

    // READ BY ID
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        return StudentMapper.mapToStudentDto(student);
    }

    // UPDATE
    public StudentDto updateStudent(Long id, StudentDto studentDto) {

    Student existingStudent = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));

    if (studentRepository.existsByEmailAndIdNot(studentDto.getEmail(), id)) {
        throw new DuplicateEmailException(studentDto.getEmail());
    }

    existingStudent.setFirstName(studentDto.getFirstName());
    existingStudent.setLastName(studentDto.getLastName());
    existingStudent.setEmail(studentDto.getEmail());
    existingStudent.setDepartment(studentDto.getDepartment());

    Student updatedStudent = studentRepository.save(existingStudent);

    return StudentMapper.mapToStudentDto(updatedStudent);
    }

    // DELETE
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }

        studentRepository.deleteById(id);
    }
}