import { useEffect, useState } from "react";
import API from "../services/api";

function AnswerPage({ question }) {

    const [answers, setAnswers] = useState([]);
    const [content, setContent] = useState("");

    useEffect(() => {
        if (question) {
            fetchAnswers();
        }
    }, [question]);

    const fetchAnswers = async () => {
        try {
            const res = await API.get(`/answers/question/${question.id}`);
            setAnswers(res.data);
        } catch (err) {
            console.log(err);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const user = JSON.parse(localStorage.getItem("user"));

            const payload = {
                content: content,
                userId: user.id,
                questionId: question.id
            };

            const res = await API.post("/answers", payload);

            alert("Answer added");

            setContent("");

            fetchAnswers();

        } catch (err) {
            alert(err.response?.data || "Error");
            console.log(err);
        }
    };

    if (!question) return null;

    return (
        <div className="container mt-5">

            <div className="card p-4 shadow mb-4">
                <h3>{question.title}</h3>
                <p>{question.description}</p>
            </div>

            <div className="card p-4 shadow mb-4">
                <h4>Add Answer</h4>

                <form onSubmit={handleSubmit}>

                    <div className="mb-3">
                        <textarea
                            className="form-control"
                            rows="3"
                            placeholder="Write your answer..."
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                        ></textarea>
                    </div>

                    <button className="btn btn-primary">
                        Post Answer
                    </button>

                </form>
            </div>

            <div>
                <h4>Answers</h4>

                {answers.length === 0 ? (
                    <p>No answers yet</p>
                ) : (
                    answers.map((a) => (
                        <div key={a.id} className="card p-3 mb-3 shadow-sm">
                            <p>{a.content}</p>
                            <small className="text-muted">
                                Answered by: {a.userName}
                            </small>
                        </div>
                    ))
                )}
            </div>

        </div>
    );

}

export default AnswerPage;
