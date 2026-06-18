import { useState, useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import toast, { Toaster } from "react-hot-toast";
import "../styles/login.css";
import { loginUser, getCurrentUser } from "../services/authService";
import { AuthContext } from "../context/AuthContext";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const { login } = useContext(AuthContext);
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);

    const handleLogin = async e => {
        e.preventDefault();
        setLoading(true);

        try {
            const { data: token } =
                await loginUser({ email, password });

            const { data: user } =
                await getCurrentUser(token);

            login(user, token);
            toast.success(`Welcome ${user.name}!`);
            navigate("/dashboard");
        } catch (err) {
            console.log(err);
            toast.error("Invalid Credentials!");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="login-container">

            <div className="login-card">
                <h1 className="logo">DoConnect AI</h1>

                <p className="subtitle">
                    StackOverflow + Discord + AI
                </p>

                <form onSubmit={handleLogin}>
                    <div className="form-group">
                        <label>Email</label>

                        <input
                            type="email"
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label>Password</label>

                        <input
                            type="password"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        />
                    </div>

                    <button type="submit" className="login-btn" disabled={loading}>
                        {loading ? "Signing In..." : "Sign In"}
                    </button>
                </form>

                <p className="register-text">
                    New User?{" "}
                    <Link
                        to="/register"
                        className="register-link"
                    >
                        Register
                    </Link>
                </p>
            </div>
        </div>
    );
}

export default Login;