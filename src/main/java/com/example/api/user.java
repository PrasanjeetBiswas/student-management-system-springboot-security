// package com.example.api;

// import java.util.List;

// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PutMapping;



// @RestController
// public class user {
// JdbcTemplate jdbcT;
// userRepo userRepo;
// user(JdbcTemplate jdbcT,userRepo userRepo){
//     this.jdbcT = jdbcT;
//     this.userRepo = userRepo;
// }


// @PostMapping("/addUser")
// public String addUser(@RequestBody UserModel user){
//     String sql = "INSERT INTO userdata (user_name, email, user_password) Values (?,?,?)";
//     jdbcT.update(sql, user.getName(), user.getEmail(), user.getPassword());
//     return "User added successfully";
// }


// @PostMapping("/addUser2")
// public String postMethodName(@RequestBody UserModel user) {
//     userRepo.save(user);
//     return "POST request processed successfully";
// }


// @GetMapping("/getUser/{id}")
// public UserModel getUserById(@PathVariable Long id) {
//     return userRepo.findById(id).orElse(null);
// }

// @GetMapping("/getAllUsers")
// public List<UserModel> getAllUsers() {
//     return userRepo.findAll();
// }

// @PutMapping("/updateUserName/{id}")
// public String putMethodName(@PathVariable String id, @RequestBody String entity) {
//     //TODO: process PUT request
//     userRepo.findById(Long.parseLong(id)).ifPresent(user -> {
//         user.setName(entity);
//         userRepo.save(user);
//     });
//     return "Update request processed successfully";
// }

// @PutMapping("/updateUser/{id}")
// public String putMethod(@PathVariable String id, @RequestBody UserModel entity) {
//     //TODO: process PUT request
//     userRepo.findById(Long.parseLong(id)).ifPresent(user -> {
//         if(entity.getName() != null) {
//             user.setName(entity.getName());
//         }
//         if(entity.getEmail() != null) {
//             user.setEmail(entity.getEmail());
//         }
//         if(entity.getPassword() != null) {
//             user.setPassword(entity.getPassword());
//         }
//         userRepo.save(user);
//     });
//     return "Update request processed successfully";
// }

// @DeleteMapping("/deleteUser/{id}")
// public String deleteMethod(@PathVariable Long id){
//     userRepo.deleteById(id);
//     return "Deleted User";
// }
// }