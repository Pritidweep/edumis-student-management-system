package com.edumis.edumis.controller;

import com.edumis.edumis.dto.AttendanceDto;
import com.edumis.edumis.service.AttendanceService;

import jakarta.validation.Valid;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(
            AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<AttendanceDto> createAttendance(
            @Valid @RequestBody AttendanceDto attendanceDto) {

        AttendanceDto createdAttendance =
                attendanceService.createAttendance(attendanceDto);

        return new ResponseEntity<>(
                createdAttendance,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<Page<AttendanceDto>> getAllAttendance(
            Pageable pageable) {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance(pageable)
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getAttendanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDto> updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceDto attendanceDto) {

        return ResponseEntity.ok(
                attendanceService.updateAttendance(
                        id,
                        attendanceDto
                )
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.noContent().build();
    }

    // SEARCH BY STUDENT ID
    @GetMapping("/search/student")
    public ResponseEntity<Page<AttendanceDto>> searchByStudentId(
        @RequestParam Long studentId,
        Pageable pageable) {
            
            return ResponseEntity.ok(
                attendanceService.searchByStudentId(
                    studentId,
                    pageable
                )
            );
    }

    // SEARCH BY COURSE ID
    @GetMapping("/search/course")
    public ResponseEntity<Page<AttendanceDto>> searchByCourseId(
        @RequestParam Long courseId,
        Pageable pageable) {
            
            return ResponseEntity.ok(
                attendanceService.searchByCourseId(
                    courseId,
                    pageable
                )
            );
    }

    // SEARCH BY ATTENDANCE DATE
    @GetMapping("/search/date")
    public ResponseEntity<Page<AttendanceDto>> searchByAttendanceDate(
        @RequestParam
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate attendanceDate,
        Pageable pageable) {
            
            return ResponseEntity.ok(
                attendanceService.searchByAttendanceDate(
                    attendanceDate,
                    pageable
                )
            );
    }

    // SEARCH BY STATUS
    @GetMapping("/search/status")
    public ResponseEntity<Page<AttendanceDto>> searchByStatus(
        @RequestParam String status,
        Pageable pageable) {
            
            return ResponseEntity.ok(
                attendanceService.searchByStatus(
                    status,
                    pageable
                )
            );
    }

    


}