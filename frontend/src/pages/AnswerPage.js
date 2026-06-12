import { useEffect, useState } from "react";
import API from "../services/api";

function AnswerPage({ question }) {

    const [answers, setAnswers] = useState([]);
    const [content, setContent] = useState("");
    const [commentText, setCommentText] = useState({});
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

    const handleCommentChange = (answerId, value) => {

        setCommentText({
            ...commentText,
            [answerId]: value
        });
    };

    const handleCommentSubmit = async (answerId) => {

        try {

            const payload = {
                content: commentText[answerId],
                answerId: answerId
            };

            await API.post("/comments", payload);

            alert("Comment added");

            setCommentText({
                ...commentText,
                [answerId]: ""
            });

            fetchAnswers();

        } catch (err) {
            alert("Error adding comment");
            console.log(err);
        }
    };


    const handleVote = async (answerId, type) => {

        try {

            const payload = {
                type: type,
                answerId: answerId
            };

            await API.post("/votes", payload);

            alert(type + " successful");

        } catch (err) {
            alert("Error voting");
            console.log(err);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            const payload = {
                content: content,
                questionId: question.id
            };

            const res = await API.post("/answers", payload);

            alert("Answer added");

            setContent("");

            fetchAnswers();

        } catch (err) {
            alert(JSON.stringify(err.response?.data));
            console.log(err.response);
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

                           
                            <div className="mt-2">
                                <button
                                    className="btn btn-outline-success me-2"
                                    onClick={() => handleVote(a.id, "UPVOTE")}
                                >
                                    👍 Upvote
                                </button>

                                <button
                                    className="btn btn-outline-danger"
                                    onClick={() => handleVote(a.id, "DOWNVOTE")}
                                >
                                    👎 Downvote
                                </button>
                            </div>

                            {/* ✅ COMMENT BOX */}
                            <div className="mt-3">
                                <input
                                    type="text"
                                    className="form-control mb-2"
                                    placeholder="Write a comment..."
                                    value={commentText[a.id] || ""}
                                    onChange={(e) =>
                                        handleCommentChange(a.id, e.target.value)
                                    }
                                />

                                <button
                                    className="btn btn-sm btn-primary"
                                    onClick={() => handleCommentSubmit(a.id)}
                                >
                                    Add Comment
                                </button>
                            </div>

                            {/* ✅ COMMENTS LIST */}
                            {a.comments && a.comments.map((c) => (
                                <div key={c.id} className="mt-2 p-2 border rounded bg-light">
                                    <small>
                                        <b>{c.userName}:</b> {c.content}
                                    </small>
                                </div>
                            ))}

                        </div>
                    ))

                )}
            </div>

        </div>
    );

}

export default AnswerPage;
