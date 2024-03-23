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

```

## API Endpoints

## API Endpoints

### Users

#### Get User by ID
- **URL**: `/api/users/{id}`
- **Method**: `GET`
- **Request Parameters**:
    - `id`: ID of the user
- **Response**: 
    - `200 OK` with user details if found
    - `404 Not Found` if user not found
- **Example**:
    ```bash
    curl -X GET http://localhost:8080/api/users/1
    ```

#### Create User
- **URL**: `/api/users`
- **Method**: `POST`
- **Request Body**: 
    - User JSON object
- **Response**: 
    - `201 Created` with created user details
- **Example**:
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '{"username": "john_doe", "email": "john@example.com"}' http://localhost:8080/api/users
    ```

#### Update User
- **URL**: `/api/users/{id}`
- **Method**: `PUT`
- **Request Parameters**:
    - `id`: ID of the user
- **Request Body**: 
    - Updated user JSON object
- **Response**: 
    - `200 OK` with updated user details if found and updated
    - `404 Not Found` if user not found
- **Example**:
    ```bash
    curl -X PUT -H "Content-Type: application/json" -d '{"email": "john.doe@example.com"}' http://localhost:8080/api/users/1
    ```

#### Delete User
- **URL**: `/api/users/{id}`
- **Method**: `DELETE`
- **Request Parameters**:
    - `id`: ID of the user
- **Response**: 
    - `204 No Content` if user successfully deleted
    - `404 Not Found` if user not found
- **Example**:
    ```bash
    curl -X DELETE http://localhost:8080/api/users/1
    ```

### Foods

#### Get Food by ID
- **URL**: `/api/foods/{id}`
- **Method**: `GET`
- **Request Parameters**:
    - `id`: ID of the food
- **Response**: 
    - `200 OK` with food details if found
    - `404 Not Found` if food not found
- **Example**:
    ```bash
    curl -X GET http://localhost:8080/api/foods/1
    ```

#### Create Food
- **URL**: `/api/foods`
- **Method**: `POST`
- **Request Body**: 
    - Food JSON object
- **Response**: 
    - `201 Created` with created food details
- **Example**:
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '{"name": "Pizza", "price": 10.99}' http://localhost:8080/api/foods
    ```

#### Update Food
- **URL**: `/api/foods/{id}`
- **Method**: `PUT`
- **Request Parameters**:
    - `id`: ID of the food
- **Request Body**: 
    - Updated food JSON object
- **Response**: 
    - `200 OK` with updated food details if found and updated
    - `404 Not Found` if food not found
- **Example**:
    ```bash
    curl -X PUT -H "Content-Type: application/json" -d '{"price": 12.99}' http://localhost:8080/api/foods/1
    ```

#### Delete Food
- **URL**: `/api/foods/{id}`
- **Method**: `DELETE`
- **Request Parameters**:
    - `id`: ID of the food
- **Response**: 
    - `204 No Content` if food successfully deleted
    - `404 Not Found` if food not found
- **Example**:
    ```bash
    curl -X DELETE http://localhost:8080/api/foods/1
    ```
