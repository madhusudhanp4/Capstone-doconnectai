import "../styles/cards.css";

function StatsCard({
    title,
    count
}) {

    return (

        <div className="card stats-card">

            <h2 className="stats-number" >
                {count}
            </h2>

            <p >
                {title}
            </p>

        </div>
    );
}

export default StatsCard;