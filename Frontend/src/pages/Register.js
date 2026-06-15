import { useState } from "react";
import { Link } from "react-router-dom";
import "../styles/login.css";
import { registerUser } from "../services/authService";
import { toast } from "react-toastify";

function Register() {

    const [user, setUser] = useState({
        name: "",
        email: "",
        password: ""
    });

    const handleChange = (e) => {
        setUser({
            ...user,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await registerUser(user);
            toast.success("Registration successful")
            
        } catch (error) {
            console.log(error);
            toast.error("Registration failed");
        }
    };

    return (
        <div className="login-container">

            <div className="login-card">

                <h1 className="logo">
                    DoConnect AI
                </h1>

                <p className="subtitle">
                    Create your account
                </p>

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

                    <button
                        type="submit"
                        className="login-btn"
                    >
                        Create Account
                    </button>

                </form>

                <p className="register-text">

                    Already have an account?{" "}

                    <Link
                        to="/"
                        className="register-link"
                    >
                        Login
                    </Link>

                </p>

            </div>

        </div>
    );
}

export default Register;