import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import StatsCard from "../components/StatsCard";
import "../styles/profile.css";

function Profile() {
    const { user } = useContext(AuthContext);

    return (
        <div className="dashboard-page">
            <Navbar />

            <div className="dashboard-body">
                <Sidebar />

                <div className="content">

                    <div className="profile-card card">

                        <div className="profile-avatar">
                            👤
                        </div>

                        <h2>
                            {user?.name}
                        </h2>

                        <p className="text-secondary">
                            {user?.email}
                        </p>

                        <span className="role-badge">
                            {user?.role}
                        </span>

                    </div>

                    <div className="row g-4 profile-stats">

                        <div className="col-md-3">
                            <StatsCard
                                title="⭐ Reputation"
                                count="0"
                            />
                        </div>

                        <div className="col-md-3">
                            <StatsCard
                                title="❓ Questions"
                                count="0"
                            />
                        </div>

                        <div className="col-md-3">
                            <StatsCard
                                title="💬 Answers"
                                count="0"
                            />
                        </div>

                        <div className="col-md-3">
                            <StatsCard
                                title="🤖 AI Answers"
                                count="0"
                            />
                        </div>

                    </div>

                    <div className="card profile-section">

                        <h3>
                            About
                        </h3>

                        <p className="text-secondary">
                            Full Stack Developer and Cybersecurity Enthusiast.
                        </p>

                    </div>

                    <div className="card profile-section">

                        <h3>
                            Recent Activity
                        </h3>

                        <p className="text-secondary">
                            No activity yet.
                        </p>

                    </div>

                </div>
            </div>
        </div>
    );
}

export default Profile;