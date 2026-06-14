import api from "./api";

export const getAllQuestions = () => {
    return api.get("/questions");
};


export const getQuestionById = (id) => {
    return api.get(`/questions/${id}`);
};

export const addQuestion = (question) => {
    return api.post("/questions", question);
};
