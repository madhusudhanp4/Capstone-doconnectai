import { useContext, useEffect, useRef, useState } from "react";
import { AuthContext } from "../context/AuthContext";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import { getAllRooms } from "../services/chatRoomService";
import {
    getMessagesByRoom,
    sendMessage
} from "../services/chatMessageService";
import "../styles/chat.css";

function Chat() {
    const { user } = useContext(AuthContext);

    const [rooms, setRooms] = useState([]);
    const [messages, setMessages] = useState([]);
    const [selectedRoom, setSelectedRoom] = useState(null);
    const [message, setMessage] = useState("");

    const messagesRef = useRef(null);

    const loadRooms = async () => {
        try {
            const { data } = await getAllRooms();
            setRooms(data.filter(r => r.name?.trim()));
        } catch (e) {
            console.log(e);
        }
    };

    const loadMessages = async id => {
        try {
            const { data } = await getMessagesByRoom(id);
            setMessages(data);
        } catch (e) {
            console.log(e);
        }
    };

    useEffect(() => {
        loadRooms();
    }, []);

    useEffect(() => {
        messagesRef.current?.scrollTo({
            top: messagesRef.current.scrollHeight,
            behavior: "smooth"
        });
    }, [messages]);

    const selectRoom = room => {
        setSelectedRoom(room);
        loadMessages(room.id);
    };

    const handleSend = async () => {
        if (!message.trim() || !selectedRoom) return;

        try {
            await sendMessage({
                roomId: selectedRoom.id,
                message
            });

            setMessage("");
            loadMessages(selectedRoom.id);
        } catch (e) {
            console.log(e);
        }
    };

    return (
        <div className="dashboard-page">
            <Navbar />

            <div className="dashboard-body">
                <Sidebar />

                <div className="chat-page">
                    <div className="chat-rooms">
                        <h3>Rooms</h3>

                        {rooms.map(room => (
                            <div
                                key={room.id}
                                className={`room-card ${
                                    selectedRoom?.id === room.id
                                        ? "active-room"
                                        : ""
                                }`}
                                onClick={() => selectRoom(room)}
                            >
                                {room.name}
                            </div>
                        ))}
                    </div>

                    <div className="chat-messages">
                        <h3>
                            {selectedRoom?.name || "Select a Room"}
                        </h3>

                        <div
                            className="messages"
                            ref={messagesRef}
                        >
                            {messages.map(msg => (
                                <div
                                    key={msg.id}
                                    className={`message-card ${
                                        msg.userName === user?.name
                                            ? "own"
                                            : ""
                                    }`}
                                >
                                    <div className="message-header">
                                        <strong>
                                            {msg.userName}
                                        </strong>

                                        <span>
                                            {new Date(
                                                msg.createdAt
                                            ).toLocaleTimeString([], {
                                                hour: "2-digit",
                                                minute: "2-digit"
                                            })}
                                        </span>
                                    </div>

                                    <p>{msg.message}</p>
                                </div>
                            ))}
                        </div>

                        {selectedRoom && (
                            <div className="message-box">
                                <input
                                    value={message}
                                    placeholder="Type a message..."
                                    onChange={e =>
                                        setMessage(e.target.value)
                                    }
                                    onKeyDown={e =>
                                        e.key === "Enter" &&
                                        handleSend()
                                    }
                                />

                                <button
                                    onClick={handleSend}
                                >
                                    Send
                                </button>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Chat;