import { Routes, Route } from "react-router-dom";
import Login from "../pages/Login";
import Register from "../pages/Register";
import Dashboard from "../pages/Dashboard";
import Questions from "../pages/Questions";
import AskQuestion from "../pages/AskQuestion";
import QuestionDetails from "../pages/QuestionDetails";
import Profile from "../pages/Profile";
import Chat from "../pages/Chat";
import EditQuestion from "../pages/EditQuestion";
import Admin from "../pages/Admin";
import UserManagement from "../pages/userManagement";
import QuestionManagement from "../pages/QuestionManagement";
import AnswerManagement from "../pages/AnswerManagement";
import ChatRoomManagement from "../pages/ChatRoomManagement";

function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login/>}/>
            <Route path="/register" element={<Register />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/questions" element={<Questions />} />
            <Route path="/ask-question" element={<AskQuestion />} />
            <Route path="/questions/:id" element={<QuestionDetails />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/chat" element={<Chat />} />
            <Route path="/questions/edit/:id" element={<EditQuestion />}
            />
            <Route
                path="/admin"
                element={<Admin />}
            />
            <Route
                path="/admin/users"
                element={<UserManagement />}
            />

            <Route
                path="/admin/questions"
                element={<QuestionManagement />}
            />

            <Route
                path="/admin/answers"
                element={<AnswerManagement />}
            />
            <Route
                path="/admin/chatrooms"
                element={<ChatRoomManagement />}
            />
        </Routes>
    );
}

export default AppRoutes;