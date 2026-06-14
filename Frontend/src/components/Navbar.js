import "../styles/navbar.css";

function Navbar() {

  return (
    <div className="top-bar">

      <h2>
        DoConnect AI
      </h2>

      <input
        className="search-box"
        type="text"
        placeholder="Search questions..."
      />

      <div className="nav-icons">

        🔔 👤

      </div>

    </div>
  );
}

export default Navbar;