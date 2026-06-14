import "../styles/questions.css";
import { useNavigate } from "react-router-dom";

function QuestionCard({
    id,
    title,
    author,
    answers,
    votes
}) {

    const navigate = useNavigate();

    return (

        <div
            className="card question-card"
            onClick={() =>
                navigate(
                    `/questions/${id}`
                )
            }
        >

            <h4 className="text-primary" >
                {title}
            </h4>

            <p>

                Posted by
                {" "}
                {author}

            </p>

            <div
                className="question-stats"
            >

                <span>
                    {answers}
                    {" "}
                    Answers
                </span>

                <span>
                    {votes}
                    {" "}
                    Votes
                </span>

            </div>

        </div>
    );
}

export default QuestionCard;