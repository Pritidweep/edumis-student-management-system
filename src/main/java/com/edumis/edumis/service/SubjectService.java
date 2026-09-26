package com.edumis.edumis.service;

import org.springframework.stereotype.Service;

import com.edumis.edumis.dto.SubjectDto;
import com.edumis.edumis.exception.CourseNotFoundException;
import com.edumis.edumis.exception.DuplicateSubjectException;
import com.edumis.edumis.exception.SubjectNotFoundException;
import com.edumis.edumis.mapper.SubjectMapper;
import com.edumis.edumis.model.Course;
import com.edumis.edumis.model.Subject;
import com.edumis.edumis.repository.CourseRepository;
import com.edumis.edumis.repository.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;

    public SubjectService(
            SubjectRepository subjectRepository,
            CourseRepository courseRepository) {

        this.subjectRepository = subjectRepository;
        this.courseRepository = courseRepository;
    }

    // CREATE
    public SubjectDto createSubject(SubjectDto subjectDto) {

        Course course = courseRepository
                .findById(subjectDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(
                        subjectDto.getCourseId()));

        if (subjectRepository
                .existsByCodeIgnoreCaseAndCourseIdAndSemester(
                        subjectDto.getCode(),
                        course.getId(),
                        subjectDto.getSemester())) {

            throw new DuplicateSubjectException(
                    "Subject already exists with this code, course and semester");
        }

        Subject subject = SubjectMapper.mapToSubject(
                subjectDto,
                course);

        Subject savedSubject = subjectRepository.save(subject);

        return SubjectMapper.mapToSubjectDto(savedSubject);
    }

    // READ ALL
    public Page<SubjectDto> getAllSubjects(Pageable pageable) {

        return subjectRepository
                .findAll(pageable)
                .map(SubjectMapper::mapToSubjectDto);
    }

    // READ BY ID
    public SubjectDto getSubjectById(Long id) {

        Subject subject = subjectRepository
                .findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id));

        return SubjectMapper.mapToSubjectDto(subject);
    }

    // UPDATE
    public SubjectDto updateSubject(
            Long id,
            SubjectDto subjectDto) {

        Subject existingSubject = subjectRepository
                .findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id));

        Course course = courseRepository
                .findById(subjectDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(
                        subjectDto.getCourseId()));

        if (subjectRepository
                .existsByCodeIgnoreCaseAndCourseIdAndSemesterAndIdNot(
                        subjectDto.getCode(),
                        course.getId(),
                        subjectDto.getSemester(),
                        id)) {

            throw new DuplicateSubjectException(
                    "Subject already exists with this code, course and semester");
        }

        existingSubject.setName(subjectDto.getName());
        existingSubject.setCode(subjectDto.getCode());
        existingSubject.setCourse(course);
        existingSubject.setSemester(subjectDto.getSemester());
        existingSubject.setCredits(subjectDto.getCredits());
        existingSubject.setDescription(subjectDto.getDescription());

        Subject updatedSubject = subjectRepository.save(existingSubject);

        return SubjectMapper.mapToSubjectDto(updatedSubject);
    }

    // DELETE
    public void deleteSubject(Long id) {

        if (!subjectRepository.existsById(id)) {
            throw new SubjectNotFoundException(id);
        }

        subjectRepository.deleteById(id);
    }

    // SEARCH BY COURSE
    public Page<SubjectDto> searchByCourse(
            Long courseId,
            Pageable pageable) {

        return subjectRepository
                .findByCourseId(
                        courseId,
                        pageable)
                .map(SubjectMapper::mapToSubjectDto);
    }

    // SEARCH BY SEMESTER
    public Page<SubjectDto> searchBySemester(
            Integer semester,
            Pageable pageable) {

        return subjectRepository
                .findBySemester(
                        semester,
                        pageable)
                .map(SubjectMapper::mapToSubjectDto);
    }

    // SEARCH BY NAME
    public Page<SubjectDto> searchByName(
            String name,
            Pageable pageable) {

        return subjectRepository
                .findByNameContainingIgnoreCase(
                        name,
                        pageable)
                .map(SubjectMapper::mapToSubjectDto);
    }

    // SEARCH BY CODE
    public Page<SubjectDto> searchByCode(
            String code,
            Pageable pageable) {

        return subjectRepository
                .findByCodeContainingIgnoreCase(
                        code,
                        pageable)
                .map(SubjectMapper::mapToSubjectDto);
    }

    // UNIVERSAL SEARCH
    public Page<SubjectDto> searchSubjects(
            String query,
            Pageable pageable) {

        return subjectRepository
                .findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(
                        query,
                        query,
                        pageable)
                .map(SubjectMapper::mapToSubjectDto);
    }
}