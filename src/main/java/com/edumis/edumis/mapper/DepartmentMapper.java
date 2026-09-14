package com.edumis.edumis.mapper;

import com.edumis.edumis.dto.DepartmentDto;
import com.edumis.edumis.model.Department;

public class DepartmentMapper {

    // Department Entity → DepartmentDto
    public static DepartmentDto mapToDepartmentDto(Department department) {

        return new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getCode(),
                department.getDescription()
        );
    }

    // DepartmentDto → Department Entity
    public static Department mapToDepartment(DepartmentDto departmentDto) {

        return new Department(
                departmentDto.getId(),
                departmentDto.getName(),
                departmentDto.getCode(),
                departmentDto.getDescription()
        );
    }
}