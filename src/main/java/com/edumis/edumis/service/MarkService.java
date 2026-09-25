package com.edumis.edumis.service;

import org.springframework.stereotype.Service;

import com.edumis.edumis.dto.MarkDto;
import com.edumis.edumis.exception.CourseNotFoundException;
import com.edumis.edumis.exception.DuplicateMarkException;
import com.edumis.edumis.exception.MarkNotFoundException;
import com.edumis.edumis.exception.StudentNotFoundException;
import com.edumis.edumis.mapper.MarkMapper;
import com.edumis.edumis.model.Mark;
import com.edumis.edumis.model.Course;
import com.edumis.edumis.model.Student;
import com.edumis.edumis.repository.CourseRepository;
import com.edumis.edumis.repository.MarkRepository;
import com.edumis.edumis.repository.StudentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class MarkService {

    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public MarkService(
            MarkRepository markRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // CREATE
    public MarkDto createMark(MarkDto markDto) {

        Student student = studentRepository
                .findById(markDto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(
                        markDto.getStudentId()));

        Course course = courseRepository
                .findById(markDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(
                        markDto.getCourseId()));

        if (markDto.getMarksObtained() > markDto.getMaximumMarks()) {
            throw new IllegalArgumentException(
                    "Marks obtained cannot exceed maximum marks");
        }

        if (markRepository
                .existsByStudentIdAndCourseIdAndExamTypeIgnoreCaseAndAcademicYearAndSemester(
                        student.getId(),
                        course.getId(),
                        markDto.getExamType(),
                        markDto.getAcademicYear(),
                        markDto.getSemester())) {

            throw new DuplicateMarkException(
                    "Marks already exist for this student, course, exam type, academic year and semester");
        }

        Mark mark = MarkMapper.mapToMark(
                markDto,
                student,
                course);

        Mark savedMark = markRepository.save(mark);

        return MarkMapper.mapToMarkDto(savedMark);
    }

    // READ ALL
    public Page<MarkDto> getAllMarks(Pageable pageable) {

        return markRepository
                .findAll(pageable)
                .map(MarkMapper::mapToMarkDto);
    }

    // READ BY ID
    public MarkDto getMarkById(Long id) {

        Mark mark = markRepository
                .findById(id)
                .orElseThrow(() -> new MarkNotFoundException(id)

                );

        return MarkMapper.mapToMarkDto(mark);

    }

    // UPDATE
    public MarkDto updateMark(
            Long id,
            MarkDto markDto) {

        Mark existingMark = markRepository
                .findById(id)
                .orElseThrow(() -> new MarkNotFoundException(id));

        Student student = studentRepository
                .findById(markDto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(
                        markDto.getStudentId()));

        Course course = courseRepository
                .findById(markDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(
                        markDto.getCourseId()));

        if (markDto.getMarksObtained() > markDto.getMaximumMarks()) {
            throw new IllegalArgumentException(
                    "Marks obtained cannot exceed maximum marks");
        }

        if (markRepository
                .existsByStudentIdAndCourseIdAndExamTypeIgnoreCaseAndAcademicYearAndSemesterAndIdNot(
                        student.getId(),
                        course.getId(),
                        markDto.getExamType(),
                        markDto.getAcademicYear(),
                        markDto.getSemester(),
                        id)) {

            throw new DuplicateMarkException(
                    "Marks already exist for this student, course, exam type, academic year and semester");
        }

        existingMark.setStudent(student);
        existingMark.setCourse(course);
        existingMark.setExamType(markDto.getExamType());
        existingMark.setMarksObtained(markDto.getMarksObtained());
        existingMark.setMaximumMarks(markDto.getMaximumMarks());
        existingMark.setAcademicYear(markDto.getAcademicYear());
        existingMark.setSemester(markDto.getSemester());

        Mark updatedMark = markRepository.save(existingMark);

        return MarkMapper.mapToMarkDto(updatedMark);
    }

    // DELETE
    public void deleteMark(Long id) {

        if (!markRepository.existsById(id)) {
            throw new MarkNotFoundException(id);
        }

        markRepository.deleteById(id);
    }

    // SEARCH BY STUDENT
    public Page<MarkDto> searchByStudent(
            Long studentId,
            Pageable pageable) {

        return markRepository
                .findByStudentId(studentId, pageable)
                .map(MarkMapper::mapToMarkDto);
    }

    // SEARCH BY COURSE
    public Page<MarkDto> searchByCourse(
            Long courseId,
            Pageable pageable) {

        return markRepository
                .findByCourseId(courseId, pageable)
                .map(MarkMapper::mapToMarkDto);
    }

    // SEARCH BY EXAM TYPE
    public Page<MarkDto> searchByExamType(
            String examType,
            Pageable pageable) {

        return markRepository
                .findByExamTypeContainingIgnoreCase(
                        examType,
                        pageable)
                .map(MarkMapper::mapToMarkDto);
    }

    // SEARCH BY ACADEMIC YEAR
    public Page<MarkDto> searchByAcademicYear(
            String academicYear,
            Pageable pageable) {

        return markRepository
                .findByAcademicYearContainingIgnoreCase(
                        academicYear,
                        pageable)
                .map(MarkMapper::mapToMarkDto);
    }

    // SEARCH BY SEMESTER
    public Page<MarkDto> searchBySemester(
            Integer semester,
            Pageable pageable) {

        return markRepository
                .findBySemester(
                        semester,
                        pageable)
                .map(MarkMapper::mapToMarkDto);
    }

}