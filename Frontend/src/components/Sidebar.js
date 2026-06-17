import { Link } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import "../styles/sidebar.css";

function Sidebar() {
    const { user, logout } = useContext(AuthContext);

    return (
        <aside className="sidebar">
            <Link to="/dashboard">🏠 Home</Link>
            <Link to="/questions">❓ Questions</Link>
            <Link to="/ask-question">➕ Ask Question</Link>
            <Link to="/ai-chat">🤖 AI Chat</Link>
            <Link to="/chat">💬 Chats</Link>
            <Link to="#" onClick={(e) => e.preventDefault()} className="disabled-link" > 🔔 Notifications </Link>
            <Link to="/leaderboard">🏆 Leaderboard</Link>
            <Link to="/profile">👤 Profile</Link>
            <Link to="#" onClick={(e) => e.preventDefault()} className="disable-link"> ⚙️ Settings</Link>

            {user?.role === "ADMIN" &&
                <Link to="/admin">🛡️ Admin Panel</Link>
            }

            <button className="logout-btn" onClick={logout} >🚪 Logout   </button>

        </aside>
    );
}

export default Sidebar;