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
public class CourseDto {

    private Long id;

    @NotBlank(message = "Course name is mandatory")
    @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Course code is mandatory")
    @Size(min = 2, max = 20, message = "Course code must be between 2 and 20 characters")
    private String code;

    @NotBlank(message = "Department is mandatory")
    private String department;

    @NotNull(message = "Course duration is mandatory")
    @Min(value = 1, message = "Course duration must be at least 1 year")
    @Max(value = 10, message = "Course duration must not exceed 10 years")
    private Integer durationInYears;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
}