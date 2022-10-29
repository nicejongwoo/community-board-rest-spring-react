import request from "../request";

const options = () => {
    return request({
        url: `/category/options`,
        method: "GET"
    });
}

const getList = (parameters) => {
    return request({
        url: `/category?${parameters}`,
        method: "GET"
    });
}

const CategoryService = {
    options,
    getList,
}

export default CategoryService
