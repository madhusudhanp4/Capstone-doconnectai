import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import "../styles/navbar.css";

function Navbar() {

  const { user } = useContext(AuthContext);

  return (
 <header className="top-bar">

    <div className="nav-left">

        <h2 className="logo">
            DoConnect AI
        </h2>

        <input
            className="search-box"
            placeholder="Search questions..."
        />

    </div>

    <div className="nav-right">

        <span>🔔</span>

        <span>
            👤 {user?.name}
        </span>

    </div>

</header>
  );
}

export default Navbar;