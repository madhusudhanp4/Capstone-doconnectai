import { useEffect, useState } from "react";
import API from "../services/api";

import QuestionCard from "../components/QuestionCard";
import AnswerForm from "../components/AnswerForm";
import AnswerCard from "../components/AnswerCard";

function AnswerPage({ question }) {

    const [answers, setAnswers] = useState([]);

    useEffect(() => {
        if (question) fetchAnswers();
    }, [question]);

    const fetchAnswers = async () => {
        const res = await API.get(`/answers/question/${question.id}`);
        setAnswers(res.data);
    };

    if (!question) return null;

    return (
        <div className="min-h-screen bg-gray-100 p-6">

            <div className="max-w-4xl mx-auto">

                <QuestionCard question={question} />

                <AnswerForm
                    questionId={question.id}
                    refresh={fetchAnswers}
                />

                <h3 className="text-xl font-semibold mb-4">Answers</h3>

                {answers.map(a => (
                    <AnswerCard
                        key={a.id}
                        answer={a}
                        refresh={fetchAnswers}
                    />
                ))}

            </div>

        </div>
    );
}

export default AnswerPage;