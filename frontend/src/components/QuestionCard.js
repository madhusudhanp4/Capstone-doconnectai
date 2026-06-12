function QuestionCard({ question }) {
    return (
        <div className="bg-white rounded-2xl shadow-sm border p-6 mb-6">
            <h2 className="text-2xl font-semibold text-gray-800">
                {question.title}
            </h2>

            <p className="text-gray-600 mt-2">
                {question.description}
            </p>
        </div>
    );
}

export default QuestionCard;