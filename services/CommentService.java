package com.example.deneme.services;


import com.example.deneme.entities.Comment;
import com.example.deneme.entities.Post;
import com.example.deneme.entities.User;
import com.example.deneme.repos.CommentRepository;
import com.example.deneme.request.CommentCreateRequest;
import com.example.deneme.request.CommentUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    private UserService userService;
    private PostService postService;


    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }
        else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }
        else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }
        else{
            return commentRepository.findAll();
        }

    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest request) {
       User user = userService.getOneUserById(request.getUserId());
       Post post = postService.getOnePostById(request.getPostId());
       if(user!=null && post!=null){
           Comment commentToSave = new Comment();
           commentToSave.setId(request.getId());
           commentToSave.setPost(post);
           commentToSave.setUser(user);
           commentToSave.setText(request.getText());
           return commentRepository.save(commentToSave);
       }
       else
        return null;
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);
        }
        else return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

