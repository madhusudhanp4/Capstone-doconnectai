import CommentCard from "./CommentCard";
import CommentForm from "./CommentForm";

import useComments
from "../../hooks/useComments";

function CommentSection({
    answerId
}) {

    const { comments, commentText, setCommentText, handleAddComment } = useComments(answerId);


    
    return (

        <>

            <h5
                className="comments-heading"
            >
                Comments
            </h5>

            {
                comments.map(
                    c => (

                        <CommentCard
                            key={c.id}
                            comment={c}
                        />

                    )
                )
            }

            <CommentForm
                commentText={
                    commentText
                }
                setCommentText={
                    setCommentText
                }
                onSubmit={
                    handleAddComment
                }
            />

        </>

    );
}

export default CommentSection;