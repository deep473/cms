package com.lms.cms.service;

import com.lms.cms.dto.CourseRequestDTO;
import com.lms.cms.dto.CourseResponseDTO;
import com.lms.cms.entity.Course;
import com.lms.cms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponseDTO createCourse(CourseRequestDTO course){
        Course createdCourse = courseRepository.save(mapToEntity(course));
        return mapToResponseDTO(createdCourse);
    }

    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).get();
        return mapToResponseDTO((course));
    }

    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();

        return courseList.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public CourseResponseDTO updateCoursePartially(Long id, CourseRequestDTO updatedCourse) {
        Course existingCourse = courseRepository.findById(id).get();

        if(updatedCourse.name() != null)
            existingCourse.setName(updatedCourse.name());

        if(updatedCourse.description() != null)
            existingCourse.setDescription(updatedCourse.description());

        if(updatedCourse.price() != null)
            existingCourse.setPrice(updatedCourse.price());

        Course savedCourse = courseRepository.save(existingCourse);
        return mapToResponseDTO(savedCourse);
    }

    public CourseResponseDTO updateCourseCompletely(Long id, CourseRequestDTO updatedCourse) {
        Course existingCourse = courseRepository.findById(id).get();

        existingCourse.setName(updatedCourse.name());
        existingCourse.setDescription(updatedCourse.description());
        existingCourse.setPrice(updatedCourse.price());

        Course savedCourse = courseRepository.save(existingCourse);
        return mapToResponseDTO(savedCourse);
    }

    public String deleteCourse(Long id) {
        courseRepository.deleteById(id);
        return "Course deleted successfully!";
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
