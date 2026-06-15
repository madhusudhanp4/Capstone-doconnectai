import { useEffect, useState } from "react";
import API from "../services/api";

function QuestionList({ onSelect }) {

    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        fetchQuestions();
    }, []);

    const fetchQuestions = async () => {
        try {
            const response = await API.get("/questions");
            setQuestions(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container mt-5">
            <h2>All Questions</h2>

            {questions.map((q) => (
                <div
                    key={q.id}
                    className="card mb-3 shadow"
                    style={{ cursor: "pointer" }}
                    onClick={() => onSelect(q)}
                >
                    <div className="card-body">
                        <h5 className="card-title">{q.title}</h5>
                        <p className="card-text">{q.description}</p>
                        <p className="text-muted">Asked by: {q.userName}</p>
                    </div>
                </div>
            ))}

        </div>
    );
}

export default QuestionList;