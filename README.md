# digitalstore

 DigitalStore is an e-commerce web application focused on selling digital codes (e.g., license keys, vouchers, gift codes). Users can register, log in, browse products (codes), make purchases, and receive their codes automatically. An admin panel will be included for managing inventory, orders, and refunds. I built this to handle the massive amount of digital codes I was selling manually everyday to help reduce my manual workload :)

<img width="230" alt="Screenshot 2025-02-24 at 12 47 57 AM" src="https://github.com/user-attachments/assets/9f9e13a0-a313-4efb-ba4c-c4cf472db028" />





## Current Setup 

Backend - Spring Boot

Frontend - React, Axios for HTTP requests, React Router for routing (Demo inside digitalstore-frontend repo on my page.)


Database - SQL through mySQL with Docker Compose

Build/Dependency Management - Maven


## Features

User Registration & Login: Users can sign up and log in with credentials stored in a MySQL database with password encryption through bycrypt.

<img width="283" alt="Screenshot 2025-02-24 at 12 26 00 AM" src="https://github.com/user-attachments/assets/4a01f057-0a63-42af-9a72-c3219b1b856e" />

<img width="927" alt="Screenshot 2025-02-23 at 11 41 09 PM" src="https://github.com/user-attachments/assets/41526c89-a526-4e97-9a25-5dba850ecb11" />

JWT-Based Authentication: Secure, stateless authentication using JSON Web Tokens.

Role-Based Access Control: Two roles supported — ROLE_USER and ROLE_ADMIN.

Product Listing: Simple product entities with names, descriptions, and prices. 

(This is just my demon react frontend, PLEASE feel free to make yours look nice instead of plaintext)

<img width="275" alt="Screenshot 2025-02-24 at 12 17 56 AM" src="https://github.com/user-attachments/assets/917793d9-3ff8-4117-866f-9d424e665df3" />


Digital Code Inventory: Each product can have multiple associated digital codes that get marked as sold upon purchase.

Shopping Cart: Add products to cart, update quantities, remove items, or clear the entire cart.

<img width="266" alt="Screenshot 2025-02-24 at 12 32 50 AM" src="https://github.com/user-attachments/assets/ab1d65e9-4ea1-41d3-ba78-6817824e1553" />

Order Workflow: Once an order is placed, the associated digital codes are flagged as sold and the user receives them.

Basic Admin Capabilities: Admin can fetch and manage users


## Getting Started:

Run git clone https://github.com/yourusername/digitalstore.git

Run GenerateJwtSecret.java to get a fresh 256-bit secret.

Set up MySQL (via Docker Compose): docker-compose up -d



Below is my Docker Compose file
```
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: mysql_db
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: 
      MYSQL_DATABASE: 
      MYSQL_USER: 
      MYSQL_PASSWORD: 
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
#adminer is optional but I find it helpful
  adminer:
    image: adminer
    container_name: adminer
    restart: always
    ports:
      - "8081:8080"

volumes:
  mysql_data:
```
## What DB should look like

<img width="1003" alt="Screenshot 2025-02-23 at 11 39 55 PM" src="https://github.com/user-attachments/assets/631796d1-50fa-4a3b-a5de-05f7363a14e0" />

## Set up applications.properties (yml)
```
#App Name
spring.application.name=codeshop

#MySQL Database Connection (Use Docker Service Name)
spring.datasource.url=jdbc:mysql://mysql_db:3306/codeshop
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver



logging.level.org.springframework.security=DEBUG
#application.properties

#tempJWTSecret

#application.properties
mystore.codeshop.jwtSecret=""
mystore.codeshop.jwtExpirationMs=86400000


stripe.api.key=sk_test_YourSecretKeyHere
stripe.success.url=https://yourdomain.com/success
stripe.cancel.url=https://yourdomain.com/cancel
stripe.webhook.secret=your_secret_key


#Auto-creation of Tables Based on Entity Classes
spring.jpa.hibernate.ddl-auto=update

#Log SQL Queries (For Debugging)
spring.jpa.show-sql=true

#Reduce Logging Noise
logging.level.root=warn
```



## Endpoints

### Testers for role management and JWT auth
GET /api/test/all (Public)
GET /api/test/user (User only)
GET /api/test/admin (Admin and user acess only)


### POST /api/auth/signup 
Payload - 
```
{ "username": "testUser",
  "email": "testUser@mail.com",
  "password": "123456",
  "role": [] (blank for User, or define "user" "admin" etc
  }
```
### POST /api/auth/signin
Returns Token, ID, Email, Role

<img width="1074" alt="Screenshot 2025-02-23 at 11 37 10 PM" src="https://github.com/user-attachments/assets/ac6c7448-974d-4a60-ac39-352ccbe88145" />

Payload -
```
{
  "username": "testUser",
  "password": "123456"
}
```

## #DEL /api/users/id/{}
Deletes User by ID

