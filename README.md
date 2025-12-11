# Job Tracker System - Backend

**Technologies:** Java, Spring Boot, MySQL, JWT Authentication, Maven

---

## **Project Overview**
Job Tracker System is a full-stack application backend built with Spring Boot.  
It allows users to track their job applications, manage job details, and perform CRUD operations securely using JWT authentication.

---

## **Features**
- User registration and login with **JWT Authentication**
- Add, update, delete, and view job applications
- Search and filter jobs
- Role-based access control (Admin/User)
- Uses **MySQL** for database storage
- Modular architecture using **Controller → Service → Repository** layers

---

## **Folder Structure**
src/
├─ main/java/com/example/tracker
│ ├─ controller/
│ ├─ model/
│ ├─ repository/
│ ├─ security/
│ └─ service/
├─ main/resources/application.properties
pom.xml

---

## **How to Run Backend**
1. Clone the repository:
```bash
git clone https://github.com/sakshi-30P/job-tracker-backend.git
2. Import project in STS / Eclipse / IntelliJ
3. Setup MySQL database:
CREATE DATABASE job_tracker;
Update application.properties with DB credentials
4. Run the Spring Boot application:
mvn spring-boot:run
5.Backend runs on: http://localhost:8080

