import "../styles/navbar.css";

function Navbar() {

  return (
    <div className="top-bar">

      {/* ✅ App Name */}
      <h2 className="logo-text">
        DoConnect AI
      </h2>

      {/* ✅ Search */}
      <input
        className="search-box"
        type="text"
        placeholder="Search questions..."
      />

      {/* ✅ Icons */}
      <div className="nav-icons">

        <span className="nav-icon">🔔</span>

        <span className="nav-icon">👤</span>

      </div>

    </div>
  );
}

export default Navbar;