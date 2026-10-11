package com.youredu.service;

import com.youredu.domain.course.Course;
import com.youredu.domain.user.User;
import org.springframework.stereotype.Service;
import com.youredu.repository.CourseRepository;
import com.youredu.repository.UserRepository;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository,
                         UserRepository userRepository) {

        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(Long authorId, Course course) {

        User author = userRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Автор не найден"));

        course.setAuthor(author);

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {

        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Курс не найден"));
    }

    public List<Course> getCoursesByAuthor(Long authorId) {
        return courseRepository.findByAuthorId(authorId);
    }
}