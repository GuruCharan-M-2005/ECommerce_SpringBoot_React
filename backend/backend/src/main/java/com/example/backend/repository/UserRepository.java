package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel,Integer> {
    @Query("select p from UserModel p where gender=:gender")
    public List<UserModel> findByGender(String gender);
} 