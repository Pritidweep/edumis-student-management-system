package com.edumis.edumis.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkDto {

    private Long id;

    @NotNull(message = "Student ID is mandatory")
    private Long studentId;

    @NotNull(message = "Course ID is mandatory")
    private Long courseId;

    @NotBlank(message = "Exam type is mandatory")
    private String examType;

    @NotNull(message = "Marks obtained is mandatory")
    @DecimalMin(value = "0.0", message = "Marks obtained cannot be negative")
    private Double marksObtained;

    @NotNull(message = "Maximum marks is mandatory")
    @DecimalMin(value = "1.0", message = "Maximum marks must be greater than 0")
    private Double maximumMarks;

    @NotBlank(message = "Academic year is mandatory")
    private String academicYear;

    @NotNull(message = "Semester is mandatory")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 12, message = "Semester must not exceed 12")
    private Integer semester;
}