# Receipt_Processor

Receipt Processor - Step-by-Step Guide

📌 Project Overview

The Receipt Processor is a Spring Boot-based REST API designed to process receipts and calculate points based on predefined rules. It includes:

✅ Full exception handling with proper validations.✅ 100% unit test coverage using JUnit & Mockito.✅ Dockerized setup for easy deployment.✅ Comprehensive API documentation for easy integration.✅ Currently stores data locally using a HashMap, with plans to integrate a database for persistence in future updates.

🚀 Tech Stack

Java 17

Spring Boot 3+

Spring Web (REST API)

Spring Data JPA (if using a database)

JUnit & Mockito for complete test coverage

Docker for containerization

Maven for dependency management

🏗️ Project Setup - Step by Step

1️⃣ Clone the Repository

git clone https://github.com/your-username/receipt-processor.git
cd receipt-processor

2️⃣ Build the Project

Ensure you have Java 17+ and Maven installed:

mvn clean package

3️⃣ Run Locally (Without Docker)

mvn spring-boot:run

OR, using the JAR file:

java -jar target/receipt-processor-0.0.1-SNAPSHOT.jar

4️⃣ Running with Docker

Build the Docker Image

docker build -t receipt-processor .

Run the Container

docker run -p 8080:8080 receipt-processor

Access the API

http://localhost:8080

📌 API Endpoints

Submit a Receipt

Endpoint: POST /receipts/processRequest:

{
  "store": "Target",
  "purchaseDate": "2024-02-09",
  "total": "35.50"
}

Response:

{
  "receiptId": "123456"
}

Get Receipt Points

Endpoint: GET /receipts/{receiptId}/pointsResponse:

{
  "points": 50
}

✅ Validations & Exception Handling

All input fields are validated to ensure data integrity, and appropriate exceptions are handled with meaningful error messages. Example:

{
  "error": "Invalid total amount. Must be a positive number."
}

🧪 Running Tests

Run unit tests using:

mvn test

Test Coverage: ✅ 100% coverage ensured with JUnit & Mockito.

🌟 Key Features Implemented

Validations & Exception Handling

100% Unit Test Coverage

Spring Boot REST API

Dockerized Deployment

Scalable & Maintainable Code

Currently using a HashMap for data storage, with future plans to integrate a database

🎯 Future Enhancements

Add database persistence for storing receipts.

Implement authentication & authorization.

Integrate with external payment APIs.


