import axios from "axios";

const BASE_URL =
    "http://localhost:8080/messages";

export const getMessagesByRoom =
    roomId =>
        axios.get(
            `${BASE_URL}/room/${roomId}`,
            {
                headers: {
                    Authorization:
                        `Bearer ${localStorage.getItem("token")}`
                }
            }
        );

export const sendMessage =
    message =>
        axios.post(
            BASE_URL,
            message,
            {
                headers: {
                    Authorization:
                        `Bearer ${localStorage.getItem("token")}`
                }
            }
        );