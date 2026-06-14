import { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import QuestionCard from "../components/QuestionCard";
import { getAllQuestions } from "../services/questionService";


function Questions() {

    const [questions, setQuestions] = useState([]);
    const [search, setSearch] = useState("");

    useEffect(() => { loadQuestions(); }, []);

    const loadQuestions = async () => {
        try {
            const response = await getAllQuestions();

            console.log("Questions API:", response.data);

            setQuestions(response.data);
        }
        catch (error) {
            console.log(error);
        }
    };

    return (

        <div className="dashboard-page" >

            <Navbar />

            <div
                className="dashboard-body"
            >

                <Sidebar />

                <div
                    className="content"
                >

                    <h1>
                        Questions
                    </h1>

                    <button
                        className="login-btn"
                    >
                        Ask Question
                    </button>

                    {
                        questions.map(q => (
                            <QuestionCard
                                key={q.id}
                                id={q.id}
                                title={q.title}
                                author={q.userName}
                                answers="0"
                                votes="0"
                            />

                        ))
                    }

                </div>

            </div>

        </div>
    );
}

export default Questions;