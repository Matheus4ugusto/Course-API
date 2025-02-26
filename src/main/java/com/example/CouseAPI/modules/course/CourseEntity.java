package com.example.CouseAPI.modules.course;

import com.example.CouseAPI.modules.course.DTO.CreateCourseRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String category;

    @NotNull()
    private boolean active = true;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public static CourseEntity convertToEntity(CreateCourseRequestDTO createCourseRequestDTO) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(String.valueOf(createCourseRequestDTO.name()));
        courseEntity.setCategory(String.valueOf(createCourseRequestDTO.category()));
        return courseEntity;
    }
}
