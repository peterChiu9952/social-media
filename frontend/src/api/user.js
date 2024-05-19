import requset from "./https"

export const register = (username, email, password, biography) => {
    return requset("post", "/api/v1/users", {
        username: username,
        email: email,
        password: password,
        biography: biography
    });
};

export const getUser = (userId) => {
    return requset("post", `/api/v1/users/${userId}`);
};