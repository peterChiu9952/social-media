package com.peter.comment;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
@CrossOrigin(origins = "http://localhost:5173/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponse> getComments() {
        return commentService.getAllComments();
    }

    @GetMapping("{postId}/posts")
    public List<CommentResponse> getCommentByPostId(@PathVariable long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping
    public void createComment(@RequestBody @Valid CommentRequest request, @RequestHeader("Authorization") String token) {
        commentService.createComment(request, token);
    }
}
