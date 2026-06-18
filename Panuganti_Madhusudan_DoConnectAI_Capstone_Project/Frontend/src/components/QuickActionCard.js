import { useNavigate } from "react-router-dom";

function QuickActionCard({
    icon,
    title,
    path
}) {
    const navigate = useNavigate();

    return (
        <div
            className="card quick-card"
            onClick={() => navigate(path)}
        >
            <h1>{icon}</h1>
            <p>{title}</p>
        </div>
    );
}

export default QuickActionCard;