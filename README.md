# User Creation

## Overview
User Creation is a **Spring Boot-based user creation system** that utilizes **PostgreSQL** for data storage.

## System Requirements
Ensure that the following dependencies are installed before running the project:
- **Docker**
- **Java 17**
- **Maven**
- **PostgreSQL**

## Setup & Installation
This project is designed to run using **Docker**. Follow the steps below to set up and run the project:

### 1. Clone the Repository
```sh
git clone https://github.com/sunday-ucheawaji/user-creation.git
cd usercreation
```

### 2. Configure Environment Variables
Copy the `.env-sample` file to `.env` and update the necessary values:
```sh
cp .env-sample .env
```
Modify the `.env` file with appropriate values.

### 3. Run the Project Using Docker
```sh
docker-compose up -d
```
This command will build and start the services in detached mode.

## Services & Ports
- **Spring Boot Application** → `http://localhost:8080`
- **PostgreSQL** → `localhost:5432`

## API Documentation
Once the application is running, the API documentation can be accessed via Swagger:
- **Swagger UI** → [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

## Key API Endpoints
### **User Registration**
- `POST /api/users/register` → Register a new user
- `POST /api/users/register-admin` → Register an admin user

### **Fetching Users**
- `GET /api/users/users` → Get all users 
- `GET /api/users/admins` → Get admin users


## Database Migrations
The project uses **Flyway** for database migrations. When the application starts, Flyway automatically applies any new migration scripts.

## Verifying the Setup
To ensure that the project is running correctly, check the following:
1. Visit the **Swagger UI**: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
2. Verify logs using:
   ```sh
   docker logs -f <container-name>
   ```
3. Confirm that the database has been initialized properly by accessing PostgreSQL:
   ```sh
   docker exec -it <postgres-container-name> psql -U postgres -d userdb
   ```

## Stopping the Project
To stop the running containers, execute:
```sh
docker-compose down
```

## Deployment
This project does not have a predefined deployment method, but it can be deployed using **Docker on cloud services** such as AWS, GCP, or DigitalOcean.

---
For any issues, please create a GitHub issue or contact the project owner.

