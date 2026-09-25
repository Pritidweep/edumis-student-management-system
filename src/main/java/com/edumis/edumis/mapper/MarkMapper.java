package com.edumis.edumis.mapper;

import com.edumis.edumis.dto.MarkDto;
import com.edumis.edumis.model.Course;
import com.edumis.edumis.model.Mark;
import com.edumis.edumis.model.Student;

public class MarkMapper {

    // ENTITY -> DTO
    public static MarkDto mapToMarkDto(Mark mark) {
        return new MarkDto(
                mark.getId(),
                mark.getStudent().getId(),
                mark.getCourse().getId(),
                mark.getExamType(),
                mark.getMarksObtained(),
                mark.getMaximumMarks(),
                mark.getAcademicYear(),
                mark.getSemester()
        );
    }

    // DTO -> ENTITY
    public static Mark mapToMark(
            MarkDto markDto,
            Student student,
            Course course) {

        return new Mark(
                markDto.getId(),
                student,
                course,
                markDto.getExamType(),
                markDto.getMarksObtained(),
                markDto.getMaximumMarks(),
                markDto.getAcademicYear(),
                markDto.getSemester()
        );
    }
}