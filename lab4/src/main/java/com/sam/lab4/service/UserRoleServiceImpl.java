package com.sam.lab4.service;

import com.sam.lab4.model.UserRole;
import com.sam.lab4.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }
}
