# Job Application Microservices Project

This repository contains a set of microservices for a job application platform, built using Spring Boot and Spring Cloud. The project is organized into several modules, each responsible for a specific domain:

## Project Structure

- **companyms/**: Microservice for company management.
- **jobms/**: Microservice for job postings and management.
- **reviewms/**: Microservice for handling reviews.
- **gateway/**: API Gateway for routing and security.
- **ConfigServer/**: Centralized configuration server.
- **service-reg/**: Service registry for service discovery.
- **application-config/**: Shared configuration files.
- **docker-compose.yaml**: Docker Compose file to orchestrate all services.
- **k8s/**: Kubernetes deployment files.

## Technologies Used

- Java 21
- Spring Boot 3.x
- Spring Cloud 2024.x/2025.x
- Spring Data JPA
- PostgreSQL & H2 (for development/testing)
- RabbitMQ (for messaging)
- Docker & Docker Compose
- Kubernetes (optional)
- Maven

## Getting Started

### Prerequisites

- Java 21+
- Maven
- Docker & Docker Compose

### Running with Docker Compose

1. Build all services:
    ```sh
    mvn clean package
    ```
2. Start all services:
    ```sh
    docker-compose up --build
    ```

### Running Locally

Each microservice can be started individually using Maven:

cd companyms
./mvnw spring-boot:run

Repeat for jobms, reviewms, gateway, etc.

Configuration
Centralized configuration is managed in application-config/ and served by the ConfigServer.
Service discovery is handled by the Service Registry (service-reg).
API Endpoints
Each microservice exposes its own REST API, typically under /api/{service}/.... The API Gateway routes external requests to the appropriate service.

Development
Use the provided .mvnw/mvnw.cmd scripts for Maven builds.
Source code for each service is under src/main/java/ in its respective directory.
