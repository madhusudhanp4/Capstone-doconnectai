import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import toast, { Toaster } from "react-hot-toast";
import "../styles/login.css";
import { registerUser } from "../services/authService";

function Register() {
    const [user, setUser] = useState({
        name: "",
        email: "",
        password: "",
        confirmPassword: ""
    });

    const navigate = useNavigate();

    const handleChange = e =>
        setUser({ ...user, [e.target.name]: e.target.value });

    const handleSubmit = async e => {
        e.preventDefault();

        if (user.password !== user.confirmPassword)
            return toast.error("Passwords do not match");

        try {
            const { confirmPassword, ...userData } = user;
            await registerUser(userData);

            toast.success("Registration successful!");
            setTimeout(() => navigate("/"), 1500);
        } catch (err) {
            console.log(err);
            toast.error("Registration failed");
        }
    };

    return (
        <div className="login-container">
            <Toaster
                position="top-center"
                containerStyle={{
                    top: "50%",
                    left: "50%",
                    transform: "translate(-50%, -50%)"
                }}
            />

            <div className="login-card">
                <h1 className="logo">DoConnect AI</h1>

                <p className="subtitle">Create your account</p>

                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Name</label>
                        <input
                            type="text"
                            name="name"
                            value={user.name}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Email</label>
                        <input
                            type="email"
                            name="email"
                            value={user.email}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input
                            type="password"
                            name="password"
                            value={user.password}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Confirm Password</label>
                        <input
                            type="password"
                            name="confirmPassword"
                            value={user.confirmPassword}
                            onChange={handleChange}
                        />
                    </div>

                    <button type="submit" className="login-btn">
                        Create Account
                    </button>
                </form>

                <p className="register-text">
                    Already have an account?{" "}
                    <Link to="/" className="register-link">
                        Login
                    </Link>
                </p>
            </div>
        </div>
    );
}

export default Register;