package com.example.test.service;

import com.example.test.model.Users;
import com.example.test.repository.IUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsersService implements IUsersService{
    @Autowired
    IUsersRepo usersRepo;
    @Override
    public Page<Users> findAll(Pageable pageable) {
        return usersRepo.findAll(pageable);
    }

    @Override
    public void save(Users users) {
        System.out.println("lam den đay");
        System.out.println("lam den đay");
        System.out.println("lam den đay");
        System.out.println("lam den đay");
    usersRepo.save(users);
    }

    @Override
    public void delete(long id) {
    usersRepo.deleteById(id);
    }

    @Override
    public Users findById(long id) {
        return usersRepo.findById(id).get();
    }

    @Override
    public Page<Users> findUsersByName(String name, Pageable pageable) {
        return usersRepo.findAllByName(name,pageable);
    }
}
