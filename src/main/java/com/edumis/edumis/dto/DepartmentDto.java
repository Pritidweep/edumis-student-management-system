package com.edumis.edumis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotBlank(message = "Department name is mandatory")
    @Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Department code is mandatory")
    @Size(min = 2, max = 20, message = "Department code must be between 2 and 20 characters")
    private String code;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
}