package com.example.deneme.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.deneme.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
