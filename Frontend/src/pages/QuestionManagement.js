import {useEffect,useState} from "react";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import {
    getAllQuestions,
    deleteQuestion
} from "../services/questionService";
import {useNavigate} from "react-router-dom";
import "../styles/admin.css";

function QuestionManagement(){

    const [questions,setQuestions]=useState([]);
    const [search,setSearch]=useState("");
    const navigate=useNavigate();

    useEffect(()=>{loadQuestions();},[]);

    const loadQuestions=async()=>{
        try{
            const {data}=await getAllQuestions();
            setQuestions(data);
        }catch(err){
            console.log(err);
        }
    };

    const handleDelete=async id=>{
        if(!window.confirm("Delete question?")) return;

        try{
            await deleteQuestion(id);
            loadQuestions();
        }catch(err){
            console.log(err);
        }
    };

    const filtered=questions.filter(q=>
        q.title?.toLowerCase().includes(search.toLowerCase())
    );

    return(
        <div className="dashboard-page">
            <Navbar/>

            <div className="dashboard-body">
                <Sidebar/>

                <div className="content">

                    <h1>❓ Question Management</h1>

                    <input
                        className="form-input"
                        placeholder="Search question..."
                        value={search}
                        onChange={e=>setSearch(e.target.value)}
                    />

                    <br/><br/>

                    <div className="card">

                        <table className="admin-table">

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>Author</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>

                            <tbody>

                                {filtered.map(q=>(
                                    <tr key={q.id}>

                                        <td>{q.id}</td>
                                        <td>{q.title}</td>
                                        <td>{q.userName}</td>

                                        <td>

                                            <button
                                                className="primary-btn"
                                                onClick={()=>
                                                    navigate(
                                                        `/questions/${q.id}`
                                                    )
                                                }
                                            >
                                                View
                                            </button>

                                            <button
                                                className="danger-btn"
                                                onClick={()=>
                                                    handleDelete(q.id)
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

export default QuestionManagement;