function CommentCard({
    comment
}) {

    return (

        <div className="card comment-card">

            <strong>
                {comment.userName}
            </strong>

            <p>
                {comment.content}
            </p>

        </div>

    );
}

export default CommentCard;