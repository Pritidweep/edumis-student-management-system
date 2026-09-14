package com.edumis.edumis.service;

import com.edumis.edumis.dto.DepartmentDto;
import com.edumis.edumis.exception.DepartmentNotFoundException;
import com.edumis.edumis.exception.DuplicateDepartmentException;
import com.edumis.edumis.mapper.DepartmentMapper;
import com.edumis.edumis.model.Department;
import com.edumis.edumis.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // CREATE
   public DepartmentDto createDepartment(DepartmentDto departmentDto) {

    // Check duplicate department name
    departmentRepository.findByName(departmentDto.getName())
            .ifPresent(department -> {
                throw new DuplicateDepartmentException(
                        "Department name already exists: " + departmentDto.getName()
                );
            });

    // Check duplicate department code
    departmentRepository.findByCode(departmentDto.getCode())
            .ifPresent(department -> {
                throw new DuplicateDepartmentException(
                        "Department code already exists: " + departmentDto.getCode()
                );
            });

    Department department =
            DepartmentMapper.mapToDepartment(departmentDto);

    Department savedDepartment =
            departmentRepository.save(department);

    return DepartmentMapper.mapToDepartmentDto(savedDepartment);

    }

    // READ ALL
    public Page<DepartmentDto> getAllDepartments(Pageable pageable) {

    return departmentRepository.findAll(pageable)
            .map(DepartmentMapper::mapToDepartmentDto);
     
    }

    // READ BY ID
    public DepartmentDto getDepartmentById(Long id) {
        
        Department department = departmentRepository.findById(id)
            .orElseThrow(() -> new DepartmentNotFoundException(id));

    return DepartmentMapper.mapToDepartmentDto(department);
   
    }

    // UPDATE
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {

    Department existingDepartment = departmentRepository.findById(id)
            .orElseThrow(() -> new DepartmentNotFoundException(id));

    // Check duplicate department name
    if (departmentRepository.existsByNameAndIdNot(departmentDto.getName(), id)) {
        throw new DuplicateDepartmentException(
                "Department name already exists: " + departmentDto.getName()
        );
    }

    // Check duplicate department code
    if (departmentRepository.existsByCodeAndIdNot(departmentDto.getCode(), id)) {
        throw new DuplicateDepartmentException(
                "Department code already exists: " + departmentDto.getCode()
        );
    }

    existingDepartment.setName(departmentDto.getName());
    existingDepartment.setCode(departmentDto.getCode());
    existingDepartment.setDescription(departmentDto.getDescription());

    Department updatedDepartment =
            departmentRepository.save(existingDepartment);

    return DepartmentMapper.mapToDepartmentDto(updatedDepartment);

    }

    // DELETE
    public void deleteDepartment(Long id) {

    if (!departmentRepository.existsById(id)) {
    throw new DepartmentNotFoundException(id);
    
    }

    departmentRepository.deleteById(id);
  
   }

   // SEARCH BY NAME
   public List<DepartmentDto> searchByName(String name) {

    return departmentRepository.findByNameContainingIgnoreCase(name)
            .stream()
            .map(DepartmentMapper::mapToDepartmentDto)
            .toList();

    }
    
    // SEARCH BY CODE
    public List<DepartmentDto> searchByCode(String code) {

    return departmentRepository.findByCodeContainingIgnoreCase(code)
            .stream()
            .map(DepartmentMapper::mapToDepartmentDto)
            .toList();

    }
    
    // SEARCH BY DESCRIPTION
    public List<DepartmentDto> searchByDescription(String description) {

    return departmentRepository.findByDescriptionContainingIgnoreCase(description)
            .stream()
            .map(DepartmentMapper::mapToDepartmentDto)
            .toList();

    }

    // UNIVERSAL SEARCH
    public Page<DepartmentDto> searchDepartments(
        String query,
        Pageable pageable) {

    return departmentRepository
            .findByNameContainingIgnoreCaseOrCodeContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    query,
                    query,
                    query,
                    pageable
            )
            .map(DepartmentMapper::mapToDepartmentDto);
        
        }

}
