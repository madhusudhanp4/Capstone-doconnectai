import api from "./api";

export const getAnswersByQuestionId = (id) => {
    return api.get(`/answers/question/${id}`);
};

export const addAnswer = (answer) => {
    return api.post("/answers", answer);
};

export const updateAnswer=(id) => {
    return api.put(`/answers/${id}`);
};

export const getAllAnswers=() =>{
    return api.get("/answers");
};

export const deleteAnswer=id=>
    api.delete(`/answers/${id}`);