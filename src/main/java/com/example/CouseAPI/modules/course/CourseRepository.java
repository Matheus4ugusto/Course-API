package com.example.CouseAPI.modules.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

    Optional<CourseEntity> findByNameOrId(String name, UUID id);
    Optional<CourseEntity> findByName(String name);
    Optional<CourseEntity> findById(UUID id);
    List<CourseEntity> findAll();
    void deleteById(UUID id);
}
