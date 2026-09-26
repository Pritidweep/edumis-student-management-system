package com.edumis.edumis.mapper;

import com.edumis.edumis.dto.SubjectDto;
import com.edumis.edumis.model.Course;
import com.edumis.edumis.model.Subject;

public class SubjectMapper {

    // ENTITY -> DTO
    public static SubjectDto mapToSubjectDto(Subject subject) {

        return new SubjectDto(
                subject.getId(),
                subject.getName(),
                subject.getCode(),
                subject.getCourse().getId(),
                subject.getSemester(),
                subject.getCredits(),
                subject.getDescription()
        );
    }

    // DTO -> ENTITY
    public static Subject mapToSubject(
            SubjectDto subjectDto,
            Course course) {

        return new Subject(
                subjectDto.getId(),
                subjectDto.getName(),
                subjectDto.getCode(),
                course,
                subjectDto.getSemester(),
                subjectDto.getCredits(),
                subjectDto.getDescription()
        );
    }
}