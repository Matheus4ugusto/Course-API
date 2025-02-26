package com.example.CouseAPI.modules.course.DTO;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record CreateCourseRequestDTO(Optional<String> name, Optional<String> category, UUID id, boolean active, LocalDateTime created_at, LocalDateTime updated_at) {
}
