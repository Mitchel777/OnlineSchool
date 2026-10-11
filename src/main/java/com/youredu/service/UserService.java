package com.youredu.service;

import com.youredu.domain.user.User;
import org.springframework.stereotype.Service;
import com.youredu.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if(existingUser.isPresent()) {
            throw new RuntimeException("Пользователь уже существует");
        }

        return userRepository.save(user);
    }

    public User getById(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}