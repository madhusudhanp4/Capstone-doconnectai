import {useEffect,useState} from "react";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import {
    getAllAnswers,
    deleteAnswer
} from "../services/answerService";
import "../styles/admin.css";

function AnswerManagement(){

    const [answers,setAnswers]=useState([]);
    const [search,setSearch]=useState("");

    useEffect(()=>{loadAnswers();},[]);

    const loadAnswers=async()=>{
        try{
            const {data}=await getAllAnswers();
            setAnswers(data);
        }catch(err){
            console.log(err);
        }
    };

    const handleDelete=async id=>{
        if(!window.confirm("Delete answer?")) return;

        try{
            await deleteAnswer(id);
            loadAnswers();
        }catch(err){
            console.log(err);
        }
    };

    const filtered=answers.filter(a=>
        a.content?.toLowerCase().includes(search.toLowerCase())
    );

    return(
        <div className="dashboard-page">
            <Navbar/>

            <div className="dashboard-body">
                <Sidebar/>

                <div className="content">

                    <h1>💬 Answer Management</h1>

                    <input
                        className="form-input"
                        placeholder="Search answer..."
                        value={search}
                        onChange={e=>setSearch(e.target.value)}
                    />

                    <br/><br/>

                    <div className="card">

                        <table className="admin-table">

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>User</th>
                                    <th>Answer</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>

                            <tbody>

                                {filtered.map(a=>(
                                    <tr key={a.id}>

                                        <td>{a.id}</td>
                                        <td>{a.userName}</td>

                                        <td>
                                            {
                                                a.content?.length>50
                                                ?a.content.substring(0,50)+"..."
                                                :a.content
                                            }
                                        </td>

                                        <td>

                                            <button
                                                className="danger-btn"
                                                onClick={()=>
                                                    handleDelete(a.id)
                                                }
                                            >
                                                Delete
                                            </button>

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

export default AnswerManagement;