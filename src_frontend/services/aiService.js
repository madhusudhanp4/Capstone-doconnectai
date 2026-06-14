import api from "./api";

export const generateAIAnswer = (question) => {
    return api.post(
        "/ai/answer",
        question
    );
};