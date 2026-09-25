package com.edumis.edumis.service;

import org.springframework.stereotype.Service;

import com.edumis.edumis.dto.AttendanceDto;
import com.edumis.edumis.exception.AttendanceNotFoundException;
import com.edumis.edumis.exception.CourseNotFoundException;
import com.edumis.edumis.exception.DuplicateAttendanceException;
import com.edumis.edumis.exception.StudentNotFoundException;
import com.edumis.edumis.mapper.AttendanceMapper;
import com.edumis.edumis.model.Attendance;
import com.edumis.edumis.model.Course;
import com.edumis.edumis.model.Student;
import com.edumis.edumis.repository.AttendanceRepository;
import com.edumis.edumis.repository.CourseRepository;
import com.edumis.edumis.repository.StudentRepository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // CREATE
    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {

        Student student = studentRepository
                .findById(attendanceDto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(
                        attendanceDto.getStudentId()));

        Course course = courseRepository
                .findById(attendanceDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(
                        attendanceDto.getCourseId()));

        if (attendanceRepository.existsByStudentIdAndCourseIdAndAttendanceDate(
                student.getId(),
                course.getId(),
                attendanceDto.getAttendanceDate())) {

            throw new DuplicateAttendanceException(
                    "Attendance already exists for this student, course and date");
        }

        Attendance attendance = AttendanceMapper.mapToAttendance(
                attendanceDto,
                student,
                course);

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return AttendanceMapper.mapToAttendanceDto(savedAttendance);
    }

    // READ ALL
    public Page<AttendanceDto> getAllAttendance(Pageable pageable) {
        return attendanceRepository
                .findAll(pageable)
                .map(AttendanceMapper::mapToAttendanceDto);
    }

    // READ BY ID
    public AttendanceDto getAttendanceById(Long id) {

        Attendance attendance = attendanceRepository
                .findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException(id));

        return AttendanceMapper.mapToAttendanceDto(attendance);
    }

    // UPDATE
    public AttendanceDto updateAttendance(
            Long id,
            AttendanceDto attendanceDto) {

        Attendance existingAttendance = attendanceRepository
                .findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException(id));

        Student student = studentRepository
                .findById(attendanceDto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(
                        attendanceDto.getStudentId()));

        Course course = courseRepository
                .findById(attendanceDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(
                        attendanceDto.getCourseId()));

        if (attendanceRepository
                .existsByStudentIdAndCourseIdAndAttendanceDateAndIdNot(
                        student.getId(),
                        course.getId(),
                        attendanceDto.getAttendanceDate(),
                        id)) {

            throw new DuplicateAttendanceException(
                    "Attendance already exists for this student, course and date");
        }

        existingAttendance.setStudent(student);
        existingAttendance.setCourse(course);
        existingAttendance.setAttendanceDate(
                attendanceDto.getAttendanceDate());
        existingAttendance.setStatus(
                attendanceDto.getStatus());

        Attendance updatedAttendance = attendanceRepository.save(existingAttendance);

        return AttendanceMapper.mapToAttendanceDto(updatedAttendance);
    }

    // DELETE
    public void deleteAttendance(Long id) {

        if (!attendanceRepository.existsById(id)) {
            throw new AttendanceNotFoundException(id);
        }

        attendanceRepository.deleteById(id);

    }

    // SEARCH BY STUDENT ID
    public Page<AttendanceDto> searchByStudentId(
            Long studentId,
            Pageable pageable) {

        return attendanceRepository
                .findByStudentId(studentId, pageable)
                .map(AttendanceMapper::mapToAttendanceDto);
    }

    // SEARCH BY COURSE ID
    public Page<AttendanceDto> searchByCourseId(
            Long courseId,
            Pageable pageable) {

        return attendanceRepository
                .findByCourseId(courseId, pageable)
                .map(AttendanceMapper::mapToAttendanceDto);

    }

    // SEARCH BY ATTENDANCE DATE
    public Page<AttendanceDto> searchByAttendanceDate(
            LocalDate attendanceDate,
            Pageable pageable) {

        return attendanceRepository
                .findByAttendanceDate(attendanceDate, pageable)
                .map(AttendanceMapper::mapToAttendanceDto);
    }

    // SEARCH BY STATUS
    public Page<AttendanceDto> searchByStatus(
            String status,
            Pageable pageable) {

        return attendanceRepository
                .findByStatusContainingIgnoreCase(status, pageable)
                .map(AttendanceMapper::mapToAttendanceDto);
    }

}