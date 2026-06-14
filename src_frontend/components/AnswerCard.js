import VoteButtons from "./VoteButtons";
import CommentCard from "./CommentCard";

function AnswerCard({ answer }) {

    const comments = [

        {
            id: 1,
            userName: "Madhu",
            content: "Great answer!"
        },

        {
            id: 2,
            userName: "Raju",
            content: "Can you explain more?"
        }

    ];

    return (

        <div className="card answer-card">

            <div className="answer-user">
                {answer.userName}
            </div>

            <div className="answer-content">
                {answer.content}
            </div>

            <VoteButtons
                votes={answer.voteCount}
                onUpVote={() => { }}
                onDownVote={() => { }}
            />
            <h5
                style={{
                    marginTop: "20px"
                }}
            >
                Comments
            </h5>

            {
                comments.map(c => (

                    <CommentCard
                        key={c.id}
                        comment={c}
                    />

                ))
            }

        </div>

    );
}

export default AnswerCard;