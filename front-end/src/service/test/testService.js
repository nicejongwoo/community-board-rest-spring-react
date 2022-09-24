import request from "../request";


const search = (parameters) => {

    // console.log("====getList====", parameters);
    return request({
        url: `/test${parameters}`,
        method: "GET",
    })
}

const TestService = {
    search,
}

export default TestService;