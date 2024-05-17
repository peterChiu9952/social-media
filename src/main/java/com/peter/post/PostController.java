package com.peter.post;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@CrossOrigin(origins = "http://localhost:5173/")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public void createPost(@RequestBody PostRequest request, @RequestHeader("Authorization") String token) {
        postService.createPost(request, token);
    }

    @DeleteMapping("{postId}")
    public void deletePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) {
        postService.deletePost(postId, token);
    }

    @PutMapping("{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody PostRequest request, @RequestHeader("Authorization") String token) {
        postService.updatePost(postId, request, token);
    }
}
