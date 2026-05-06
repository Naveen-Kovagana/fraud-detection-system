# 🚨 Real-Time Fraud Detection System

An event-driven fraud detection platform built with **Spring Boot Microservices** that processes financial transactions in real time using **Kafka**, detects suspicious activity using **Redis-backed fraud rules**, and secures APIs via **JWT-based authentication and RBAC**.

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.3.5-green)
![Kafka](https://img.shields.io/badge/EventStreaming-Kafka-black)
![Redis](https://img.shields.io/badge/Cache-Redis-red)
![Security](https://img.shields.io/badge/Security-JWT-orange)
![Database](https://img.shields.io/badge/Database-PostgreSQL-blue)

---

# 🚀 Overview

This system simulates a real-world fraud detection pipeline where transaction events are published asynchronously through Kafka and analyzed by a dedicated Fraud Detection Service.

The platform follows a **microservices architecture** with secure API access, reliable event publishing using the **Outbox Pattern**, and real-time behavioral fraud analysis powered by Redis.

---

# 🧠 Key Features

- ⚡ Event-driven microservices architecture
- 📩 Kafka-based asynchronous transaction processing
- 🔄 Reliable event publishing using Outbox Pattern
- 🚨 Real-time fraud detection engine
- ⚡ Redis-backed velocity fraud checks
- 🔐 JWT Authentication & Role-Based Access Control (RBAC)
- 🔁 Refresh Token & Logout mechanism
- 🌐 API Gateway for centralized routing & security
- 🐳 Dockerized infrastructure setup
- 📊 PostgreSQL persistence for transactions & fraud alerts

---

# 🏗️ Architecture

```text
Client
   │
   ▼
API Gateway (JWT + RBAC)
   │
   ▼
Transaction Service
   │
   ├── PostgreSQL
   ├── Outbox Table
   └── Kafka Producer
            │
            ▼
        Kafka Topic
            │
            ▼
Fraud Detection Service
   │
   ├── Fraud Rule Engine
   ├── Redis Velocity Checks
   └── PostgreSQL Fraud Alerts
```

---

# ⚙️ Tech Stack

- Java 21, Spring Boot 3.3.5
- Spring Cloud Gateway
- Spring Security + JWT
- Apache Kafka
- Redis
- PostgreSQL
- Docker & Docker Compose
- Maven
- JPA / Hibernate

---

# 🔐 Security Features

- JWT-based Authentication
- Role-Based Access Control (RBAC)
- Access Token & Refresh Token Flow
- Secure Logout Handling
- Protected API Endpoints

---

# 🚨 Fraud Rules Implemented

- 💰 High Amount Detection
- 🔁 Velocity-Based Fraud Detection (Redis)
- 🌍 Foreign Currency Detection
- 🔵 Round Amount Detection

---

# ⚡ Outbox Pattern Flow

```text
Transaction Created
        │
        ▼
Save Transaction in DB
        │
        ▼
Save Event in Outbox Table
        │
        ▼
Scheduler Publishes Event to Kafka
        │
        ▼
Fraud Service Consumes Event
```

---

# 🐳 Docker Services

The project uses Docker Compose for infrastructure setup.

### Containers
- PostgreSQL
- Kafka
- Zookeeper
- Redis

---

# ▶️ Running the Project

## 1️⃣ Start Docker Containers

```bash
docker-compose up -d
```

## 2️⃣ Start Services

Run the following services individually:

- api-gateway-service
- transaction-service
- fraud-detection-service

---

# 🧪 API Testing

## 🔐 Login

```http
POST /auth/login
```

## 💳 Create Transaction

```http
POST /transactions
```

## 🚨 Get Fraud Alerts

```http
GET /fraud/alerts
```

---

# 📌 Key Concepts Covered

- Microservices Architecture
- Event-Driven Communication
- Kafka Messaging
- Outbox Pattern
- JWT Security
- RBAC
- Redis Caching
- Fraud Detection Rules
- API Gateway
- Dockerized Infrastructure

---

# 🚀 Future Enhancements

- Circuit Breaker (Resilience4j)
- Centralized Logging
- Distributed Tracing
- Kubernetes Deployment
- CI/CD Pipeline
- Monitoring & Metrics
