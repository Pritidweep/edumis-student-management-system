package com.edumis.edumis.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    private Long id;

    @NotBlank(message = "Subject name is mandatory")
    @Size(min = 2, max = 100, message = "Subject name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Subject code is mandatory")
    @Size(min = 2, max = 20, message = "Subject code must be between 2 and 20 characters")
    private String code;

    @NotNull(message = "Course ID is mandatory")
    private Long courseId;

    @NotNull(message = "Semester is mandatory")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 12, message = "Semester must not exceed 12")
    private Integer semester;

    @NotNull(message = "Credits are mandatory")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 10, message = "Credits must not exceed 10")
    private Integer credits;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
}