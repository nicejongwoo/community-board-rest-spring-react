import request from "../request";

const signup = (data) => {
    return request({
        url: "/auth/signup",
        method: "POST",
        data: data
    })
}

const login = (data) => {
    return request({
        url: "/auth/login",
        method: "POST",
        data: data
    })
}

const AuthService = {
    signup,
    login,
}

export default AuthService;