package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String username;
    public String phonenumber;
    
    public String fullname;
    public String age;
    public String gender;
    public String position;


    @OneToOne(mappedBy = "userModel", cascade = CascadeType.PERSIST)
    @JsonBackReference
    public LoginModel loginModel;

}
