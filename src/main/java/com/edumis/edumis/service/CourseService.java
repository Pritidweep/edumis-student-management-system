package com.edumis.edumis.service;

import com.edumis.edumis.dto.CourseDto;
import com.edumis.edumis.exception.CourseNotFoundException;
import com.edumis.edumis.exception.DuplicateCourseException;
import com.edumis.edumis.mapper.CourseMapper;
import com.edumis.edumis.model.Course;
import com.edumis.edumis.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // CREATE
    public CourseDto createCourse(CourseDto courseDto) {

        courseRepository.findByName(courseDto.getName())
                .ifPresent(course -> {
                    throw new DuplicateCourseException(
                            "Course name already exists: " + courseDto.getName());
                });

        courseRepository.findByCode(courseDto.getCode())
                .ifPresent(course -> {
                    throw new DuplicateCourseException(
                            "Course code already exists: " + courseDto.getCode());
                });

        Course course = CourseMapper.mapToCourse(courseDto);

        Course savedCourse = courseRepository.save(course);

        return CourseMapper.mapToCourseDto(savedCourse);
    }

    // READ ALL
    public Page<CourseDto> getAllCourses(Pageable pageable) {

        return courseRepository.findAll(pageable)
                .map(CourseMapper::mapToCourseDto);

    }

    // READ BY ID
    public CourseDto getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        return CourseMapper.mapToCourseDto(course);
    }

    // SEARCH BY NAME
    public List<CourseDto> searchByName(String name) {
        return courseRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(CourseMapper::mapToCourseDto)
                .toList();
    }

    // SEARCH BY CODE
    public List<CourseDto> searchByCode(String code) {
        return courseRepository.findByCodeContainingIgnoreCase(code)
                .stream()
                .map(CourseMapper::mapToCourseDto)
                .toList();
    }

    // SEARCH BY DEPARTMENT
    public List<CourseDto> searchByDepartment(String department) {
        return courseRepository.findByDepartmentContainingIgnoreCase(department)
                .stream()
                .map(CourseMapper::mapToCourseDto)
                .toList();
    }

    // SEARCH BY DESCRIPTION
    public List<CourseDto> searchByDescription(String description) {
        return courseRepository.findByDescriptionContainingIgnoreCase(description)
                .stream()
                .map(CourseMapper::mapToCourseDto)
                .toList();
    }

    // UNIVERSAL SEARCH
    public Page<CourseDto> searchCourses(String query, Pageable pageable) {

        return courseRepository
                .findByNameContainingIgnoreCaseOrCodeContainingIgnoreCaseOrDepartmentContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        query,
                        query,
                        query,
                        query,
                        pageable)
                .map(CourseMapper::mapToCourseDto);

    }

    // UPDATE
    public CourseDto updateCourse(Long id, CourseDto courseDto) {

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        if (courseRepository.existsByNameAndIdNot(courseDto.getName(), id)) {
            throw new DuplicateCourseException(
                    "Course name already exists: " + courseDto.getName());
        }

        if (courseRepository.existsByCodeAndIdNot(courseDto.getCode(), id)) {
            throw new DuplicateCourseException(
                    "Course code already exists: " + courseDto.getCode());
        }

        existingCourse.setName(courseDto.getName());
        existingCourse.setCode(courseDto.getCode());
        existingCourse.setDepartment(courseDto.getDepartment());
        existingCourse.setDurationInYears(courseDto.getDurationInYears());
        existingCourse.setDescription(courseDto.getDescription());

        Course updatedCourse = courseRepository.save(existingCourse);

        return CourseMapper.mapToCourseDto(updatedCourse);
    }

    // DELETE
    public void deleteCourse(Long id) {

        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }

        courseRepository.deleteById(id);
    }
}