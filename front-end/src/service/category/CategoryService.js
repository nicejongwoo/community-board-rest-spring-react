import request from "../request";

const getList = (parameters) => {
    return request({
        url: `/category?${parameters}`,
        method: "GET"
    });
}

const CategoryService = {
    getList,
}

export default CategoryService
