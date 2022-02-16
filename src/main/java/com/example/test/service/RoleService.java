package com.example.test.service;

import com.example.test.model.Role;
import com.example.test.repository.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements IRoleService {
    @Autowired
    IRoleRepo roleRepo;
    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }
}
