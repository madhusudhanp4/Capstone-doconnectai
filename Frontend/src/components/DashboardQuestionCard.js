function DashboardQuestionCard({
    title,
    author,
    answers,
    views
}) {
    return (
        <div className="card dashboard-question-card">
            <h5 className="heading-secondary">
                {title}
            </h5>

            <p className="text-primary">
                👤 {author}
            </p>

            <div className="question-meta">
                <span>👁️ {views}</span>
                <span>💬 {answers}</span>
            </div>
        </div>
    );
}

export default DashboardQuestionCard;