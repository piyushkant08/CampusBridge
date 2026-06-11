# CampusBridge - Student Helpdesk and Resource Request Platform

CampusBridge is a simple full-stack project made for students. Instead of being a generic task app, it solves a real student problem: students often need notes, project teammates, placement guidance, hostel help, or lost-and-found support, but these requests get scattered across WhatsApp groups.

This app gives students one common place to raise requests and get replies from peers or mentors.

---

## Project Structure

```text
campusbridge-fullstack/
├── backend/     Spring Boot Java REST API
└── frontend/    React + Vite user interface
```

---

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

### Frontend
- React.js
- Vite
- CSS
- Fetch API

---

## Features

- Create student help/resource requests
- View all requests
- Filter requests by status and category
- Change request status: OPEN, IN_PROGRESS, RESOLVED
- Add comments/helpful replies to a request
- Delete requests
- H2 database console for checking saved data
- Clean layered backend architecture

---

## Student Request Categories

- ACADEMICS
- PROJECTS
- PLACEMENT
- HOSTEL
- LOST_FOUND
- GENERAL

---

## Backend Flow

```text
Controller -> Service -> Repository -> H2 Database
```

### Important Backend Files

```text
CampusBridgeApplication.java        Main Spring Boot app
HelpRequestController.java          REST API endpoints
HelpRequestService.java             Business logic
HelpRequestRepository.java          Database operations for requests
RequestCommentRepository.java       Database operations for comments
HelpRequest.java                    Main request entity
RequestComment.java                 Comment entity
GlobalExceptionHandler.java         Clean error handling
DataLoader.java                     Adds sample data when app starts
```

---

## How to Run Backend

```bash
cd backend
mvn spring-boot:run
```

Backend will run on:

```text
http://localhost:8080
```

H2 Console:

```text
http://localhost:8080/h2-console
```

H2 login details:

```text
JDBC URL: jdbc:h2:mem:campusbridge
Username: sa
Password: leave empty
```

---

## How to Run Frontend

Open another terminal:

```bash
cd frontend
npm install
npm run dev
```

Frontend will run on:

```text
http://localhost:5173
```

---

## Main APIs

| Method | Endpoint | Use |
|---|---|---|
| GET | /api/requests | Get all requests |
| GET | /api/requests?status=OPEN | Filter by status |
| GET | /api/requests?category=ACADEMICS | Filter by category |
| GET | /api/requests/{id} | Get request by ID |
| POST | /api/requests | Create request |
| PUT | /api/requests/{id} | Update request |
| PATCH | /api/requests/{id}/status | Update status |
| DELETE | /api/requests/{id} | Delete request |
| GET | /api/requests/{id}/comments | Get comments |
| POST | /api/requests/{id}/comments | Add comment |

---

## Sample Create Request Body

```json
{
  "studentName": "Rahul",
  "title": "Need help in Spring Boot basics",
  "description": "I have an interview soon and need a simple explanation of Controller, Service and Repository.",
  "category": "ACADEMICS",
  "priority": "HIGH"
}
```

---

## What To Say In Interview

I built CampusBridge, a student helpdesk and resource request platform. The idea is that students often ask for notes, project help, placement guidance, hostel help, or lost-and-found updates in different WhatsApp groups, which becomes messy. This platform allows students to raise a request in one place, categorize it, track its status, and receive comments or help from other students.

The backend is built using Spring Boot with a clean Controller-Service-Repository structure. I used Spring Data JPA with H2 database for persistence because it keeps the setup lightweight and easy to run. The frontend is made in React and calls the backend REST APIs using fetch.

Since I had limited time and wanted to focus on Java backend fundamentals, I kept the project simple but complete. It includes CRUD APIs, filtering, status updates, comments, validation, exception handling, database integration, and a working frontend.

---

## Why This Project Is Better Than A Basic Task App

A task management app is common and generic. CampusBridge is still simple, but it is more meaningful because it directly solves a real college problem: scattered student help requests.

It also gives better interview talking points:

- Why the project is useful
- How students can use it
- How REST APIs work
- How database entities are connected
- How frontend and backend communicate
- How status tracking works

---

## One-Day Learning Plan

### First 2 hours
Understand the problem, run the project, test APIs from the frontend.

### Next 3 hours
Study backend flow:

```text
Controller -> Service -> Repository -> Entity -> Database
```

### Next 2 hours
Understand frontend API calls and state updates.

### Last 2 hours
Practice explaining the project and prepare answers for common interview questions.
