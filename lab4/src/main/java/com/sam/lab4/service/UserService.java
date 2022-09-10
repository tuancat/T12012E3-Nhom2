package com.sam.lab4.service;

import com.sam.lab4.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public void add(User user);

    public List<User> findAll();

    public User findById(Long id);
}
