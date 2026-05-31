package com.lms.cms.controller;

import com.lms.cms.entity.Course;
import com.lms.cms.service.CourseService;
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
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PatchMapping("/{id}")
    public Course updateCoursePartially(@PathVariable Long id, @RequestBody Course updatedCourse){
        return courseService.updateCoursePartially(id, updatedCourse);
    }

    @PutMapping("/{id}")
    public Course updateCourseCompletely(@PathVariable Long id, @RequestBody Course updatedCourse){
        return courseService.updateCourseCompletely(id, updatedCourse);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id){
        return courseService.deleteCourse(id);
    }

}
