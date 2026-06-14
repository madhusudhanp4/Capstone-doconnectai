import api from "./api";

export const getAnswersByQuestionId = (id) => {
    return api.get(`/answers/question/${id}`);
};

export const addAnswer = (answer) => {
    return api.post("/answers", answer);
};