package com.example.CouseAPI.modules.course.controllers;

import com.example.CouseAPI.modules.course.CourseEntity;
import com.example.CouseAPI.modules.course.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CourseEntity courseEntity){
        try{
            var result = this.courseService.createCourse(courseEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        try {

            var result = this.courseService.getCourses();
            return ResponseEntity.status(HttpStatus.OK).body(result);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
