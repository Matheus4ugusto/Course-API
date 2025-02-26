package com.example.CouseAPI.modules.course.DTO;

import java.util.Optional;
import java.util.UUID;

public record UpdateCourseRequestDTO(Optional<String> name, Optional<String> category) {
}
