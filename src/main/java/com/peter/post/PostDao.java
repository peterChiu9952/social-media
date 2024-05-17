package com.peter.post;

import java.util.List;
import java.util.Optional;

public interface PostDao {
    List<Post> selectAllPosts();

    Optional<Post> selectPostByPostId(Long postId);
    void insertPost(Post post);
    void deletePost(Long postId);
    void updatePost(Post post);
    boolean existsPostByPostId(Long postId);
}
