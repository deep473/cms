package com.lms.cms.dto;

import jakarta.validation.constraints.*;

public record CourseRequestDTO(

        @NotBlank(message = "Course name is required")
        @Size(min = 3, max = 100, message = "Name must be 3 to 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(max = 50, message = "Description cannot exceed 50 characters")
        String description,

        @NotNull(message = "Price is required")
        @Min(value = 0, message = "Price cannot be negative")
        Integer price
) { }

