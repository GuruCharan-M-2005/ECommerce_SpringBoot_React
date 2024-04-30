package com.example.backend.controller;

import com.example.backend.model.AppModel;
import com.example.backend.service.AppService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;






@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    public AppService appService;

    @GetMapping("/get")
    public List<AppModel> getData() {
        return appService.getAppData();
    }
    
    @GetMapping("/getByPage/{page}")
    public ResponseEntity<Page<AppModel>> getDataByPage(
        @PathVariable int page,
        @RequestParam(defaultValue = "10") int size ) {
        Page<AppModel> pages= appService.getAppDataByPage(page,size);
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/getbyid/{id}")
    public AppModel getById(@PathVariable int id) {
        return appService.getSingleAppData(id);
    }
    

    @PostMapping("/post")
    public AppModel postData(@RequestBody AppModel data) {
        return appService.postAppData(data);
    }

    @PostMapping("/postall")
    public List<AppModel> postManyData(@RequestBody List<AppModel> data) {
        return appService.postManyAppData(data);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteData(@PathVariable int id) {
        appService.deleteAppData(id);
        return "Deleted Successfully";
    }

    @PutMapping("/put/{id}")
    public AppModel putData(@PathVariable int id,@RequestBody AppModel data) {
        return appService.putAppData(id,data);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/type/{type}")
    public List<AppModel> getByAll(@PathVariable String type) {
        return appService.getByType(type);

    }
    @GetMapping("/cart")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<AppModel> getPageforCart() {
        return appService.getCartPage();

    }

    @PutMapping("cart/{id}/inc")
    @CrossOrigin(origins = "http://localhost:3000")
    public AppModel AddToCartName(@PathVariable int id, @RequestBody AppModel entity) {
        return appService.increaseCount(id,entity);
    }
    
    @PutMapping("cart/{id}/dec")
    @CrossOrigin(origins = "http://localhost:3000")
    public AppModel deleteFromCartName(@PathVariable int id, @RequestBody AppModel entity) {
        return appService.decreaseCount(id,entity);
        
    }
    @PutMapping("cart/{id}/remove")
    @CrossOrigin(origins = "http://localhost:3000")
    public AppModel removeFromCartName(@PathVariable int id, @RequestBody AppModel entity) {
        return appService.removefromCart(id,entity);
        
    }

    @GetMapping("/filtered-items")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<AppModel> getMethodName(@RequestParam String searchQuery) {
        return appService.searchAllItems(searchQuery);
    }
    
 }
