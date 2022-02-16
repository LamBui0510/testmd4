package com.example.test.repository;

import com.example.test.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsersRepo extends JpaRepository<Users,Long> {
    @Query(value = " select u from Users u where u.userName like concat('%' ,:name, '%')")
    Page<Users> findAllByName(@Param("name") String name, Pageable pageable);
}
