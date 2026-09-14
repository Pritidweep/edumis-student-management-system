package com.edumis.edumis.service;

import com.edumis.edumis.dto.StudentDto;
import com.edumis.edumis.exception.DuplicateEmailException;
import com.edumis.edumis.exception.StudentNotFoundException;
import com.edumis.edumis.mapper.StudentMapper;
import com.edumis.edumis.model.Student;
import com.edumis.edumis.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

     // SEARCH BY FIRST NAME
public List<StudentDto> searchByFirstName(String firstName) {
    return studentRepository.findByFirstNameContainingIgnoreCase(firstName)
            .stream()
            .map(StudentMapper::mapToStudentDto)
            .toList();
}

    // SEARCH BY LAST NAME
public List<StudentDto> searchByLastName(String lastName) {
    return studentRepository.findByLastNameContainingIgnoreCase(lastName)
            .stream()
            .map(StudentMapper::mapToStudentDto)
            .toList();
}

// SEARCH BY EMAIL
public List<StudentDto> searchByEmail(String email) {
    return studentRepository.findByEmailContainingIgnoreCase(email)
            .stream()
            .map(StudentMapper::mapToStudentDto)
            .toList();
}

// SEARCH BY DEPARTMENT
public List<StudentDto> searchByDepartment(String department) {
    return studentRepository.findByDepartmentContainingIgnoreCase(department)
            .stream()
            .map(StudentMapper::mapToStudentDto)
            .toList();
}
// // UNIVERSAL SEARCH
// public List<StudentDto> searchStudents(String query) {

//     return studentRepository
//             .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
//                     query,
//                     query,
//                     query,
//                     query
//             )
//             .stream()
//             .map(StudentMapper::mapToStudentDto)
//             .toList();
// }

    // UNIVERSAL SEARCH WITH PAGINATION
       public Page<StudentDto> searchStudents(String query, Pageable pageable) {
        return studentRepository
            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
                    query,
                    query,
                    query,
                    query,
                    pageable
            )
            .map(StudentMapper::mapToStudentDto);
}
   
// READ ALL
    public Page<StudentDto> getAllStudents(Pageable pageable) {

    return studentRepository.findAll(pageable)
            .map(StudentMapper::mapToStudentDto);
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