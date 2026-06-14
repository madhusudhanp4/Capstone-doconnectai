import "../../styles/votebuttons.css";

function VoteButtons({
    upVotes,
    downVotes,
    votes,
    onUpVote,
    onDownVote
}) {

    return (

        <div className="vote-buttons">

            <button
                className="vote-btn"
                onClick={onUpVote}
            >
                <i className="bi bi-hand-thumbs-up-fill"></i>
                <span>{upVotes}</span>
            </button>

            <span className="vote-score">
                Score: {votes}
            </span>

            <button
                className="vote-btn"
                onClick={onDownVote}
            >
                <i className="bi bi-hand-thumbs-down-fill"></i>
                <span>{downVotes}</span>
            </button>

        </div>
    );
}

export default VoteButtons;