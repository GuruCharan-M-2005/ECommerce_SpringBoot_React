package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.model.AppModel;

public interface AppRepository extends JpaRepository <AppModel,Integer> {

    @Query("select d from AppModel d where d.type=:type")
    List<AppModel> findByType(String type);
    
    @Query("select d from AppModel d where d.count>0")
    List<AppModel> forCartPage();


    @Query("SELECT d FROM AppModel d WHERE d.description LIKE %:search%")
    List<AppModel> searchByFilter(String search);
} 
