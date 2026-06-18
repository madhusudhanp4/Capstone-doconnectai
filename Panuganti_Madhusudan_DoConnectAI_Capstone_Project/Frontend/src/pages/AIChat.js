import { useState, useRef, useEffect } from "react";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import { generateAIAnswer } from "../services/aiService";
import "../styles/aichat.css";


function AIChat() {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState("");
    const [loading, setLoading] = useState(false);
    const messagesRef = useRef(null);

    useEffect(() => {
        messagesRef.current?.scrollTo({ top: messagesRef.current.scrollHeight, behavior: "smooth" });
    }, [messages]);

    const handleSend = async () => {
        if (!input.trim()) return;

        const userMsg = { type: "user", text: input };
        setMessages(prev => [...prev, userMsg]);
        setInput("");
        setLoading(true);

        try {
            const { data } = await generateAIAnswer({ title: input, description: input });
            const aiMsg = { type: "ai", text: data };
            setMessages(prev => [...prev, aiMsg]);
        } catch {
            setMessages(prev => [...prev, { type: "ai", text: "AI failed to respond." }]);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="dashboard-page">
            <Navbar />
            <div className="dashboard-body">
                <Sidebar />

                <div className="ai-chat-page">
                    <h2>🤖 AI Chat Assistant</h2>

                    <div className="ai-chat-messages" ref={messagesRef}>
                        {messages.map((msg, i) => (
                            <div key={i} className={`chat-bubble ${msg.type}`}>
                                {msg.text}
                            </div>
                        ))}
                        {loading && <div className="chat-bubble ai">Thinking...</div>}
                    </div>

                    <div className="ai-chat-box">
                        <input
                            value={input}
                            placeholder="Ask anything..."
                            onChange={e => setInput(e.target.value)}
                            onKeyDown={e => e.key === "Enter" && handleSend()}
                        />
                        <button onClick={handleSend}>Send</button>
                    </div>
                </div>

            </div>
        </div>
    );
}

export default AIChat;
