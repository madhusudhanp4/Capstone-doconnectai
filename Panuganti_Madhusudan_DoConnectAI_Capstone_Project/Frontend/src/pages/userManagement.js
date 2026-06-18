import {useEffect,useState} from "react";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import {getAllUsers,deleteUser} from "../services/userService";

function UserManagement(){

    const [users,setUsers]=useState([]);
    const [search,setSearch]=useState("");

    useEffect(()=>{loadUsers();},[]);

    const loadUsers=async()=>{
        try{
            const {data}=await getAllUsers();
            setUsers(data);
        }catch(err){
            console.log(err);
        }
    };

    const handleDelete=async id=>{
        if(!window.confirm("Delete user?")) return;
        try{
            await deleteUser(id);
            loadUsers();
        }catch(err){
            console.log(err);
        }
    };

    const filtered=users.filter(u=>
        u.name?.toLowerCase().includes(search.toLowerCase())||
        u.email?.toLowerCase().includes(search.toLowerCase())
    );

    return(
        <div className="dashboard-page">
            <Navbar/>

            <div className="dashboard-body">
                <Sidebar/>

                <div className="content">

                    <h1>👥 User Management</h1>

                    <input
                        className="form-input"
                        placeholder="Search user..."
                        value={search}
                        onChange={e=>setSearch(e.target.value)}
                    />

                    <br/><br/>

                    <div className="card">

                        <table className="admin-table">

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Role</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>

                            <tbody>

                                {filtered.map(u=>(
                                    <tr key={u.id}>
                                        <td>{u.id}</td>
                                        <td>{u.name}</td>
                                        <td>{u.email}</td>
                                        <td>{u.role}</td>

                                        <td>

                                            <button
                                                className="primary-btn"
                                                onClick={()=>alert(
                                                    `ID : ${u.id}\nName : ${u.name}\nEmail : ${u.email}\nRole : ${u.role}`
                                                )}
                                            >
                                                View
                                            </button>

                                            {u.role!=="ADMIN"&&(
                                                <button
                                                    className="danger-btn"
                                                    onClick={()=>handleDelete(u.id)}
                                                >
                                                    Delete
                                                </button>
                                            )}

                                        </td>

                                    </tr>
                                ))}

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default UserManagement;