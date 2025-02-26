package com.example.CouseAPI.modules.course.services;

import com.example.CouseAPI.exception.CourseFoundException;
import com.example.CouseAPI.modules.course.CourseEntity;
import com.example.CouseAPI.modules.course.CourseRepository;
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

}
