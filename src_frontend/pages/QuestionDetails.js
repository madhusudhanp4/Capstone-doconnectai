import { useEffect, useState } from "react";
import AIAnswerCard from "../components/AIAnswerCard";
import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import AnswerCard from "../components/AnswerCard";
import { getQuestionById } from "../services/questionService";
import { getAnswersByQuestionId, addAnswer } from "../services/answerService";
import "../styles/questionDetails.css";

function QuestionDetails() {

    const { id } = useParams();

    const [question, setQuestion] = useState({});
    const [answers, setAnswers] = useState([]);
    const [aiAnswer, setAiAnswer] = useState("");
    const [content, setContent] = useState("");

    useEffect(() => {
        loadQuestion();
        loadAnswers();
    }, [id]);

    const loadQuestion = async () => {
        try {
            const response =
                await getQuestionById(id);

            setQuestion(response.data);
        }
        catch (error) {
            console.log(error);
        }
    };

    const loadAnswers = async () => {
        try {
            const response =
                await getAnswersByQuestionId(id);

            setAnswers(response.data);
        }
        catch (error) {
            console.log(error);
        }
    };

    const generateAIAnswer =
        () => {

            setAiAnswer(

                "This is a dummy AI generated answer for testing purposes."

            );
        };

    const handleAnswerSubmit = async () => {

        if (!content.trim()) {
            alert("Please enter an answer.");
            return;
        }

        try {

            const answer = {
                content: content,
                questionId: id
            };

            await addAnswer(answer);

            setContent("");

            loadAnswers();
        }
        catch (error) {
            console.log(error);
        }
    };

    return (

        <div className="dashboard-page">

            <Navbar />

            <div className="dashboard-body">

                <Sidebar />

                <div className="content">

                    <h1 className="question-title" >
                        {question.title}
                    </h1>

                    <p className="question-author" >
                        Posted by {question.userName}
                    </p>

                    <br />

                    <h3>
                        Description
                    </h3>

                    <p>
                        {question.description}
                    </p>

                    <hr />

                    <button
                        className="primary-btn"
                        onClick={generateAIAnswer}
                    >
                        Generate AI Answer
                    </button>

                    <br />
                    <br />

                    {
                        aiAnswer &&

                        <AIAnswerCard
                            answer={aiAnswer}
                        />
                    }

                    <h3 className="answers-section">
                        Answers
                    </h3>

                    {
                        answers.length === 0 ?

                            <p>
                                No answers yet.
                                Be the first to answer!
                            </p>

                            :

                            answers.map(answer => (

                                <AnswerCard
                                    key={answer.id}
                                    answer={answer}
                                />

                            ))
                    }

                    <hr />

                    <div className="answer-box">

                        <h3>Your Answer</h3>

                        <textarea
                            className="form-input"
                            value={content}
                            onChange={(e) =>
                                setContent(e.target.value)
                            }
                            placeholder="Write your answer here..."
                        />

                        <button
                            type="button"
                            className="primary-btn answer-btn"
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