function AIAnswerCard({
    answer
}) {

    return (

        <div className="card">

            <h4 className="text-primary" >
                🤖 DoConnect AI
            </h4>

            <p className="text-secondary" >
                {answer}
            </p>

        </div>

    );
}

export default AIAnswerCard;