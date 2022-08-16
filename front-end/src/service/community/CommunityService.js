import request from "../request";

const getList = (parameters) => {
    return request({
        url: `/community${parameters}`,
        method: "GET"
    });
}

const CommunityService = {
    getList,

}

export default CommunityService;