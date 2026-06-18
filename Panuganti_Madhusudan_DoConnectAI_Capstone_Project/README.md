# DoConnect AI

## Project Overview

DoConnect AI is an intelligent community platform developed using React, Spring Boot, and MySQL. The application enables users to ask questions, post answers, comment, vote, participate in chat rooms, and receive AI-powered assistance. It also provides role-based access control with separate functionalities for Users and Administrators.

## Features

* User Registration and Login using JWT Authentication
* Role-Based Access Control (User and Admin)
* Ask and Manage Questions
* Post and Accept Answers
* Comment on Answers
* Upvote and Downvote System
* Chat Rooms and Messaging
* AI-Powered Assistance
* Admin Dashboard and Management Modules
* Input Validation and Global Exception Handling

## Technology Stack

### Frontend

* React
* React Router
* Axios
* Bootstrap
* SweetAlert2

### Backend

* Java 22
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT Authentication
* Maven

### Database

* MySQL 8.0

## Project Structure

Backend/
Frontend/
AI-Service/
Documentation/

## Prerequisites

* Java JDK 22
* Maven 3.x
* Node.js v22.x
* MySQL 8.0
* Git

## Setup Instructions

### Backend

1. Clone the repository.
2. Configure database properties in application.properties.
3. Create the database:
   CREATE DATABASE doconnectai;
4. Run the Spring Boot application:
   mvn spring-boot:run

### Frontend

1. Navigate to the frontend directory.
2. Install dependencies:
   npm install
3. Start the application:
   npm run dev

Frontend URL:
http://localhost:3000

Backend URL:
http://localhost:8080

## API Documentation

Swagger UI:
http://localhost:8080/swagger-ui/index.html

## Project Modules

* Authentication Module
* Question Management Module
* Answer Management Module
* Comment Management Module
* Vote Management Module
* Chat Room and Messaging Module
* AI Assistant Module
* Admin Management Module

## Author

Panuganti Madhusudan
Project Engineer, Wipro Limited
Hyderabad, Telangana, India

## License

This project is developed for educational and learning purposes as part of the Great Learning Capstone Project.
