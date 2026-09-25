package com.edumis.edumis.controller;

import com.edumis.edumis.dto.MarkDto;
import com.edumis.edumis.service.MarkService;

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
@RequestMapping("/api/v1/marks")
public class MarkController {

    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<MarkDto> createMark(
            @Valid @RequestBody MarkDto markDto) {

        MarkDto createdMark = markService.createMark(markDto);

        return new ResponseEntity<>(
                createdMark,
                HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<Page<MarkDto>> getAllMarks(
            Pageable pageable) {

        return ResponseEntity.ok(
                markService.getAllMarks(pageable));
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<MarkDto> getMarkById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                markService.getMarkById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<MarkDto> updateMark(
            @PathVariable Long id,
            @Valid @RequestBody MarkDto markDto) {

        return ResponseEntity.ok(
                markService.updateMark(
                        id,
                        markDto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMark(
            @PathVariable Long id) {

        markService.deleteMark(id);

        return ResponseEntity.noContent().build();
    }

    // SEARCH BY STUDENT
    @GetMapping("/search/student")
    public ResponseEntity<Page<MarkDto>> searchByStudent(
            @RequestParam Long studentId,
            Pageable pageable) {

        return ResponseEntity.ok(
                markService.searchByStudent(
                        studentId,
                        pageable));
    }

    // SEARCH BY COURSE
    @GetMapping("/search/course")
    public ResponseEntity<Page<MarkDto>> searchByCourse(
            @RequestParam Long courseId,
            Pageable pageable) {

        return ResponseEntity.ok(
                markService.searchByCourse(
                        courseId,
                        pageable));
    }

    // SEARCH BY EXAM TYPE
    @GetMapping("/search/exam-type")
    public ResponseEntity<Page<MarkDto>> searchByExamType(
            @RequestParam String examType,
            Pageable pageable) {

        return ResponseEntity.ok(
                markService.searchByExamType(
                        examType,
                        pageable));
    }

    // SEARCH BY ACADEMIC YEAR
    @GetMapping("/search/academic-year")
    public ResponseEntity<Page<MarkDto>> searchByAcademicYear(
            @RequestParam String academicYear,
            Pageable pageable) {

        return ResponseEntity.ok(
                markService.searchByAcademicYear(
                        academicYear,
                        pageable));
    }

    // SEARCH BY SEMESTER
    @GetMapping("/search/semester")
    public ResponseEntity<Page<MarkDto>> searchBySemester(
            @RequestParam Integer semester,
            Pageable pageable) {

        return ResponseEntity.ok(
                markService.searchBySemester(
                        semester,
                        pageable));
    }
}