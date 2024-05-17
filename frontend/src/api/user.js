import requset from "./https"

export const register = (username, email, password, coverImage, biography) => {
    return requset("post", "/api/v1/users", {
        username: username,
        email: email,
        password: password,
        coverImage: coverImage,
        biography: biography
    });
};

export const getUser = (userId) => {
    return requset("post", `/api/v1/users/${userId}`);
};