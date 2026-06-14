import { useState } from "react";
import { Link } from "react-router-dom";
import "../styles/login.css";
import { loginUser } from "../services/authService";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";


function Login() {

    const [email, setEmail] = useState("");

    const [password, setPassword] = useState("");

    const { login } = useContext(AuthContext);

    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await loginUser({
                email,
                password
            });

            const token = response.data;

            login({ email }, token);

            toast.success(
                "Login Successful!"
            );

            navigate("/dashboard");

        } catch (error) {
            console.log(error);

            toast.error(
                "Invalid Credentials!"
            );
        }
    };

    return (
        <div className="login-container">

            <div className="login-card">

                <h1 className="logo">
                    DoConnect AI
                </h1>

                <p className="subtitle">
                    StackOverflow + Discord + AI
                </p>

                <form onSubmit={handleLogin}>

                    <div className="form-group">

                        <label>Email</label>

                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(
                                e.target.value
                            )
                            }
                        />

                    </div>

                    <div className="form-group">

                        <label>Password</label>

                        <input
                            type="password"
                            value={password}
                            onChange={(e) =>
                                setPassword(
                                    e.target.value
                                )
                            }
                        />

                    </div>

                    <button
                        type="submit" className="login-btn"
                    >
                        Sign In
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