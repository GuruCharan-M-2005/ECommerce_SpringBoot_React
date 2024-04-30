package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.LoginModel;
import com.example.backend.model.UserModel;
import com.example.backend.repository.LoginRepository;
import com.example.backend.repository.UserRepository;

@Service
public class LoginService {
    @Autowired
    public LoginRepository loginRepository;

    @Autowired
    public UserRepository userRepository;


    public List<LoginModel> getLoginData(){
        return loginRepository.findAll();
    }
    public LoginModel postLoginData(LoginModel data){
        return loginRepository.save(data);
    }
    public void deleteLoginData(int id){
        LoginModel k=loginRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id: " + id)) ;
        UserModel um=k.getUserModel();
        if(um!=null){
            int m=um.getId();
            userRepository.deleteById((m));
        }
        int n=k.getId();
        loginRepository.deleteById(n);
    }
    public LoginModel putLoginData(int id,LoginModel data){
        LoginModel k=loginRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id: " + id)) ;
        k.setUsername(data.getUsername());
        k.setPassword(data.getPassword());
        k.setPhonenumber(data.getPhonenumber());
        return loginRepository.save(k);
    }

    public List<LoginModel> validateUser(String username,String password){
        return  loginRepository.findByUsernameAndPassword(username,password);
    }
    
    public List<LoginModel> checkUser(String username,String phonenumber){
        return  loginRepository.findByUsernameAndPhone(username,phonenumber);
    }
}
