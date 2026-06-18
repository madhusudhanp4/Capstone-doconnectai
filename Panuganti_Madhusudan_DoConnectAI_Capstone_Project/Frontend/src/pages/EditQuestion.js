import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import {
    getQuestionById,
    updateQuestion
} from "../services/questionService";

function EditQuestion() {

    const { id } = useParams();
    const navigate = useNavigate();

    const [question, setQuestion] =
        useState({
            title: "",
            description: ""
        });

    useEffect(() => {
        loadQuestion();
    }, []);

    const loadQuestion = async () => {
        const { data } =
            await getQuestionById(id);

        setQuestion({
            title: data.title,
            description: data.description
        });
    };

    const handleChange = e => {
        setQuestion({
            ...question,
            [e.target.name]:
                e.target.value
        });
    };

    const handleSubmit = async e => {
        e.preventDefault();

        await updateQuestion(
            id,
            question
        );

        navigate(`/questions/${id}`);
    };

    return (
        <div className="dashboard-page">

            <Navbar />

            <div className="dashboard-body">

                <Sidebar />

                <div className="content">

                    <h1>
                        ✏️ Edit Question
                    </h1>

                    <div className="ask-card">

                        <form
                            onSubmit={
                                handleSubmit
                            }
                        >

                            <div className="ask-form-group">

                                <label>
                                    Title
                                </label>

                                <input
                                    name="title"
                                    value={
                                        question.title
                                    }
                                    onChange={
                                        handleChange
                                    }
                                />

                            </div>

                            <div className="ask-form-group">

                                <label>
                                    Description
                                </label>

                                <textarea
                                    name="description"
                                    value={
                                        question.description
                                    }
                                    onChange={
                                        handleChange
                                    }
                                />

                            </div>

                            <button
                                className="primary-btn"
                            >
                                Save Changes
                            </button>

                        </form>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default EditQuestion;