package com.example.backend.controller;

import com.example.backend.model.LoginModel;
import com.example.backend.service.LoginService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    public LoginService loginService;

    @GetMapping("/get")
    @CrossOrigin(origins ="http://localhost:3000")
    
    public List<LoginModel> getData() {
        return loginService.getLoginData();
    }

    @PostMapping("/post")
    @CrossOrigin(origins = "http://localhost:3000")
    public LoginModel postData(@RequestBody LoginModel data) {
        return loginService.postLoginData(data);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteData(@PathVariable int id) {
        loginService.deleteLoginData(id);
        return "Deleted Successfully";
    }

    @PutMapping("/put/{id}")
    public LoginModel putData(@PathVariable int id,@RequestBody LoginModel data) {
        return loginService.putLoginData(id,data);
    }

    @GetMapping("/users")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoginModel> validateUser(@RequestParam("username") String username,@RequestParam("password") String password){
        return loginService.validateUser(username,password);
    }
    @GetMapping("/users/search")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoginModel> checkUser(@RequestParam("username") String username,@RequestParam("phonenumber") String phonenumber){
        return loginService.checkUser(username,phonenumber);
    }
    
}
