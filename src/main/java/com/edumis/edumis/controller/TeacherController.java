package com.edumis.edumis.controller;

import com.edumis.edumis.dto.TeacherDto;
import com.edumis.edumis.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(
            @Valid @RequestBody TeacherDto teacherDto) {

        TeacherDto createdTeacher =
                teacherService.createTeacher(teacherDto);

        return new ResponseEntity<>(
                createdTeacher,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public Page<TeacherDto> getAllTeachers(Pageable pageable) {
        return teacherService.getAllTeachers(pageable);
    
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                teacherService.getTeacherById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody TeacherDto teacherDto) {

        return ResponseEntity.ok(
                teacherService.updateTeacher(id, teacherDto)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(
            @PathVariable Long id) {

        teacherService.deleteTeacher(id);

        return ResponseEntity.noContent().build();
    }

    // SEARCH BY FIRST NAME
    @GetMapping("/search/first-name")
    public ResponseEntity<List<TeacherDto>> searchByFirstName(
        @RequestParam String firstName) {
            
            return ResponseEntity.ok(
                teacherService.searchByFirstName(firstName)
            );
        }
        
        // SEARCH BY LAST NAME
@GetMapping("/search/last-name")
public ResponseEntity<List<TeacherDto>> searchByLastName(
        @RequestParam String lastName) {

    return ResponseEntity.ok(
            teacherService.searchByLastName(lastName)
    );
}

// SEARCH BY DEPARTMENT
@GetMapping("/search/department")
public ResponseEntity<List<TeacherDto>> searchByDepartment(
        @RequestParam String department) {

    return ResponseEntity.ok(
            teacherService.searchByDepartment(department)
    );
}

// UNIVERSAL SEARCH
@GetMapping("/search")
public Page<TeacherDto> searchTeachers(
        @RequestParam String query,
        Pageable pageable) {

    return teacherService.searchTeachers(query, pageable);
}
}