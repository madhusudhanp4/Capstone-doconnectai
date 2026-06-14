import api from "./api";

export const addComment = (comment) => {
    return api.post("/comments", comment);
};

export const getCommentsByAnswerId = (answerId) => {
    return api.get(`/comments/answer/${answerId}`);
};

export const deleteComment = (id) => {
    return api.delete(`/comments/${id}`);
};