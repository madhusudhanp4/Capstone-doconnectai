function AIAnswerCard({answer}) {

    return (

        <div className="card ai-card">

            <h4>
                🤖 DoConnect AI
            </h4>

            <p className="ai-answer-text">
                {answer}
            </p>

            <div className="ai-feedback">

                <button>
                    👍 Helpful
                </button>

                <button>
                    👎 Not Helpful
                </button>

            </div>

        </div>

    );
}

export default AIAnswerCard;