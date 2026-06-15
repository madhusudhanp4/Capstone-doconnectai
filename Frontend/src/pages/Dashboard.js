import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import StatsCard from "../components/StatsCard";
import QuestionPreview from "../components/QuestionPreview";
import "../styles/dashboard.css";
import { useNavigate } from "react-router-dom";

function Dashboard() {

   // const navigate = useNavigate()

    return (

        <div className="dashboard-page">

            <Navbar />

            <div className="dashboard-body">

                <Sidebar />

                <div className="content">

                    <h1 className="page-title">
                        Welcome Back 👋
                    </h1>

                    

                    <br />

                                      

                    <div className="row g-4">

                        <div className="col-md-3">
                            <StatsCard
                                title="Questions"
                                count="120"
                            />
                        </div>

                        

                        <div className="col-md-3">
                            <StatsCard
                                title="Answers"
                                count="340"
                            />
                        </div>

                        <div className="col-md-3">
                            <StatsCard
                                title="Users"
                                count="45"
                            />
                        </div>

                        <div className="col-md-3">
                            <StatsCard
                                title="AI Answers"
                                count="65"
                            />
                        </div>

                    </div>

                    <div className="recent-questions">

                        <h3 className="page-subtitle">
                            Recent Questions
                        </h3>

                        <QuestionPreview
                            title="How does JWT authentication work in Spring Boot?"
                            author="Madhu"
                            answers="5"
                        />

                        <QuestionPreview
                            title="Difference between React Context API and Redux?"
                            author="Raju"
                            answers="3"
                        />

                        <QuestionPreview
                            title="How to integrate Gemini API in React?"
                            author="Kumar"
                            answers="8"
                        />
                        

                    </div>

                </div>

            </div>

        </div>
    );
}

export default Dashboard;