import api from "./api";

export const getAllUsers =
    () => api.get("/users/all");

export const deleteUser = id =>
    api.delete(`/users/${id}`);


