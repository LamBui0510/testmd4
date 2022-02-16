package com.example.test.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Users {
//    User(id,user,pass,fullname,phone,email,avatar
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @NotNull
//    @Size(min = 3, max = 125,message = "around 3 to 125 characters")
    private String userName;
//    @Size(min = 5, message = "more than 5 characters")
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String avatar;
    @ManyToOne
    private Role role;
}
