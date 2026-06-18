import VoteButtons from "./VoteButtons";
import useVotes from "../../hooks/useVotes";

function VoteSection({ answerId }) 
{
    const {
        score,
        upVotes,
        downVotes,
        handleUpVote,
        handleDownVote
    } =
    
    useVotes(answerId);

    return (

        <VoteButtons
            votes={score}
            upVotes={upVotes}
            downVotes={downVotes}
            onUpVote={handleUpVote}
            onDownVote={handleDownVote}
        />

    );
}

export default VoteSection;