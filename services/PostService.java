package com.example.deneme.services;

import com.example.deneme.entities.Post;
import com.example.deneme.entities.User;
import com.example.deneme.repos.PostRepository;
import com.example.deneme.request.PostCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;
   public PostService(PostRepository postRepository, UserService userService) {
       this.postRepository = postRepository;
       this.userService = userService;
   }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if(userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
       User user = userService.getOneUser(newPostRequest.getUserId());
       if(user==null){
           return null;
       }
       Post toSave=new Post();
       toSave.setId(newPostRequest.getId());
       toSave.setText(newPostRequest.getText());
       toSave.setUser(user);
       toSave.setTitle(newPostRequest.getTitle());
       return postRepository.save(toSave);
    }
}
