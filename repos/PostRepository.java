package com.example.deneme.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.deneme.entities.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
