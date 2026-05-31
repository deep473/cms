package com.lms.cms.dto;

public record CourseResponseDTO(
        Long id,
        String name,
        String description,
        Integer price
) {
}
