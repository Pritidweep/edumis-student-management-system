package com.edumis.edumis.controller;

import com.edumis.edumis.dto.StudentDto;
import com.edumis.edumis.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(
            @Valid @RequestBody StudentDto studentDto) {

        StudentDto createdStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    // READ ALL - PAGINATED
    @GetMapping
    public Page<StudentDto> getAllStudents(Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDto studentDto) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, studentDto)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    // SEARCH BY FIRST NAME
@GetMapping("/search/firstname")
public ResponseEntity<List<StudentDto>> searchByFirstName(
        @RequestParam String firstName) {

    return ResponseEntity.ok(
            studentService.searchByFirstName(firstName)
    );
}

// SEARCH BY LAST NAME
@GetMapping("/search/lastname")
public ResponseEntity<List<StudentDto>> searchByLastName(
        @RequestParam String lastName) {

    return ResponseEntity.ok(
            studentService.searchByLastName(lastName)
    );
}

// SEARCH BY EMAIL
@GetMapping("/search/email")
public ResponseEntity<List<StudentDto>> searchByEmail(
        @RequestParam String email) {

    return ResponseEntity.ok(
            studentService.searchByEmail(email)
    );
}

// SEARCH BY DEPARTMENT
@GetMapping("/search/department")
public ResponseEntity<List<StudentDto>> searchByDepartment(
        @RequestParam String department) {

    return ResponseEntity.ok(
            studentService.searchByDepartment(department)
    );
}

// // UNIVERSAL SEARCH
// @GetMapping("/search")
// public ResponseEntity<List<StudentDto>> searchStudents(
//         @RequestParam String query) {

//     return ResponseEntity.ok(
//             studentService.searchStudents(query)
//     );
// }

// UNIVERSAL SEARCH WITH PAGINATION + SORTING
@GetMapping("/search")
public Page<StudentDto> searchStudents(
        @RequestParam String query,
        Pageable pageable) {

    return studentService.searchStudents(query, pageable);
}
}