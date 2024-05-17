package com.peter.post;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PostResponseMapper implements Function<Post, PostResponse> {

    @Override
    public PostResponse apply(Post post) {
        return new PostResponse(
                post.getPostId(),
                post.getUser().getUserId(),
                post.getUser().getUsername(),
                post.getContent(),
                post.getCreatedAt()
        );
    }
}
