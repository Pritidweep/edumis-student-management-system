package com.edumis.edumis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(StudentNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleStudentNotFound(
                        StudentNotFoundException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationExceptions(
                        MethodArgumentNotValidException ex) {

                Map<String, String> errors = new HashMap<>();

                ex.getBindingResult().getAllErrors().forEach(error -> {
                        String fieldName = ((FieldError) error).getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                });

                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(DuplicateEmailException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateEmail(
                        DuplicateEmailException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.CONFLICT.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }

        // HANDLE DEPARTMENT NOT FOUND
        @ExceptionHandler(DepartmentNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleDepartmentNotFound(
                        DepartmentNotFoundException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        }

        // HANDLE DUPLICATE DEPARTMENT
        @ExceptionHandler(DuplicateDepartmentException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateDepartment(
                        DuplicateDepartmentException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.CONFLICT.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(error, HttpStatus.CONFLICT);

        }

        @ExceptionHandler(TeacherNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleTeacherNotFound(
                        TeacherNotFoundException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        }

        @ExceptionHandler(DuplicateTeacherEmailException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateTeacherEmail(
                        DuplicateTeacherEmailException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.CONFLICT.value(),
                                ex.getMessage(),
                                LocalDateTime.now()

                );

                return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }

        // HANDLE COURSE NOT FOUND
        @ExceptionHandler(CourseNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleCourseNotFound(
                        CourseNotFoundException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                LocalDateTime.now()

                );

                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        }

        // HANDLE DUPLICATE COURSE
        @ExceptionHandler(DuplicateCourseException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateCourse(
                        DuplicateCourseException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.CONFLICT.value(),
                                ex.getMessage(),
                                LocalDateTime.now()

                );

                return new ResponseEntity<>(error, HttpStatus.CONFLICT);

        }

        @ExceptionHandler(EnrollmentNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleEnrollmentNotFound(
                        EnrollmentNotFoundException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        // HANDLE DUPLICATE ENROLLMENT
        @ExceptionHandler(DuplicateEnrollmentException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateEnrollment(
                        DuplicateEnrollmentException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.CONFLICT.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(error, HttpStatus.CONFLICT);

        }

        // HANDLE ATTENDANCE NOT FOUND
        @ExceptionHandler(AttendanceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleAttendanceNotFound(
                        AttendanceNotFoundException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(
                                error,
                                HttpStatus.NOT_FOUND);
        }

        // HANDLE DUPLICATE ATTENDANCE
        @ExceptionHandler(DuplicateAttendanceException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateAttendance(
                        DuplicateAttendanceException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.CONFLICT.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }

        // HANDLE MARK NOT FOUND
        @ExceptionHandler(MarkNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleMarkNotFound(
                        MarkNotFoundException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(
                                error,
                                HttpStatus.NOT_FOUND);
        }

        // HANDLE DUPLICATE MARK
        @ExceptionHandler(DuplicateMarkException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateMark(
                        DuplicateMarkException ex) {

                ErrorResponse error = new ErrorResponse(

                                HttpStatus.CONFLICT.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(
                                error,
                                HttpStatus.CONFLICT);
        }

        // HANDLE INVALID ARGUMENT
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleIllegalArgument(
                        IllegalArgumentException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(
                                error,
                                HttpStatus.BAD_REQUEST);
        }

        // HANDLE SUBJECT NOT FOUND
        @ExceptionHandler(SubjectNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleSubjectNotFound(
                        SubjectNotFoundException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(
                                error,
                                HttpStatus.NOT_FOUND);
        }

        // HANDLE DUPLICATE SUBJECT
        @ExceptionHandler(DuplicateSubjectException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateSubject(
                        DuplicateSubjectException ex) {

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.CONFLICT.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return new ResponseEntity<>(
                                error,
                                HttpStatus.CONFLICT);
        }

}