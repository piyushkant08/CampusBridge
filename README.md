# CampusBridge

CampusBridge is a full-stack student helpdesk and resource request platform. It allows students to raise campus-related requests such as academic help, project support, placement guidance, hostel issues, lost-and-found updates, and general queries in one centralized place.

## Features

* Create student help/resource requests
* View all requests
* Filter requests by status and category
* Update request status: `OPEN`, `IN_PROGRESS`, `RESOLVED`
* Add comments or replies to a request
* Delete requests
* H2 database console for checking saved data
* Clean layered backend architecture

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database
* Maven

### Frontend

* React.js
* Vite
* CSS
* Fetch API

## Project Structure

```text
campusbridge-fullstack/
├── backend/      # Spring Boot REST API
└── frontend/     # React + Vite frontend
```

## How to Run Backend

```bash
cd backend
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

H2 Console:

```text
http://localhost:8080/h2-console
```

## How to Run Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on:

```text
http://localhost:5173
```

## API Endpoints

| Method | Endpoint                           | Description                 |
| ------ | ---------------------------------- | --------------------------- |
| GET    | `/api/requests`                    | Get all requests            |
| GET    | `/api/requests?status=OPEN`        | Filter requests by status   |
| GET    | `/api/requests?category=ACADEMICS` | Filter requests by category |
| GET    | `/api/requests/{id}`               | Get request by ID           |
| POST   | `/api/requests`                    | Create a new request        |
| PUT    | `/api/requests/{id}`               | Update a request            |
| PATCH  | `/api/requests/{id}/status`        | Update request status       |
| DELETE | `/api/requests/{id}`               | Delete a request            |
| GET    | `/api/requests/{id}/comments`      | Get comments                |
| POST   | `/api/requests/{id}/comments`      | Add comment                 |
