import request from "../request";

const search = (parameters) => {
    return request({
        url: `/account${parameters}`,
        method: "GET",
    })
}

const insert = (data) => {
    return request({
        url: `/account`,
        method: "POST",
        data: data
    })
}

const AccountService = {
    search,
    insert,
}

export default AccountService;