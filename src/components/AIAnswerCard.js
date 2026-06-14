import ReactMarkdown from "react-markdown";

function AIAnswerCard({ answer }) {

    return (

        <div className="card ai-card">

            <h4 className="text-primary">
                🤖 DoConnect AI
            </h4>

            <div className="answer-content">

                <ReactMarkdown>
                    {answer}
                </ReactMarkdown>

            </div>

        </div>

    );
}

export default AIAnswerCard;