package com.springboot.jobportal.controller;

import com.springboot.jobportal.entity.Users;
import com.springboot.jobportal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/addUser")
    public Users postUser(@RequestBody Users users){
        return usersService.addUser(users);
    }

    @GetMapping("allUsers")
    public List<Users> getUsers(){
        return usersService.allUsers();
    }
}
