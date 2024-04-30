package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.model.LoginModel;

public interface LoginRepository extends JpaRepository <LoginModel,Integer>{

    @Query("select l from LoginModel l where l.username=:username and l.password=:password")
    public List<LoginModel> findByUsernameAndPassword(String username,String password);

    @Query("select l from LoginModel l where l.username=:username and l.phonenumber=:phonenumber")
    public List<LoginModel> findByUsernameAndPhone(String username,String phonenumber);
} 

