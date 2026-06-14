import React from "react";
import ReactDOM from "react-dom/client";
import "bootstrap/dist/css/bootstrap.min.css";
import "./styles/global.css";
import AuthProvider from "./context/AuthContext";
import "bootstrap-icons/font/bootstrap-icons.css";
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';

import App from "./App";

const root = ReactDOM.createRoot(
  document.getElementById("root")
);

root.render(

  <React.StrictMode>

    <AuthProvider>

      <App />

      <ToastContainer/>

    </AuthProvider>

  </React.StrictMode>
);