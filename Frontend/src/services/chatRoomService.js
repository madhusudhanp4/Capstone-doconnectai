import api from "./api";

export const getAllRooms=
    ()=>api.get("/chatrooms");

export const createRoom=
    room=>api.post(
        "/chatrooms",
        room
    );

export const deleteRoom=
    id=>api.delete(
        `/chatrooms/${id}`
    );