package com.example.deneme.controllers;

import com.example.deneme.entities.Post;
import com.example.deneme.request.PostCreateRequest;
import com.example.deneme.request.PostUpdateRequest;
import com.example.deneme.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;

    }
    @GetMapping
    @Transactional
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {

        return postService.getAllPosts(userId);

    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {
        return postService.createOnePost(newPostRequest);

    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId) {
        return postService.getOnePostById(postId);
    }
    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId,@RequestBody PostUpdateRequest updatePost){
        return postService.updateOnePostById(postId,updatePost);
    }


    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        // Belirtilen ID'ye sahip kullanıcıyı siliyoruz.
        postService.deletePostById(postId);
    }
}