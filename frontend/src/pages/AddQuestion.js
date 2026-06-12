import { useState } from "react";
import API from "../services/api";

function AddQuestion() {

    const [question, setQuestion] = useState({
        title: "",
        description: ""
    });

    const handleChange = (e) => {
        setQuestion({
            ...question,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const payload = {
                title: question.title,
                description: question.description
            };

            const response = await API.post("/questions", payload);

            console.log(response.data);

            alert("Question Added Successfully");

            setQuestion({
                title: "",
                description: ""
            });

        } catch (error) {
            console.error("FULL ERROR:", error);
            console.log("RESPONSE:", error.response);

            alert(JSON.stringify(error.response?.data));
        }

    };

    return (
        <div className="container mt-5">
            <div className="card p-4 shadow">

                <h2>Ask Question</h2>

                <form onSubmit={handleSubmit}>

                    <div className="mb-3">
                        <label className="form-label">Title</label>
                        <input
                            type="text"
                            name="title"
                            value={question.title}
                            onChange={handleChange}
                            className="form-control"
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Description</label>
                        <textarea
                            name="description"
                            value={question.description}
                            onChange={handleChange}
                            className="form-control"
                            rows="3"
                        />
                    </div>

                    <button className="btn btn-success">
                        Post Question
                    </button>

                </form>

            </div>
        </div>
    );

}

export default AddQuestion;