import axios from "axios";

const BASE_URL = "http://localhost:8080/users";

export const registerUser = user =>
  axios.post(`${BASE_URL}/register`, user);

export const loginUser = user =>
  axios.post(`${BASE_URL}/login`, user);

export const getCurrentUser = token =>
  axios.get(`${BASE_URL}/me`, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });