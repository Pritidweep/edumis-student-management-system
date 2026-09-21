package com.edumis.edumis.controller;

import com.edumis.edumis.dto.CourseDto;
import com.edumis.edumis.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CourseDto> createCourse(
            @Valid @RequestBody CourseDto courseDto) {

        CourseDto createdCourse =
                courseService.createCourse(courseDto);

        return new ResponseEntity<>(
                createdCourse,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<Page<CourseDto>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(
            courseService.getAllCourses(pageable)
        );
    }
    
    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(
        @PathVariable Long id) {
            
            return ResponseEntity.ok(
                courseService.getCourseById(id)
            );
        }
    
    
    // SEARCH BY NAME
    @GetMapping("/search/name")
    public ResponseEntity<List<CourseDto>> searchByName(
        @RequestParam String name) {
            return ResponseEntity.ok(
                courseService.searchByName(name)
            );
    }
    
    // SEARCH BY CODE
    @GetMapping("/search/code")
    public ResponseEntity<List<CourseDto>> searchByCode(
        @RequestParam String code) {
            
            return ResponseEntity.ok(
            courseService.searchByCode(code)
        );
    }
    
    // SEARCH BY DEPARTMENT
    @GetMapping("/search/department")
    public ResponseEntity<List<CourseDto>> searchByDepartment(
        @RequestParam String department) {
            
            return ResponseEntity.ok(
                courseService.searchByDepartment(department)
            );
    }
        
    // SEARCH BY DESCRIPTION
    @GetMapping("/search/description")
    public ResponseEntity<List<CourseDto>> searchByDescription(
        @RequestParam String description) {
            
            return ResponseEntity.ok(
                courseService.searchByDescription(description)
            );
    }

    // UNIVERSAL SEARCH
    @GetMapping("/search")
    public ResponseEntity<Page<CourseDto>> searchCourses(
        @RequestParam String query,
        Pageable pageable) {
            
            return ResponseEntity.ok(
            courseService.searchCourses(query, pageable)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseDto courseDto) {

        return ResponseEntity.ok(
                courseService.updateCourse(id, courseDto)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}