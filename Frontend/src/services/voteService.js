import api from "./api";

export const addVote = (vote) => {
    return api.post("/votes", vote);
};

export const getVoteCount = (answerId) => {
    return api.get(`/votes/count/${answerId}`);
};

export const getVotesByAnswerId = (answerId) => {
    return api.get(`/votes/answer/${answerId}`);
};