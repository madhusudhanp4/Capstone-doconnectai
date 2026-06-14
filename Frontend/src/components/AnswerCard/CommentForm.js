function CommentForm({
    commentText,
    setCommentText,
    onSubmit
}) {

    return (

        <div className="comment-box">

            <textarea
                className="form-input"
                placeholder="Write a comment..."
                value={commentText}
                onChange={(e) =>
                    setCommentText(
                        e.target.value
                    )
                }
            />

            <button
                className="primary-btn comment-btn"
                onClick={onSubmit}
            >
                Post Comment
            </button>

        </div>

    );
}

export default CommentForm;