package com.lms.cms.service;

import com.lms.cms.entity.Course;
import com.lms.cms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course){
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCoursePartially(Long id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id).get();

        if(updatedCourse.getName() != null)
            existingCourse.setName(updatedCourse.getName());

        if(updatedCourse.getDescription() != null)
            existingCourse.setDescription(updatedCourse.getDescription());

        if(updatedCourse.getPrice() != null)
            existingCourse.setPrice(updatedCourse.getPrice());

        return courseRepository.save(existingCourse);
    }

    public Course updateCourseCompletely(Long id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id).get();

        existingCourse.setName(updatedCourse.getName());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setPrice(updatedCourse.getPrice());

        return courseRepository.save(existingCourse);
    }

    public String deleteCourse(Long id) {
        courseRepository.deleteById(id);
        return "Course deleted successfully!";
    }

}
