package com.edumis.edumis.service;

import com.edumis.edumis.dto.TeacherDto;
import com.edumis.edumis.exception.DuplicateTeacherEmailException;
import com.edumis.edumis.exception.TeacherNotFoundException;
import com.edumis.edumis.mapper.TeacherMapper;
import com.edumis.edumis.model.Teacher;
import com.edumis.edumis.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // CREATE
    public TeacherDto createTeacher(TeacherDto teacherDto) {

        teacherRepository.findByEmail(teacherDto.getEmail())
                .ifPresent(teacher -> {
                    throw new DuplicateTeacherEmailException(
                            "Teacher email already exists: " + teacherDto.getEmail()
                    );
                });

        Teacher teacher = TeacherMapper.mapToTeacher(teacherDto);

        Teacher savedTeacher = teacherRepository.save(teacher);

        return TeacherMapper.mapToTeacherDto(savedTeacher);
    }

    // READ ALL
    public Page<TeacherDto> getAllTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable)
        .map(TeacherMapper::mapToTeacherDto);
    }

    // READ BY ID
    public TeacherDto getTeacherById(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));

        return TeacherMapper.mapToTeacherDto(teacher);
    }

    // UPDATE
    public TeacherDto updateTeacher(Long id, TeacherDto teacherDto) {

        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));

        if (teacherRepository.existsByEmailAndIdNot(teacherDto.getEmail(), id)) {
            throw new DuplicateTeacherEmailException(
                    "Teacher email already exists: " + teacherDto.getEmail()
            );
        }

        existingTeacher.setFirstName(teacherDto.getFirstName());
        existingTeacher.setLastName(teacherDto.getLastName());
        existingTeacher.setEmail(teacherDto.getEmail());
        existingTeacher.setDepartment(teacherDto.getDepartment());
        existingTeacher.setPhone(teacherDto.getPhone());

        Teacher updatedTeacher = teacherRepository.save(existingTeacher);

        return TeacherMapper.mapToTeacherDto(updatedTeacher);
    }

    // DELETE
    public void deleteTeacher(Long id) {

        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException(id);
        }

        teacherRepository.deleteById(id);
    }

    // SEARCH BY FIRST NAME
    public List<TeacherDto> searchByFirstName(String firstName) {
        return teacherRepository.findByFirstNameContainingIgnoreCase(firstName)
            .stream()
            .map(TeacherMapper::mapToTeacherDto)
            .toList();
        }
        
        // SEARCH BY LAST NAME
        public List<TeacherDto> searchByLastName(String lastName) {
            return teacherRepository.findByLastNameContainingIgnoreCase(lastName)
            .stream()
            .map(TeacherMapper::mapToTeacherDto)
            .toList();
        
        }
        
        // SEARCH BY DEPARTMENT
        public List<TeacherDto> searchByDepartment(String department) {
            return teacherRepository.findByDepartmentContainingIgnoreCase(department)
            .stream()
            .map(TeacherMapper::mapToTeacherDto)
            .toList();
        
        }

        // UNIVERSAL SEARCH
        public Page<TeacherDto> searchTeachers(
        String query,
        Pageable pageable) {
            return teacherRepository
            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDepartmentContainingIgnoreCaseOrPhoneContainingIgnoreCase(
                    query,
                    query,
                    query,
                    query,
                    query,
                    pageable
            )
            .map(TeacherMapper::mapToTeacherDto);
        }
}