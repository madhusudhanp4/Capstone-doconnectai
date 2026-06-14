function VoteButtons({
    votes,
    onUpVote,
    onDownVote
}) {

    return (
        <div className="vote-buttons">

            <button
                onClick={onUpVote}
            >
                👍
            </button>

            <span>
                {votes}
            </span>

            <button
                onClick={onDownVote}
            >
                👎
            </button>

        </div>
    );
}

export default VoteButtons;