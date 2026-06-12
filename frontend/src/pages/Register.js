import { useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";



function Register() {

    const navigate = useNavigate();

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
            const response = await API.post("/users/register", user);

            console.log(response.data);

            localStorage.setItem("user", JSON.stringify(response.data));

            alert("User Registered Successfully");

            navigate("/");

            setUser({
                name: "",
                email: ""
            });


        } catch (error) {
            console.error(error);
            alert("Error: " + error.response?.data);
        }
    };

    return (
        <div className="container mt-5">
            <div className="card p-4 shadow">
                <h2 className="text-center">Register</h2>

                <form onSubmit={handleSubmit}>

                    <div className="mb-3">
                        <label className="form-label">Name</label>
                        <input
                            type="text"
                            name="name"
                            value={user.name}
                            onChange={handleChange}
                            className="form-control"
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Email</label>
                        <input
                            type="email"
                            name="email"
                            value={user.email}
                            onChange={handleChange}
                            className="form-control"
                        />

                        <div className="mb-3" >
                            <label className="form-label"> Password </label>
                            <input
                                type="password"
                                name="password"
                                value={user.password}
                                onChange={handleChange}
                                className="form-control"
                            />
                        </div>

                    </div>

                    <button className="btn btn-primary w-100">
                        Register
                    </button>

                </form>
            </div>
        </div>
    );
}

export default Register;