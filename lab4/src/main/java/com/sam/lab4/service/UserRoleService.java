package com.sam.lab4.service;

import com.sam.lab4.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRoleService {
    public List<UserRole> findAll();
}
