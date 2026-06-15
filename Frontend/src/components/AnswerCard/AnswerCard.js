import VoteSection from "./VoteSection";
import CommentSection from "./CommentSection";

function AnswerCard({ answer }) {

    return (

        <div className="card answer-card">

            <div className="answer-user">
                {answer.userName}
            </div>

            <div className="answer-content">
                {answer.content}
            </div>

            <VoteSection
                answerId={answer.id}
            />

            <hr/>

            <CommentSection
                answerId={answer.id}
            />

        </div>

    );
}

export default AnswerCard;