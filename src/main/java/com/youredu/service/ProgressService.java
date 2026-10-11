package com.youredu.service;

import com.youredu.domain.lesson.Lesson;
import com.youredu.domain.progress.Progress;
import com.youredu.domain.user.User;
import org.springframework.stereotype.Service;
import com.youredu.repository.LessonRepository;
import com.youredu.repository.ProgressRepository;
import com.youredu.repository.UserRepository;

import java.util.List;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    public ProgressService(ProgressRepository progressRepository, UserRepository userRepository,
                           LessonRepository lessonRepository) {

        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    public Progress updateProgress(Long userId, Long lessonId, String status) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Урок не найден"));

        Progress progress = progressRepository.findByUserIdAndLessonId(userId, lessonId).orElse(new Progress());

        progress.setUser(user);
        progress.setLesson(lesson);
        progress.setStatus(status);

        return progressRepository.save(progress);
    }

    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserId(userId);
    }
}