package com.example.deneme.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.deneme.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
