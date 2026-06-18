import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import StatsCard from "../components/StatsCard";
import QuestionPreview from "../components/QuestionPreview";
import "../styles/dashboard.css";
import DashboardQuestionCard from "../components/DashboardQuestionCard";


function Dashboard() {
    const { user } = useContext(AuthContext);

    return (
        <div className="dashboard-page">
            <Navbar />

            <div className="dashboard-body">
                <Sidebar />

                <div className="content">
                    <h1 className="page-title">
                        Welcome Back, {user?.name} 👋
                    </h1>

                    <p className="page-subtitle">
                        Glad to see you again.
                    </p>

                    <div className="row g-4">
                        <div className="col-md-4">
                            <StatsCard
                                title="🔔 Notifications"
                                count="14"
                            />
                        </div>

                        <div className="col-md-4">
                            <StatsCard
                                title="💬 Unread Chats"
                                count="8"
                            />
                        </div>

                        <div className="col-md-4">
                            <StatsCard
                                title="🤖 AI Suggestions"
                                count="20"
                            />
                        </div>
                    </div>

                    <div className="row g-4 recent-questions">

                        <div className="col-lg-6">
                            <h3 className="page-subtitle">
                                📰 Trending Tech News
                            </h3>

                            <DashboardQuestionCard
                                title="OpenAI releases GPT-6 with advanced coding capabilities"
                                author="OpenAI"
                                views="2.1M"
                                answers="AI"
                            />

                            <DashboardQuestionCard
                                title="React 20 introduces server-first rendering improvements"
                                author="React Team"
                                views="850K"
                                answers="React"
                            />

                            <DashboardQuestionCard
                                title="Java 25 officially released with new language features"
                                author="Oracle"
                                views="640K"
                                answers="Java"
                            />
                        </div>

                        <div className="col-lg-6">
                            <h3 className="page-subtitle">
                                🔒 Cyber & Industry Updates
                            </h3>

                            <DashboardQuestionCard
                                title="Google patches critical Chrome zero-day vulnerability"
                                author="Google"
                                views="1.3M"
                                answers="Security"
                            />

                            <DashboardQuestionCard
                                title="AWS launches new AI infrastructure and GPU services"
                                author="AWS"
                                views="720K"
                                answers="Cloud"
                            />

                            <DashboardQuestionCard
                                title="Infosys announces new hiring drive for fresh graduates"
                                author="Infosys"
                                views="410K"
                                answers="Careers"
                            />
                        </div>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;