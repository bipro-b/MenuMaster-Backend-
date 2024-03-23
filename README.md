# MasterMenu API

MasterMenu is a RESTful API for managing users, foods, events, and more.

## Features

- User management
- Food management
- Event management
- Role-based access control
- JWT-based authentication
- MySQL integration

## Technologies Used

- Java
- Spring Boot
- Spring Security
- MySQL
- JSON Web Tokens (JWT)
- Maven

## Setup

1. **Clone the repository**

```bash
git clone <repository-url>
cd mastermenu

Set up MySQL

Install MySQL on your system
Create a database named mastermenu
Update application.properties with your MySQL username and password
Run the application

mvn spring-boot:run


API Endpoints
Users

GET /api/users/{id}: Get user by ID
POST /api/users: Create a new user
PUT /api/users/{id}: Update user
DELETE /api/users/{id}: Delete user
Foods

GET /api/foods/{id}: Get food by ID
POST /api/foods: Create a new food
PUT /api/foods/{id}: Update food
DELETE /api/foods/{id}: Delete food
Events

GET /api/events/{id}: Get event by ID
POST /api/events: Create a new event
PUT /api/events/{id}: Update event
DELETE /api/events/{id}: Delete event
Authentication

POST /api/auth/login: Login and get JWT token







