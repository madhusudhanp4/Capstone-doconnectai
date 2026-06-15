import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "../styles/login.css";
import { loginUser } from "../services/authService";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { toast } from "react-toastify";

function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);

    const { login } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        setLoading(true);

        try {
            const response = await loginUser({
                email,
                password
            });

            const token = response.data;

            // ✅ store token via context
            login({ email }, token);

            toast.success("Login Successful!");

            // ✅ keep your requirement (dashboard)
            navigate("/dashboard");

        } catch (error) {
            console.error(error);
            toast.error("Invalid email or password");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="login-container">
            <div className="login-card">

                <h1 className="logo">
                    DoConnect
                </h1>

                <p className="subtitle">
                    StackOverflow + Discord + AI
                </p>

                <form onSubmit={handleLogin}>

                    {/* ✅ EMAIL */}
                    <div className="form-group">
                        <label>Email</label>

                        <input
                            type="email"
                            value={email}
                            required
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>

                    {/* ✅ PASSWORD */}
                    <div className="form-group">
                        <label>Password</label>

                        <input
                            type="password"
                            value={password}
                            required
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>

                    {/* ✅ BUTTON */}
                    <button
                        type="submit"
                        className="login-btn"
                        disabled={loading}
                    >
                        {loading ? "Signing In..." : "Sign In"}
                    </button>

                </form>

                <p className="register-text">
                    New User?{" "}
                    <Link to="/register" className="register-link">
                        Register
                    </Link>
                </p>

            </div>
        </div>
    );
}

export default Login;