import requset from "./https"

export const getCommentsByPostId = (postId) => {
    return requset("get", `/api/v1/comments/${postId}/posts`);
};

export const createComment = (postId, content) => {
    return requset("post", "/api/v1/comments", {
        postId: postId,
        content: content
    });
};