import api from "./api";

export const generateAIAnswer = data => {
    return api.post("/ai/generate", data);
};