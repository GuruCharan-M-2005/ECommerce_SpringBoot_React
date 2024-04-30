package com.example.backend.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.LoginModel;
import com.example.backend.model.UserModel;
import com.example.backend.repository.LoginRepository;
import com.example.backend.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    public LoginRepository loginRepository;

    @Autowired
    public UserRepository userRepository;


    public List<UserModel> getUserData(){
        return userRepository.findAll();
    }
    public UserModel postUserData(UserModel data){
        return userRepository.save(data);
    }
    public void deleteUserData(int id){
        UserModel k=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id: " + id)) ;
        LoginModel lm=k.getLoginModel();
        if(lm!=null){
            int m=lm.getId();
            loginRepository.deleteById(m);
        }
        int n=k.getId();
        userRepository.deleteById(n);
    }
    public UserModel putUserData(int id,UserModel data){
        UserModel k=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id: " + id)) ;
        k.setUsername(data.getUsername());
        k.setPhonenumber(data.getPhonenumber());
        return userRepository.save(k);
    }

    public void deleteLoginDataByGender(String gender) {
        List<UserModel> usersToDelete = userRepository.findByGender(gender);
        for (UserModel user : usersToDelete) {
            LoginModel loginModel = user.getLoginModel();
            if (loginModel != null) {
                loginRepository.deleteById(loginModel.getId());
            }
        }
    }

}
