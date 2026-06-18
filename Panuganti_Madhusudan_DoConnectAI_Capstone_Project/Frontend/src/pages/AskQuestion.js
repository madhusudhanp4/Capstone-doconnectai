import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import { addQuestion } from "../services/questionService";
import "../styles/askQuestion.css";

function AskQuestion() {

    const navigate =
        useNavigate();

    const [question,
        setQuestion]
        =
        useState({
            title: "",
            description: "",
        });

    const handleChange =
        (e) => {

            setQuestion({

                ...question,

                [e.target.name]:
                    e.target.value

            });
        };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await addQuestion(question);

            navigate("/questions");

        }
        catch (error) {

            console.log(error);

        }
    };

    return (

        <div className="ask-container">

            <Navbar />

            <div
                className="dashboard-body"
            >

                <Sidebar />

                <div
                    className="content"
                >

                    <h1>
                        Ask Question
                    </h1>

                    <div
                        className="ask-card"
                    >

                        <form
                            onSubmit={
                                handleSubmit
                            }
                        >

                            <div
                                className=
                                "ask-form-group"
                            >

                                <label>
                                    Title
                                </label>

                                <input
                                    type="text"
                                    name="title"
                                    value={
                                        question.title
                                    }
                                    onChange={
                                        handleChange
                                    }
                                    placeholder=
                                    "Enter question title"
                                />

                            </div>

                            <div
                                className=
                                "ask-form-group"
                            >

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
                                    placeholder=
                                    "Describe your problem..."
                                />
                                <div className="ask-tips">

                                    <h5>
                                        Writing Tips
                                    </h5>

                                    <ul>
                                        <li>Explain what you are trying to achieve.</li>
                                        <li>Mention any errors or exceptions.</li>
                                        <li>Include enough details to reproduce the problem.</li>

                                    </ul>

                                </div>

                            </div>




                            <button
                                type="submit"
                                className=
                                "login-btn"
                            >

                                Post Question

                            </button>

                        </form>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default AskQuestion;