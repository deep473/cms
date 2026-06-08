package com.lms.cms.service;

import com.lms.cms.dto.CourseRequestDTO;
import com.lms.cms.dto.CourseResponseDTO;
import com.lms.cms.entity.Course;
import com.lms.cms.exception.CourseNotFoundException;
import com.lms.cms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {
        Course course = mapToEntity(courseRequestDTO);
        Course savedCourse = courseRepository.save(course);

        return mapToResponseDTO(savedCourse);
    }

    public CourseResponseDTO getCourseById(Long id) {
        Course course = findCourseOrThrow(id);
        return mapToResponseDTO(course);
    }

    public List<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public CourseResponseDTO updateCoursePartially(Long id, CourseRequestDTO updatedCourse) {
        Course existingCourse = findCourseOrThrow(id);

        if (updatedCourse.name() != null) {
            existingCourse.setName(updatedCourse.name());
        }

        if (updatedCourse.description() != null) {
            existingCourse.setDescription(updatedCourse.description());
        }

        if (updatedCourse.price() != null) {
            existingCourse.setPrice(updatedCourse.price());
        }

        Course savedCourse = courseRepository.save(existingCourse);

        return mapToResponseDTO(savedCourse);
    }

    public CourseResponseDTO updateCourseCompletely(Long id, CourseRequestDTO updatedCourse) {
        Course existingCourse = findCourseOrThrow(id);

        existingCourse.setName(updatedCourse.name());
        existingCourse.setDescription(updatedCourse.description());
        existingCourse.setPrice(updatedCourse.price());

        Course savedCourse = courseRepository.save(existingCourse);

        return mapToResponseDTO(savedCourse);
    }

    public void deleteCourse(Long id) {
        Course existingCourse = findCourseOrThrow(id);
        courseRepository.delete(existingCourse);
    }

    private Course findCourseOrThrow(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(
                        "Course not found with id: " + id
                ));
    }

    private Course mapToEntity(CourseRequestDTO dto) {
        Course course = new Course();
        course.setName(dto.name());
        course.setDescription(dto.description());
        course.setPrice(dto.price());
        return course;
    }

    private CourseResponseDTO mapToResponseDTO(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getPrice()
        );
    }
}