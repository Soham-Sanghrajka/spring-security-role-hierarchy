package com.security.poc.service.impl;

import com.security.poc.entity.User;
import com.security.poc.exception.ResourceNotFoundException;
import com.security.poc.repository.UserRepository;
import com.security.poc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
<<<<<<< HEAD
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return user;
    }
}
=======
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found with ID: "+ userId));
    }
}
>>>>>>> 9465a732f88077dfa536a11a857b48420a2f4339
