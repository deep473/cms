package com.lms.cms.controller;

import com.lms.cms.dto.CourseRequestDTO;
import com.lms.cms.dto.CourseResponseDTO;
import com.lms.cms.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CourseResponseDTO> createCourse(
            @Valid @RequestBody CourseRequestDTO courseRequestDTO) {

        CourseResponseDTO createdCourse = courseService.createCourse(courseRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCoursePartially(
            @PathVariable Long id,
            @RequestBody CourseRequestDTO updatedCourse) {

        CourseResponseDTO updatedResponse =
                courseService.updateCoursePartially(id, updatedCourse);

        return ResponseEntity.ok(updatedResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourseCompletely(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDTO updatedCourse) {

        CourseResponseDTO updatedResponse =
                courseService.updateCourseCompletely(id, updatedCourse);

        return ResponseEntity.ok(updatedResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}