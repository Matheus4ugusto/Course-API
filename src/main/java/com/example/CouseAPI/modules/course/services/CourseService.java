package com.example.CouseAPI.modules.course.services;

import com.example.CouseAPI.exception.CourseFoundException;
import com.example.CouseAPI.exception.CourseNotFoundException;
import com.example.CouseAPI.modules.course.CourseEntity;
import com.example.CouseAPI.modules.course.CourseRepository;
import com.example.CouseAPI.modules.course.DTO.UpdateCourseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity createCourse(CourseEntity courseEntity){

        this.courseRepository
                .findByNameOrId(courseEntity.getName(), courseEntity.getId())
                .ifPresent(course -> {
                    throw  new CourseFoundException("Este Curso já existe");
                });

        return this.courseRepository.save(courseEntity);
    }

    public List<CourseEntity> getCourses(){
        var courses = this.courseRepository.findAll();
        return courses;
    }

    public CourseEntity updateCourse(UpdateCourseRequestDTO updateCourseRequestDTO){
        var course = this.courseRepository
                .findByNameOrId(String.valueOf(updateCourseRequestDTO.name()), updateCourseRequestDTO.id())
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));
        updateCourseRequestDTO.name().ifPresent(course::setName);
        updateCourseRequestDTO.category().ifPresent(course::setCategory);
        return this.courseRepository.save(course);
    }

}
