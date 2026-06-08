package com.lms.cms.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponseDTO(
        LocalDateTime timestamp,
        int status,
        String error,
        Map<String, String> fieldErrors,
        String path
) { }