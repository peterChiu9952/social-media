import requset from "./https"

export const login = (username, password) => {
    return requset("post", "/api/v1/auth/login", {
        username: username,
        password: password
    })
};