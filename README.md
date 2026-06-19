# 📌 DocConnectAI

> A modern full-stack Q&A collaboration platform inspired by StackOverflow + Discord, enhanced with AI assistance.

---

## 🚀 Overview

**DocConnectAI** is a scalable web application that allows users to ask questions, share answers, and collaborate in real-time. It integrates AI to assist users with suggestions, making problem-solving faster and smarter.

Built using **React (Frontend)** and **Spring Boot (Backend)**, the application follows industry-standard architecture with secure authentication and role-based access.

---

## ✨ Features

### 👤 User Features
- User Registration & Login (JWT Authentication)
- Ask Questions and Post Answers
- Upvote / Downvote System
- Accept Best Answer
- Profile Management (CRUD)
- Dark Mode UI

### 🛠️ Advanced Features
- AI-based Answer Suggestions
- Real-time Chat Rooms (Discord style)
- Global Search Functionality
- Role-Based Dashboards (Admin / User / Moderator)
- Secure Role-Based Access Control

### 🧑‍💼 Admin Features
- Manage Users
- Moderate Questions & Answers
- Platform Monitoring

---

## 🏗️ Tech Stack

### Frontend
- React.js
- Tailwind CSS
- Axios
- React Router

### Backend
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate / JPA
- REST APIs

### Database
- MySQL

### Microservices
- AI Service
- Notification Service

---

## 📂 Project Structure
DocConnectAI/
│
├── Frontend/                 # React Application
│   ├── components/
│   ├── pages/
│   ├── services/
│   ├── hooks/
│   ├── context/
│   └── utils/
│
├── Backend/                  # Spring Boot Application
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   ├── security/
│   └── dto/
│    
└── README.md

---

## ⚙️ Setup Instructions

### 🔹 Prerequisites

Make sure you have the following installed:

- Java 17+
- Maven
- Node.js (v16 or above)
- MySQL Server
- Git

---

### 🔹 Backend Setup (Spring Boot)

```bash
- cd Backend

```
- Open this file: src/main/resources/application.properties
Update database configuration:

spring.datasource.url=jdbc:mysql://localhost:3306/doconnect
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

--- 

### 🔹 Frontend Setup (React)

cd Frontend
npm install
npm start

- Frontend runs at: http://localhost:3000

---

###🔹 API Configuration
- Make sure your Axios base URL is: 
- baseURL: http://localhost:8080


🔹 Authentication Flow
- User logs in → JWT token generated
- Token stored in localStorage
- Token sent in every request
- Authorization header format:
- Authorization: Bearer


🔹 Run Order
- Start MySQL Server
- Run Backend
- Run Frontend

🔹 Common Issues
- 403 Error
- Check JWT token
- Check Authorization header
- CORS Error
- Enable CORS in backend
- API not working
- Check baseURL
- Database error
- Verify MySQL credentials

🔹 AI Integration
- Provides AI suggestions
- Helps when no answers found
- Implemented as microservice

🔹 Sample API
- POST /api/questions
- Header:
- Authorization: Bearer 
Body:
{
"title": "JWT token not working",
"description": "Getting 403 forbidden error"
}

🔹Design Patterns Used
- MVC Architecture
- Layered Architecture
- DTO Pattern
- Dependency Injection
- Microservices Architecture