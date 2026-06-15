import { useState } from "react";
import QuestionList from "./QuestionList";
import AnswerPage from "./AnswerPage";
import AddQuestion from "./AddQuestion";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

function Home() {

    const [selectedQuestion, setSelectedQuestion] = useState(null);


    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("token");

        if (!token) {
            navigate("/");
        }
    }, []);

    <button
        className="btn btn-danger mb-3"
        onClick={() => {
            localStorage.removeItem("token");
            window.location.href = "/";
        }}
    >
        Logout
    </button>

    return (
        <div className="container mt-4">

            <h2>DoConnect</h2>

            <AddQuestion />

            <QuestionList onSelect={setSelectedQuestion} />

            <AnswerPage question={selectedQuestion} />

        </div>
    );
}

export default Home;
