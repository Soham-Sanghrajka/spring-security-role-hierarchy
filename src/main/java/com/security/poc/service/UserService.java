package com.security.poc.service;

import com.security.poc.entity.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
<<<<<<< HEAD
    User getUserById(Long id);
=======

    User findById(Long userId);
>>>>>>> 9465a732f88077dfa536a11a857b48420a2f4339
}
