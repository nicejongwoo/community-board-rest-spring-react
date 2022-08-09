import request from "../request";

const signup = (data) => {
    return request({
        url: "/auth/signup",
        method: "POST",
        data: data
    })
}

const AuthService = {
    signup,

}

export default AuthService;