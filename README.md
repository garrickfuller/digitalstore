# digitalstore

Overview
This project is a simple e-commerce web application focused on selling digital codes (e.g., license keys, vouchers, gift codes). Users can register, log in, browse products (codes), make purchases, and receive their codes automatically. An admin panel will be included for managing inventory, orders, and refunds.

Current Setup 

Backend - Spring Boot

Frontend - Nonexistent right now lol

Database - SQL through mySQL with Docker Compose

User Management:
User entity with BCrypt password hashing.
REST endpoints for CRUD
Includes basic validation via Jakarta Bean Validation.
JWT through Spring Security for authentication.

Endpoints
Testers for role management and JWT auth
GET /api/test/all (Public)
GET /api/test/user (User only)
GET /api/test/admin (Admin and user acess only)

POST /api/auth/signup 
Payload - 
{ "username": "testUser",
  "email": "testUser@mail.com",
  "password": "123456",
  "role": [] (blank for User, or define "user" "admin" etc
  }

POST /api/auth/signin
Returns Token, ID, Email, Role
Payload -
{
  "username": "testUser",
  "password": "123456"
}

DEL /api/users/id/{}
Self explanatory

