import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import QuestionCard from "../components/QuestionCard";
import { getAllQuestions } from "../services/questionService";
import "../styles/questions.css";

function Questions() {

    const [questions, setQuestions] = useState([]);
    const [search, setSearch] = useState("");

    const navigate = useNavigate();

    useEffect(() => {
        loadQuestions();
    }, []);

    const loadQuestions = async () => {
        try {
            const { data } = await getAllQuestions();
            setQuestions(data);
        }
        catch (err) {
            console.log(err);
        }
    };

    const filteredQuestions =
        questions.filter(q =>
            q.title
                .toLowerCase()
                .includes(
                    search.toLowerCase()
                )
        );

    return (
        <div className="dashboard-page">

            <Navbar />

            <div className="dashboard-body">

                <Sidebar />

                <div className="content">


                    <div className="questions-header">

                        <h1>
                            Questions
                        </h1>


                        <button
                            className="login-btn ask-btn"
                            onClick={() =>
                                navigate(
                                    "/ask-question"
                                )
                            }
                        >
                            Ask Question
                        </button>

                    </div>

                    <input
                        className="search-box"
                        value={search}
                        onChange={e =>
                            setSearch(
                                e.target.value
                            )
                        }
                        placeholder="Search questions..."
                    />

                                        <div className="question-filters">

                        <button>Latest</button>

                        <button>Most Answered</button>

                        <button>My Questions</button>

                    </div>


                    {
                        filteredQuestions.map(q => (

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