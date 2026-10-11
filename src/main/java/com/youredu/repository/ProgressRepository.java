package com.youredu.repository;

import com.youredu.domain.progress.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    List<Progress> findByUserId(Long userId);

    Optional<Progress> findByUserIdAndLessonId(Long userId, Long lessonId);
}