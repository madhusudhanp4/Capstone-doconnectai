import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import { getAllRooms, createRoom, deleteRoom } from "../services/chatRoomService";
import "../styles/admin.css";

function ChatRoomManagement() {
    const [rooms, setRooms] = useState([]);
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");

    useEffect(() => { loadRooms(); }, []);

    const loadRooms = async () => {
        try { const { data } = await getAllRooms(); setRooms(data); }
        catch (err) { console.log(err); }
    };

    const handleCreate = async () => {
        if (!name.trim()) return;
        try {
            await createRoom({ name, description });
            setName(""); setDescription("");
            loadRooms();
        } catch (err) { console.log(err); }
    };

    const handleDelete = async id => {
        if (!window.confirm("Delete room?")) return;
        try { await deleteRoom(id); loadRooms(); }
        catch (err) { console.log(err); }
    };

    return (
        <div className="dashboard-page">
            <Navbar />
            <div className="dashboard-body">
                <Sidebar />
                <div className="content">
                    <h1>🏠 Chat Room Management</h1>
                    <div className="card">
                        <div className="room-form">
                            <input className="form-input" placeholder="Room name" value={name} onChange={e => setName(e.target.value)} />
                            <input className="form-input" placeholder="Description" value={description} onChange={e => setDescription(e.target.value)} />
                            <button className="primary-btn" onClick={handleCreate}>Create</button>
                        </div>
                        <br />
                        <table className="admin-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {rooms.map(r => (
                                    <tr key={r.id}>
                                        <td>{r.id}</td>
                                        <td>{r.name}</td>
                                        <td>{r.description || "No description"}</td>
                                        <td>
                                            <button className="danger-btn" onClick={() => handleDelete(r.id)}>Delete</button>
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

export default ChatRoomManagement;