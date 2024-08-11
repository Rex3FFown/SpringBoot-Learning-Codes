package com.example.deneme.services;

import com.example.deneme.entities.User;
import com.example.deneme.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            // Eğer kullanıcı varsa, bilgilerini güncelliyoruz.
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setUserSurname(newUser.getUserSurname());
            foundUser.setPassword(newUser.getPassword());
            return userRepository.save(foundUser);
        } else {
            // Eğer kullanıcı yoksa, null döndürüyoruz.
            return null;
        }
}

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
    }
