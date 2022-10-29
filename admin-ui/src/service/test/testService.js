import request from "../request";


const search = (parameters) => {

    // console.log("====getList====", parameters);
    return request({
        url: `/test${parameters}`,
        method: "GET",
    })
}

const insert = (data) => {
    return request({
        url: `/test`,
        method: "POST",
    })
}

const TestService = {
    search,
    insert,
}

export default TestService;