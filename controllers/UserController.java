package com.example.deneme.controllers;

import java.util.List;
import java.util.Optional;

import com.example.deneme.entities.User;
import com.example.deneme.repos.UserRepository;
import com.example.deneme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        // Veri tabanından tüm kullanıcıları getiriyoruz.
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        // Yeni bir kullanıcı oluşturuyoruz.
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        // Belirtilen ID'ye sahip kullanıcıyı getiriyoruz. Eğer kullanıcı bulunamazsa, null döndürülüyor.
        return userService.getOneUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User newUser) {
       return userService.updateOneUser(userId,newUser);
        }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        // Belirtilen ID'ye sahip kullanıcıyı siliyoruz.
        userService.deleteById(userId);
    }
}
