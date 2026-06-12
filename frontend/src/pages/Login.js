import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";


function Login() {

    const [user, setUser] = useState({
        email: "",
        password: ""
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const res = await API.post("/users/login", user);

            localStorage.setItem("token", res.data);

            alert("Login successful");

            navigate("/home");

        } catch (err) {
            alert("Invalid user");
        }
    };

    return (
        <div className="container mt-5">
            <div className="card p-4">

                <h2>Login</h2>

                <form onSubmit={handleLogin}>

                    <input
                        type="email"
                        name="email"
                        className="form-control mb-3"
                        placeholder="Enter email"
                        onChange={handleChange}
                    />

                    <input
                        type="password"
                        name="password"
                        placeholder="Enter password"
                        className="form-control mb-3"
                        onChange={handleChange}
                    />

                    <button className="btn btn-primary">Login</button>

                    <p
                        style={{ marginTop: "15px", cursor: "pointer", color: "blue" }}
                        onClick={() => navigate("/register")}
                    >
                        New User? Register
                    </p>
                </form>
            </div>
        </div>
    );
}

export default Login;