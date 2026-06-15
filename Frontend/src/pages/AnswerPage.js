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
        <div className="min-h-screen bg-gray-100">

            <div className="max-w-4xl mx-auto p-6">
                <h2 className="text-2xl font-semibold text-gray-800">
                    {question.title}
                </h2>

                <p className="text-gray-600 mt-2">
                    {question.description}
                </p>
            </div>

            <div className="bg-white rounded-2xl shadow-sm border p-6 mb-6">
                <h4>Add Answer</h4>

                <form onSubmit={handleSubmit}>

                    <div className="mb-3">
                        <textarea
                            className="w-full border rounded-lg p-3"
                            rows="3"
                            placeholder="Write your answer..."
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                        ></textarea>
                    </div>

                    <button className="mt-3 bg-blue-600 text-white px-5 py-2 rounded-lg">

                        Post Answer
                    </button>

                </form>
            </div>

            <div>
                <h3 className="text-xl font-semibold text-gray-700 mb-4"></h3>
                Answers <h3/>

                {answers.length === 0 ? (
                    <p>No answers yet</p>
                ) : (

                    answers.map((a) => (
                        <div key={a.id} className="bg-white rounded-xl shadow-md p-4 mb-4 border border-gray-100">

                            <p className="text-gray-800 text-lg">{a.content}</p>

                            <p className="text-sm text-gray-500 mt-1">
                                Answered by <span className="font-semibold">{a.userName}</span>
                            </p>


                            <div className="flex items-center gap-3 mt-4">

                                <button
                                    onClick={() => handleVote(a.id, "UPVOTE")}
                                    className="px-3 py-1 rounded-lg bg-green-100 text-green-700 hover:bg-green-200 transition"
                                >
                                    Upvote
                                </button>

                                <button
                                    onClick={() => handleVote(a.id, "DOWNVOTE")}
                                    className="px-3 py-1 rounded-lg bg-red-100 text-red-700 hover:bg-red-200 transition"
                                >
                                    Downvote
                                </button>

                            </div>


                            <div className="mt-4">
                                <input
                                    type="text"
                                    placeholder="Write a comment..."
                                    className="w-full border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
                                    value={commentText[a.id] || ""}
                                    onChange={(e) => handleCommentChange(a.id, e.target.value)}
                                />

                                <button
                                    onClick={() => handleCommentSubmit(a.id)}
                                    className="mt-2 bg-blue-500 text-white px-4 py-1 rounded-lg hover:bg-blue-600 transition"
                                >
                                    Add Comment
                                </button>
                            </div>


                            {a.comments && a.comments.map((c) => (
                                <div key={c.id} className="mt-2 bg-gray-50 rounded-lg px-3 py-2">
                                    <p className="text-sm">
                                        <span className="font-semibold">{c.userName}:</span> {c.content}
                                    </p>
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
