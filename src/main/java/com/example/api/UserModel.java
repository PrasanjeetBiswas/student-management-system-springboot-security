// package com.example.api;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;

// @Entity
// public class UserModel {
// private String name;
// private String email;
// private String password;

// public String getName() {
//     return name;
// }
// public void setName(String name) {
//     this.name = name;
// }
// public String getEmail() {
//     return email;
// }
// public void setEmail(String email) {
//     this.email = email;
// }
// public String getPassword() {
//     return password;
// }
// public void setPassword(String password) {
//     this.password = password;
// }

// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long id;
// @Column(name = "user_name",nullable = false)
// private String name;
// @Column(name = "user_email", unique = true,nullable = false)
// private String email;
// @Column(name = "user_password",nullable = false)
// private String password;

// public UserModel() {
// }

// @Column(name = "user_dept",nullable = false)
// private String dept;

// public String getName() {
//     return name;
// }
// public void setName(String name) {
//     this.name = name;
// }

// public String getEmail() {
//     return email;
// }
// public void setEmail(String email) {
//     this.email = email;
// }
// public String getPassword() {
//     return password;
// }
// public void setPassword(String password) {
//     this.password = password;
// }

// }
