import axios from "axios";

const client = axios.create({
    baseURL: "http://localhost:8080/api"
})

const request = (options) => {
    const onSuccess = (response) => {
        console.debug("Request Successful:: ", response);
        return response.data;
    }

    const onError = (error) => {
        console.error("Request Failed:: ", error.config);

        if (error.response) {
            console.error("Status:: ", error.response.status);
            console.error("Data:: ", error.response.data);
            console.error("Headers:: ", error.response.headers);
        } else {
            console.error("Error Message:: ", error.message);
        }

        return Promise.reject(error.response || error.message);
    }

    return client(options).then(onSuccess).catch(onError);

}

export default request;