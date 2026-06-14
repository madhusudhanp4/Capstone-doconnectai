import { Routes, Route } from "react-router-dom";
import Login from "../pages/Login";
import Register from "../pages/Register";
import Dashboard from "../pages/Dashboard";
import Questions from "../pages/Questions";
import AskQuestion from "../pages/AskQuestion";
import QuestionDetails from "../pages/QuestionDetails";

function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/questions" element={<Questions />} />
            <Route path="/ask-question" element={<AskQuestion />} />
            <Route path="/questions/:id" element={<QuestionDetails />} />
        </Routes>
    );
}

export default AppRoutes;