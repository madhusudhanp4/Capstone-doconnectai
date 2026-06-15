import "../styles/sidebar.css";
import { useNavigate, useLocation } from "react-router-dom";

function Sidebar() {

    const navigate = useNavigate();
    const location = useLocation(); // ✅ to detect active page

    return (

        <div className="sidebar">

            <p
                className={`sidebar-item ${location.pathname === "/dashboard" ? "active" : ""}`}
                onClick={() => navigate("/dashboard")}
            >
                🏠 Dashboard
            </p>

            <p
                className={`sidebar-item ${location.pathname === "/questions" ? "active" : ""}`}
                onClick={() => navigate("/questions")}
            >
                ❓ Questions
            </p>

            <p
                className={`sidebar-item ${location.pathname === "/ask-question" ? "active" : ""}`}
                onClick={() => navigate("/ask-question")}
            >
                ➕ Ask Question
            </p>

            <p className="sidebar-item">
                💬 Chat
            </p>

            <p className="sidebar-item">
                👤 Profile
            </p>

            <p className="sidebar-item">
                ⚙️ Admin
            </p>

            <p
                className="sidebar-item"
                onClick={() => navigate("/")}
            >
                🚪 Logout
            </p>

        </div>
    );
}

export default Sidebar;