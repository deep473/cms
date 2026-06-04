package com.lms.cms.controller;

import com.lms.cms.dto.CourseRequestDTO;
import com.lms.cms.dto.CourseResponseDTO;
import com.lms.cms.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseResponseDTO createCourse(@Valid @RequestBody CourseRequestDTO course){
        return courseService.createCourse(course);
    }

    @GetMapping("/{id}")
    public CourseResponseDTO getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @GetMapping
    public List<CourseResponseDTO> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PatchMapping("/{id}")
    public CourseResponseDTO updateCoursePartially(@PathVariable Long id, @RequestBody CourseRequestDTO updatedCourse){
        return courseService.updateCoursePartially(id, updatedCourse);
    }

    @PutMapping("/{id}")
    public CourseResponseDTO updateCourseCompletely(@PathVariable Long id, @RequestBody CourseRequestDTO updatedCourse){
        return courseService.updateCourseCompletely(id, updatedCourse);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id){
        return courseService.deleteCourse(id);
    }

}
