package com.example.CouseAPI.modules.course.services;

import com.example.CouseAPI.exception.CategoryNotInformedException;
import com.example.CouseAPI.exception.CourseFoundException;
import com.example.CouseAPI.exception.CourseNotFoundException;
import com.example.CouseAPI.exception.NameNotInformedException;
import com.example.CouseAPI.modules.course.CourseEntity;
import com.example.CouseAPI.modules.course.CourseRepository;
import com.example.CouseAPI.modules.course.DTO.CreateCourseRequestDTO;
import com.example.CouseAPI.modules.course.DTO.UpdateCourseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.CouseAPI.modules.course.CourseEntity.convertToEntity;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity createCourse(CreateCourseRequestDTO createCourseRequestDTO) {

        CourseEntity courseEntity = convertToEntity(createCourseRequestDTO);

        createCourseRequestDTO.name().ifPresent(name -> courseEntity.setName(name.trim()));
        createCourseRequestDTO.category().ifPresent(category -> courseEntity.setCategory(category.trim()));

        if (courseEntity.getName().isEmpty() || courseEntity.getName().isBlank()){
            throw new NameNotInformedException("O nome do curso não foi informado");
        }
        if (courseEntity.getCategory().isEmpty() || courseEntity.getCategory().isBlank()){
            throw new CategoryNotInformedException("A categoria do curso não foi informada");
        }

        this.courseRepository
                .findByName(courseEntity.getName())
                .ifPresent(course -> {
                    throw new CourseFoundException("Este Curso já existe");
                });

        return this.courseRepository.save(courseEntity);
    }

    public List<CourseEntity> getCourses(){
        var courses = this.courseRepository.findAll();
        return courses;
    }

    public CourseEntity updateCourse(UpdateCourseRequestDTO updateCourseRequestDTO, UUID id){
        var course = this.courseRepository
                .findByNameOrId(String.valueOf(updateCourseRequestDTO.name()), id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));
        updateCourseRequestDTO.name().ifPresent(name -> course.setName(name.trim()));
        updateCourseRequestDTO.category().ifPresent(category -> course.setCategory(category.trim()));
        return this.courseRepository.save(course);
    }

    public void deleteCourse(UUID id){
        this.courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));
        this.courseRepository.deleteById(id);
    }

    public CourseEntity togleActive(UUID id){
        var course = this.courseRepository
                .findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));
        course.setActive(!course.isActive());
        return this.courseRepository.save(course);
    }

}
