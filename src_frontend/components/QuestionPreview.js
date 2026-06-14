function QuestionPreview({
    title,
    author,
    answers
}) {

    return (

        <div
            className="card question-preview"
        >

            <h4 className="heading-secondary" >
                {title}
            </h4>

            <p className="text-primary" >
                Posted by {author}
            </p>

            <span className="text-accent" >
                {answers} Answers
            </span>

        </div>

    );
}

export default QuestionPreview;