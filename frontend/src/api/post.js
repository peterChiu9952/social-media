import requset from "./https"

export const getPosts = () => {
    return requset("get", "/api/v1/posts");
};

export const createPost = (content) => {
    return requset("post", "/api/v1/posts", {
        content: content
    });
};

export const deletePost = (postId) => {
    return requset("delete", `/api/v1/posts/${postId}`);
};

export const updatePost = (postId, content) => {
    return requset("put", `/api/v1/posts/${postId}`, {
        content: content
    });
};