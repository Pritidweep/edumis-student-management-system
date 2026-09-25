package com.edumis.edumis.controller;

import com.edumis.edumis.dto.EnrollmentDto;
import com.edumis.edumis.service.EnrollmentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    
    }
    
    // CREATE

    @PostMapping
    public ResponseEntity<EnrollmentDto> createEnrollment(
        @Valid @RequestBody EnrollmentDto enrollmentDto) {
            
            EnrollmentDto createdEnrollment =
            enrollmentService.createEnrollment(enrollmentDto);
            
            return new ResponseEntity<>(
            createdEnrollment,
            HttpStatus.CREATED
        );
    }

    // READ ALL

    @GetMapping
    public ResponseEntity<Page<EnrollmentDto>> getAllEnrollments(
        Pageable pageable) {
            return ResponseEntity.ok(
                enrollmentService.getAllEnrollments(pageable)
            );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(
        @PathVariable Long id) {
            return ResponseEntity.ok(
                enrollmentService.getEnrollmentById(id)
            );
   }

   // UPDATE
   @PutMapping("/{id}")
   public ResponseEntity<EnrollmentDto> updateEnrollment(
    @PathVariable Long id,
    @Valid @RequestBody EnrollmentDto enrollmentDto) {
        
        return ResponseEntity.ok(
            enrollmentService.updateEnrollment(id, enrollmentDto)
        );
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(
        @PathVariable Long id) {

            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.noContent().build();
        }
    
    // SEARCH BY STUDENT ID
    @GetMapping("/search/student")
    public ResponseEntity<Page<EnrollmentDto>> searchByStudentId(
        @RequestParam Long studentId,
        Pageable pageable) {
            
            return ResponseEntity.ok(
            enrollmentService.searchByStudentId(
                    studentId,
                    pageable
            )
        );
    }

    // SEARCH BY COURSE ID
    @GetMapping("/search/course")
    public ResponseEntity<Page<EnrollmentDto>> searchByCourseId(
        @RequestParam Long courseId,
        Pageable pageable) {
            
            return ResponseEntity.ok(
                enrollmentService.searchByCourseId(
                    courseId,
                    pageable
                )
            );
    }

    // SEARCH BY ACADEMIC YEAR
    @GetMapping("/search/academic-year")
    public ResponseEntity<Page<EnrollmentDto>> searchByAcademicYear(
        @RequestParam String academicYear,
        Pageable pageable) {
            
            return ResponseEntity.ok(
                enrollmentService.searchByAcademicYear(
                    academicYear,
                    pageable
                )
            );
        }

    // SEARCH BY STATUS
    @GetMapping("/search/status")
    public ResponseEntity<Page<EnrollmentDto>> searchByStatus(
        @RequestParam String status,
        Pageable pageable) {
            
            return ResponseEntity.ok(
                enrollmentService.searchByStatus(
                    status,
                    pageable
                )
            );
        }

    // SEARCH BY SEMESTER
    @GetMapping("/search/semester")
    public ResponseEntity<Page<EnrollmentDto>> searchBySemester(
        @RequestParam Integer semester,
        Pageable pageable) {
            
            return ResponseEntity.ok(
                enrollmentService.searchBySemester(
                    semester,
                    pageable
            )
        );
    }

    
}