package com.example.backend.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.backend.model.AppModel;
import com.example.backend.repository.AppRepository;

@Service
public class AppService {
    
    @Autowired
    public AppRepository appRepository;

    public AppModel getSingleAppData(int id){
        return appRepository.findById(id).orElseThrow(()->new Error("Product Not Found"));
    }
    public List<AppModel> getAppData(){
        return appRepository.findAll();
    }
    public Page<AppModel> getAppDataByPage(int page,int size){
        Pageable pageable=PageRequest.of(page, size);
        return appRepository.findAll(pageable);
    }
    public AppModel postAppData(AppModel data){
        return appRepository.save(data);
    }
    public List<AppModel> postManyAppData(List<AppModel> datas){
        return appRepository.saveAll(datas);
    }
    public void deleteAppData(int id){
        appRepository.deleteById(id);
    }

    public AppModel putAppData(int id,AppModel data){
        AppModel k=appRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found with id: " + id)) ;
        k.setType(data.getType());
        k.setDescription(data.getDescription());
        k.setPrice(data.getPrice());
        k.setCount(data.getCount());
        return appRepository.save(k);
    }
    public AppModel increaseCount(int id,AppModel data){
        AppModel k=appRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found with id: " + id)) ;
        k.setType(data.getType());
        k.setDescription(data.getDescription());
        k.setPrice(data.getPrice());
        k.setCount(data.getCount()+1);
        return appRepository.save(k);
    }
    public AppModel decreaseCount(int id,AppModel data){
        AppModel k=appRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found with id: " + id)) ;
        k.setType(data.getType());
        k.setDescription(data.getDescription());
        k.setPrice(data.getPrice());
        k.setCount(data.getCount()-1);
        return appRepository.save(k);
    }
    public AppModel removefromCart(int id,AppModel data){
        AppModel k=appRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found with id: " + id)) ;
        k.setType(data.getType());
        k.setDescription(data.getDescription());
        k.setPrice(data.getPrice());
        k.setCount(0);
        return appRepository.save(k);
    }



    public List<AppModel> getByType(String s){
        return appRepository.findByType(s);
    }

    public List<AppModel> getCartPage(){
        return appRepository.forCartPage();
    }
    public List<AppModel> searchAllItems(String search){
        return appRepository.searchByFilter(search);
    }
}