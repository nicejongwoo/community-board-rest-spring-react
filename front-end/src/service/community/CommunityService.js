import request from "../request";

const search = (parameters) => {
    return request({
        url: `/community${parameters}`,
        method: "GET"
    });
}

const CommunityService = {
    search,

}

export default CommunityService;