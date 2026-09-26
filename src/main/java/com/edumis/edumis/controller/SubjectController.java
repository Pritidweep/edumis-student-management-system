package com.edumis.edumis.controller;

import com.edumis.edumis.dto.SubjectDto;
import com.edumis.edumis.service.SubjectService;

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
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<SubjectDto> createSubject(
            @Valid @RequestBody SubjectDto subjectDto) {

        SubjectDto createdSubject = subjectService.createSubject(subjectDto);

        return new ResponseEntity<>(
                createdSubject,
                HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<Page<SubjectDto>> getAllSubjects(
            Pageable pageable) {

        return ResponseEntity.ok(
                subjectService.getAllSubjects(pageable));
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                subjectService.getSubjectById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<SubjectDto> updateSubject(
            @PathVariable Long id,
            @Valid @RequestBody SubjectDto subjectDto) {

        return ResponseEntity.ok(
                subjectService.updateSubject(
                        id,
                        subjectDto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(
            @PathVariable Long id) {

        subjectService.deleteSubject(id);

        return ResponseEntity.noContent().build();
    }

    // SEARCH BY COURSE
    @GetMapping("/search/course")
    public ResponseEntity<Page<SubjectDto>> searchByCourse(
            @RequestParam Long courseId,
            Pageable pageable) {

        return ResponseEntity.ok(
                subjectService.searchByCourse(
                        courseId,
                        pageable));
    }

    // SEARCH BY SEMESTER
    @GetMapping("/search/semester")
    public ResponseEntity<Page<SubjectDto>> searchBySemester(
            @RequestParam Integer semester,
            Pageable pageable) {

        return ResponseEntity.ok(
                subjectService.searchBySemester(
                        semester,
                        pageable));
    }

    // SEARCH BY NAME
    @GetMapping("/search/name")
    public ResponseEntity<Page<SubjectDto>> searchByName(
            @RequestParam String name,
            Pageable pageable) {

        return ResponseEntity.ok(
                subjectService.searchByName(
                        name,
                        pageable));
    }

    // SEARCH BY CODE
    @GetMapping("/search/code")
    public ResponseEntity<Page<SubjectDto>> searchByCode(
            @RequestParam String code,
            Pageable pageable) {

        return ResponseEntity.ok(
                subjectService.searchByCode(
                        code,
                        pageable));
    }

    // UNIVERSAL SEARCH
    @GetMapping("/search")
    public ResponseEntity<Page<SubjectDto>> searchSubjects(
            @RequestParam String query,
            Pageable pageable) {

        return ResponseEntity.ok(
                subjectService.searchSubjects(
                        query,
                        pageable));
    }
}