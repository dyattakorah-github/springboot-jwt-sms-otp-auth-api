# Spring Boot JWT & SMS OTP Authentication API

A secure phone number-based authentication API built with Spring Boot, Spring Security, JWT, PostgreSQL, and SMS OTP verification using Africa's Talking.
---

## Features

- User Registration
- OTP Verification
- JWT Stateless Authentication
- SMS OTP Delivery via Africa's Talking
- Stateless Authentication using Spring Security

---

## Tech Stack

- Java 23
- Spring Boot 4.0.6
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT
- Lombok
- Africa's Talking SMS API

---


---

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/verify` | Verify OTP sent to phone |
| POST | `/api/auth/login` | Login and receive JWT token |
| POST | `/api/auth/resend-otp` | Resend OTP verification code |

---

## Request & Response Examples

### Register User

#### Request

```json
{
  "fullName": "John Doe",
  "phoneNumber": "+233241234567",
  "password": "yourpassword"
}
```

#### Response

```json
{
  "message": "Registration successful. Please verify your phone number."
}
```

---

### Verify OTP

#### Request

```json
{
  "phoneNumber": "+233241234567",
  "otp": "123456"
}
```

#### Response

```json
{
  "message": "Phone number verified successfully"
}
```

---

### Login User

#### Request

```json
{
  "phoneNumber": "+233241234567",
  "password": "yourpassword"
}
```

#### Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## Project Structure

```text
src/main/java/com/example/SpringAuth2/
│
├── config/
├── controller/
├── dto/
├── model/
├── notification/
├── repository/
├── service/
└── security/
```

---

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 23
- Maven
- PostgreSQL
- Africa's Talking Account (Sandbox for development)

---

### Installation

#### Clone Repository

```bash
git clone hgit@github.com:dyattakorah-github/springboot-jwt-sms-otp-auth-api.git
cd springboot-jwt-sms-otp-auth-api
```

---

#### Configure Your Database

```sql
CREATE DATABASE "YourDatabase";
```

---

#### Configure Environment Variables

Create an `application.properties` file inside:

```text
src/main/resources/
```

Example configuration:

```properties
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD

security.jwt.secret-key=YOUR_SECRET_KEY
security.jwt.expiration-time=3600000

africastalking.api-key=YOUR_API_KEY
africastalking.username=YOUR_USERNAME
africastalking.sender-id=YOUR_SENDER_ID
```

---

#### Run Application

```bash
./mvnw spring-boot:run
```

---

## Current Status

🚧 Active Development — core authentication flow is complete and additional enterprise-grade improvements are in progress.
### Upcoming Improvements

- Global Exception Handling
- Proper HTTP Status Mapping
- Input Validation
- Refresh Token Support
- Role-Based Authorization
- Rate Limiting
- API Versioning
- Audit Logging

---

## Version

Current Release: `v1.0`

---