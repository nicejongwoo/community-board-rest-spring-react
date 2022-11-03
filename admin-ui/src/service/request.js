import React from "react";
import axios from "axios";

const request = (options) => {

    const client = axios.create({
        baseURL: "http://localhost:8080/api",
        headers: {
            'Content-Type': 'application/json',
        },
        withCredentials: true
    })

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

    client.interceptors.request.use(function (config) {
        const token = JSON.parse(sessionStorage.getItem("token"));

        config.headers.Authorization = token ? `Bearer ${token.accessToken}` : "";
        return config;
    });

    return client(options).then(onSuccess).catch(onError);

}

export default request;