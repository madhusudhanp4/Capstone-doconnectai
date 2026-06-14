import "../styles/sidebar.css";
import { useNavigate } from "react-router-dom";

function Sidebar() {

    const navigate = useNavigate();

    return (

        <div className="sidebar">

            <p onClick={() => navigate("/dashboard")} >
                🏠 Dashboard</p>

            <p onClick={() => navigate("/questions")} >
                ❓ Questions</p>

            <p onClick={() => navigate("/ask-question")} >
                ➕ Ask Question</p>

            <p>💬 Chat</p>

            <p>👤 Profile</p>

            <p>⚙️ Admin</p>

            <p>🚪 Logout</p>

        </div>

    );
}

export default Sidebar;