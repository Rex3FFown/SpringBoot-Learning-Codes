package com.example.deneme.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.deneme.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long>{
}
