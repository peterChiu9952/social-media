package com.peter.post;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostDataAccessService implements PostDao{

    private final PostRepository postRepository;

    public PostDataAccessService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> selectAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> selectPostByPostId(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public void insertPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public boolean existsPostByPostId(Long postId) {
        return postRepository.existsPostByPostId(postId);
    }
}
