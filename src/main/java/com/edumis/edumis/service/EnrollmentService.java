package com.edumis.edumis.service;

import org.springframework.stereotype.Service;

import com.edumis.edumis.dto.EnrollmentDto;
import com.edumis.edumis.exception.CourseNotFoundException;
import com.edumis.edumis.exception.DuplicateEnrollmentException;
import com.edumis.edumis.exception.EnrollmentNotFoundException;
import com.edumis.edumis.exception.StudentNotFoundException;
import com.edumis.edumis.mapper.EnrollmentMapper;
import com.edumis.edumis.model.Course;
import com.edumis.edumis.model.Enrollment;
import com.edumis.edumis.model.Student;
import com.edumis.edumis.repository.CourseRepository;
import com.edumis.edumis.repository.EnrollmentRepository;
import com.edumis.edumis.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service

public class EnrollmentService {

        private final EnrollmentRepository enrollmentRepository;
        private final StudentRepository studentRepository;
        private final CourseRepository courseRepository;

        public EnrollmentService(
                        EnrollmentRepository enrollmentRepository,
                        StudentRepository studentRepository,
                        CourseRepository courseRepository) {
                this.enrollmentRepository = enrollmentRepository;
                this.studentRepository = studentRepository;
                this.courseRepository = courseRepository;
        }

        // CREATE
        public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto) {

                Student student = studentRepository
                                .findById(enrollmentDto.getStudentId())
                                .orElseThrow(() -> new StudentNotFoundException(
                                                enrollmentDto.getStudentId()));

                Course course = courseRepository
                                .findById(enrollmentDto.getCourseId())
                                .orElseThrow(() -> new CourseNotFoundException(
                                                enrollmentDto.getCourseId()));

                if (enrollmentRepository.existsByStudentIdAndCourseId(
                                student.getId(),
                                course.getId())) {

                        throw new DuplicateEnrollmentException(
                                        "Student is already enrolled in this course");
                }

                Enrollment enrollment = EnrollmentMapper.mapToEnrollment(
                                enrollmentDto,
                                student,
                                course);

                Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

                return EnrollmentMapper.mapToEnrollmentDto(savedEnrollment);

        }

        // READ ALL
        public Page<EnrollmentDto> getAllEnrollments(Pageable pageable) {

                return enrollmentRepository.findAll(pageable)
                                .map(EnrollmentMapper::mapToEnrollmentDto);

        }

        // READ BY ID
        public EnrollmentDto getEnrollmentById(Long id) {

                Enrollment enrollment = enrollmentRepository.findById(id)
                                .orElseThrow(() -> new EnrollmentNotFoundException(id));

                return EnrollmentMapper.mapToEnrollmentDto(enrollment);
        }

        // UPDATE
        public EnrollmentDto updateEnrollment(
                        Long id,
                        EnrollmentDto enrollmentDto) {

                Enrollment existingEnrollment = enrollmentRepository.findById(id)
                                .orElseThrow(() -> new EnrollmentNotFoundException(id));

                Student student = studentRepository
                                .findById(enrollmentDto.getStudentId())
                                .orElseThrow(() -> new StudentNotFoundException(
                                                enrollmentDto.getStudentId()));

                Course course = courseRepository
                                .findById(enrollmentDto.getCourseId())
                                .orElseThrow(() -> new CourseNotFoundException(
                                                enrollmentDto.getCourseId()));

                if (enrollmentRepository.existsByStudentIdAndCourseIdAndIdNot(
                                student.getId(),
                                course.getId(),
                                id)) {

                        throw new DuplicateEnrollmentException(
                                        "Student is already enrolled in this course");
                }

                existingEnrollment.setStudent(student);
                existingEnrollment.setCourse(course);
                existingEnrollment.setEnrollmentDate(
                                enrollmentDto.getEnrollmentDate());
                existingEnrollment.setAcademicYear(
                                enrollmentDto.getAcademicYear());
                existingEnrollment.setSemester(
                                enrollmentDto.getSemester());
                existingEnrollment.setStatus(
                                enrollmentDto.getStatus());

                Enrollment updatedEnrollment = enrollmentRepository.save(existingEnrollment);

                return EnrollmentMapper.mapToEnrollmentDto(updatedEnrollment);

        }

        // DELETE
        public void deleteEnrollment(Long id) {

                if (!enrollmentRepository.existsById(id)) {
                        throw new EnrollmentNotFoundException(id);
                }

                enrollmentRepository.deleteById(id);
        }

        // SEARCH BY STUDENT ID
        public Page<EnrollmentDto> searchByStudentId(
                        Long studentId,
                        Pageable pageable) {

                return enrollmentRepository
                                .findByStudentId(studentId, pageable)
                                .map(EnrollmentMapper::mapToEnrollmentDto);
        }

        // SEARCH BY COURSE ID
        public Page<EnrollmentDto> searchByCourseId(
                        Long courseId,
                        Pageable pageable) {

                return enrollmentRepository
                                .findByCourseId(courseId, pageable)
                                .map(EnrollmentMapper::mapToEnrollmentDto);
        }

        // SEARCH BY ACADEMIC YEAR
        public Page<EnrollmentDto> searchByAcademicYear(
                        String academicYear,
                        Pageable pageable) {

                return enrollmentRepository
                                .findByAcademicYearContainingIgnoreCase(
                                                academicYear,
                                                pageable)
                                .map(EnrollmentMapper::mapToEnrollmentDto);
        }

        // SEARCH BY STATUS
        public Page<EnrollmentDto> searchByStatus(
                        String status,
                        Pageable pageable) {

                return enrollmentRepository
                                .findByStatusContainingIgnoreCase(
                                                status,
                                                pageable)
                                .map(EnrollmentMapper::mapToEnrollmentDto);
        }

        // SEARCH BY SEMESTER
        public Page<EnrollmentDto> searchBySemester(
                        Integer semester,
                        Pageable pageable) {

                return enrollmentRepository
                                .findBySemester(
                                                semester,
                                                pageable)

                                .map(EnrollmentMapper::mapToEnrollmentDto);
        }

}
