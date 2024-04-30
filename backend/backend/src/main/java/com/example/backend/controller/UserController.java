package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.UserModel;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/user")

public class UserController {
    
    @Autowired
    public UserService userService;

    @GetMapping("/get")
    @CrossOrigin(origins = {"http://localhost:3000/login","http://localhost:3000/signup"})
    public List<UserModel> getData() {
        return userService.getUserData();
    }

    @PostMapping("/post")
    @CrossOrigin(origins = {"http://localhost:3000/login","http://localhost:3000/signup"})
    public UserModel postData(@RequestBody UserModel data) {
        return userService.postUserData(data);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteData(@PathVariable int id) {
        userService.deleteUserData(id);
        return "Deleted Successfully";
    }
    
    @DeleteMapping("/delete/gender/{gender}")
    public String deleteDataByGender(@PathVariable String gender) {
        userService.deleteLoginDataByGender(gender);
        return "Deleted Successfully";
    }

    @PutMapping("/put/{id}")
    public UserModel putData(@PathVariable int id,@RequestBody UserModel data) {
        return userService.putUserData(id,data);
    }

}
