package com.edumis.edumis.mapper;

import com.edumis.edumis.dto.CourseDto;
import com.edumis.edumis.model.Course;

public class CourseMapper {

    public static CourseDto mapToCourseDto(Course course) {
        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getCode(),
                course.getDepartment(),
                course.getDurationInYears(),
                course.getDescription()
        );
    }

    public static Course mapToCourse(CourseDto courseDto) {
        return new Course(
                courseDto.getId(),
                courseDto.getName(),
                courseDto.getCode(),
                courseDto.getDepartment(),
                courseDto.getDurationInYears(),
                courseDto.getDescription()
        );
    }
}