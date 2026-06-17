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
                                count="0"
                            />
                        </div>

                        <div className="col-md-4">
                            <StatsCard
                                title="💬 Unread Chats"
                                count="0"
                            />
                        </div>

                        <div className="col-md-4">
                            <StatsCard
                                title="🤖 AI Suggestions"
                                count="0"
                            />
                        </div>
                    </div>

                    <div className="row g-4 recent-questions">

                        <div className="col-lg-6">
                            <h3 className="page-subtitle">
                                🔥 Trending Questions
                            </h3>

                            <DashboardQuestionCard
                                title="How does JWT authentication work?"
                                author="Madhu"
                                views="120"
                                answers="5"
                            />

                            <DashboardQuestionCard
                                title="React Context API vs Redux?"
                                author="Raju"
                                views="95"
                                answers="3"
                            />
                        </div>

                        <div className="col-lg-6">
                            <h3 className="page-subtitle">
                                ❓ Unanswered Questions
                            </h3>

                            <DashboardQuestionCard
                                title="How to integrate Gemini API?"
                                author="Kumar"
                                views="34"
                                answers="0"
                            />

                            <DashboardQuestionCard
                                title="Docker with Spring Boot?"
                                author="Prabhas"
                                views="21"
                                answers="0"
                            />
                        </div>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;