import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080"
});

API.interceptors.request.use((req) => {

    const token = localStorage.getItem("token");

    if (token) {
        req.headers.Authorization = `Bearer ${token}`;
    }

    return req;
});

API.interceptors.response.use(
    (response) => response,
    (error) => {

        if (error.response && error.response.status === 403) {
            alert("Session expired. Please login again.");
            localStorage.removeItem("token");
            window.location.href = "/";
        }

        return Promise.reject(error);
    }
);

export default API;