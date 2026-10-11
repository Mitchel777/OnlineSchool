package com.youredu.service;

import com.youredu.domain.course.Course;
import com.youredu.domain.lesson.Lesson;
import org.springframework.stereotype.Service;
import com.youredu.repository.CourseRepository;
import com.youredu.repository.LessonRepository;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository) {

        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    public Lesson createLesson(Long courseId, Lesson lesson) {

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Курс не найден"));

        lesson.setCourse(course);

        return lessonRepository.save(lesson);
    }

    public Lesson getLessonById(Long id) {

        return lessonRepository.findById(id).orElseThrow(() -> new RuntimeException("Урок не найден"));
    }

    public List<Lesson> getLessonsByCourse(Long courseId) {

        return lessonRepository.findByCourseIdOrderByPositionAsc(courseId);
    }

    public Lesson updateLesson(Long id, Lesson updatedLesson) {

        Lesson lesson = getLessonById(id);

        lesson.setTitle(updatedLesson.getTitle());
        lesson.setContent(updatedLesson.getContent());
        lesson.setPosition(updatedLesson.getPosition());

        return lessonRepository.save(lesson);
    }
}