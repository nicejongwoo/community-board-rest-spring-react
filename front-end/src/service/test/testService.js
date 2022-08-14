import request from "../request";


const getList = (parameters) => {

    console.log("====getList====", parameters);
    return request({
        url: `/test${parameters}`,
        method: "GET",
    })
}

const TestService = {
    getList,

}

export default TestService;