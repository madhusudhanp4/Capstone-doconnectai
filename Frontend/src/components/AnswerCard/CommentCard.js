import "../../styles/commentcard.css";

function CommentCard({comment}) {

    return (

        <div className="card comment-card">

            <strong className="comment-author">
                {comment.userName}
            </strong>

            <p className="comment-content">
                {comment.content}
            </p>

        </div>

    );
}

export default CommentCard;