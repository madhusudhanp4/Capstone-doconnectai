import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import AIAnswerCard from "../components/AIAnswerCard";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import AnswerCard from "../components/AnswerCard/AnswerCard";
import { getQuestionById, deleteQuestion } from "../services/questionService";
import { getAnswersByQuestionId, addAnswer } from "../services/answerService";
import { generateAIAnswer } from "../services/aiService";
import "../styles/questionDetails.css";

function QuestionDetails() {
    const { id } = useParams();
    const navigate = useNavigate();
    const currentUser = JSON.parse(localStorage.getItem("user"));

    const [question, setQuestion] = useState({});
    const [answers, setAnswers] = useState([]);
    const [aiAnswer, setAiAnswer] = useState("");
    const [loadingAI, setLoadingAI] = useState(false);
    const [content, setContent] = useState("");

    useEffect(() => {
        loadQuestion();
        loadAnswers();
    }, [id]);

    const loadQuestion = async () => {
        try {
            const { data } = await getQuestionById(id);
            setQuestion(data);
        } catch (err) {
            console.log(err);
        }
    };

    const loadAnswers = async () => {
        try {
            const { data } = await getAnswersByQuestionId(id);
            setAnswers(data);
        } catch (err) {
            console.log(err);
        }
    };

    const handleGenerateAIAnswer =
        async () => {

            try {

                setLoadingAI(true);

                const dto = {
                    title: question.title,
                    description:
                        question.description
                };

                const response =
                    await generateAIAnswer(
                        dto
                    );

                setAiAnswer(
                    response.data
                );

            } catch (error) {

                console.log(error);

                setAiAnswer(
                    "Failed to generate AI answer."
                );

            } finally {

                setLoadingAI(false);

            }
        };


    const handleAnswerSubmit = async () => {
        if (!content.trim()) return;

        try {
            await addAnswer({
                content,
                questionId: id
            });

            setContent("");
            loadAnswers();
        } catch (err) {
            console.log(err);
        }
    };

    const handleDelete = async () => {
        if (!window.confirm("Delete this question?")) return;

        try {
            await deleteQuestion(id);
            navigate("/questions");
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <div className="dashboard-page">
            <Navbar />

            <div className="dashboard-body">
                <Sidebar />

                <div className="content">

                    <h1 className="question-title">
                        ❓ {question.title}
                    </h1>

                    <div className="question-meta">
                        <span>👤 {question.userName}</span>

                        {question.createdAt &&
                            <span>
                                📅 {new Date(question.createdAt)
                                    .toLocaleDateString()}
                            </span>
                        }
                    </div>

                    <div className="question-actions">

                        <button
                            className="primary-btn"
                            onClick={handleGenerateAIAnswer}
                        >
                            {
                                loadingAI
                                    ? "🤖 Thinking..."
                                    : "🤖 Generate AI Answer"
                            }
                        </button>

                        {(currentUser?.name === question.userName ||
                            currentUser?.role === "ADMIN") && (
                                <>
                                    <button
                                        className="secondary-btn"
                                        onClick={() =>
                                            navigate(`/questions/edit/${id}`)
                                        }
                                    >
                                        ✏️ Edit
                                    </button>

                                    <button
                                        className="danger-btn"
                                        onClick={handleDelete}
                                    >
                                        🗑 Delete
                                    </button>
                                </>
                            )}

                    </div>

                    <div className="card">
                        <h3>Description</h3>
                        <p>{question.description}</p>
                    </div>

                    {aiAnswer &&
                        <>
                            <br />
                            <AIAnswerCard answer={aiAnswer} />
                        </>
                    }

                    <h3 className="answers-section">
                        💬 Answers ({answers.length})
                    </h3>

                    {answers.length === 0 ?

                        <div className="card">
                            <h4>😔 No answers yet</h4>
                            <p>Be the first to answer.</p>
                        </div>

                        :

                        answers.map(answer => (
                            <AnswerCard
                                key={answer.id}
                                answer={answer}
                            />
                        ))
                    }

                    <div className="answer-box">

                        <h3>✍️ Your Answer</h3>

                        <textarea
                            className="form-input"
                            value={content}
                            onChange={e => setContent(e.target.value)}
                            placeholder="Write your answer here..."
                        />

                        <button
                            className="primary-btn answer-btn"
                            disabled={!content.trim()}
                            onClick={handleAnswerSubmit}
                        >
                            Submit Answer
                        </button>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default QuestionDetails;