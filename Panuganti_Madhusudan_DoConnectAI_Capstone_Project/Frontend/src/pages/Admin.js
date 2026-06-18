import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import { getAllUsers } from "../services/userService";
import { getAllQuestions } from "../services/questionService";
import { getAllAnswers } from "../services/answerService";
import { getAllRooms } from "../services/chatRoomService";
import StatsCard from "../components/StatsCard";
import "../styles/admin.css";
import { useNavigate } from "react-router-dom";
import { createRoom } from "../services/chatRoomService";
function Admin() {

    const [users, setUsers] = useState([]);
    const [questions, setQuestions] = useState([]);
    const [answers, setAnswers] = useState([]);
    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        loadData();
    }, []);

    const loadData = async () => {
        try {
            const u = await getAllUsers();
            const q = await getAllQuestions();
            const r = await getAllRooms();
            const a = await getAllAnswers();

            setUsers(u.data);
            setQuestions(q.data);
            setRooms(r.data);
            setAnswers(a.data);

        } catch (err) {
            console.log(err);
        }
    };

    const navigate = useNavigate();

    return (
        <div className="dashboard-page">

            <Navbar />

            <div className="dashboard-body">

                <Sidebar />

                <div className="content">

                    <h1 className="page-title">
                        🛡️ Admin Dashboard
                    </h1>

                    <div className="row g-4">

                        <div className="col-md-3">
                            <StatsCard
                                title="Users"
                                count={users.length}
                            />
                        </div>

                        <div className="col-md-3">
                            <StatsCard
                                title="Questions"
                                count={questions.length}
                            />
                        </div>

                        <div className="col-md-3">
                            <StatsCard
                                title="Answers"
                                count={answers.length}
                            />
                        </div>

                        <div className="col-md-3">
                            <StatsCard
                                title="Chat Rooms"
                                count={rooms.length}
                            />
                        </div>

                    </div>

                    <div className="admin-section">
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             <h3>
                            ⚡ Quick Actions
                        </h3>

                        <div className="row g-4">

                            <div className="col-md-4">
                                <div
                                    className="card admin-action"
                                    onClick={() => navigate("/admin/users")}
                                >
                                    👥 Manage Users
                                </div>
                            </div>

                            <div className="col-md-4">
                                <div
                                    className="card admin-action"
                                    onClick={() => navigate("/admin/questions")}
                                >
                                    ❓ Manage Questions
                                </div>
                            </div>

                            <div className="col-md-4">
                                <div
                                    className="card admin-action"
                                    onClick={() => navigate("/admin/answers")}
                                >
                                    💬 Manage Answers
                                </div>
                            </div>
                            <div className="col-md-4">
                                <div
                                    className="card admin-action"
                                    onClick={() =>
                                        navigate("/admin/chatrooms")
                                    }
                                >
                                    🏠 Manage Chat Rooms
                                </div>
                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default Admin;