package com.springboot.jobportal.service;

import com.springboot.jobportal.entity.Users;
import com.springboot.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users addUser(Users users){
        return usersRepository.save(users);
    }

    public List<Users> allUsers(){
        return usersRepository.findAll();
    }
}
