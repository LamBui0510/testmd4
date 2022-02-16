package com.example.test.service;

import com.example.test.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsersService{
     Page<Users> findAll(Pageable pageable);
     void save(Users users);
     void delete(long id);
     Users findById(long id);
     Page<Users> findUsersByName(String name, Pageable pageable);
}
