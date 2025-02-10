# Receipt Processor - Step-by-Step Guide

## 📌 Project Overview

The **Receipt Processor** is a Spring Boot-based REST API designed to process receipts and calculate points based on predefined rules. It includes:

✅ Full **exception handling** with proper validations.\
✅ **100% unit test coverage** using JUnit & Mockito.\
✅ **Dockerized setup** for easy deployment.\
✅ **Comprehensive API documentation** for easy integration.\
✅ **Currently stores data locally using a HashMap**, with plans to integrate a database for persistence in future updates.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3+**
- **Spring Web (REST API)**
- **JUnit & Mockito** for complete test coverage
- **Docker** for containerization
- **Maven** for dependency management

---

## 🏗️ Project Setup - Step by Step

### **1️⃣ Clone the Repository**

```sh
git clone https://github.com/rishwa344/Receipt_Processor
cd Receipt_Processor
```

### **2️⃣ Build the Project**

Ensure you have **Java 17+** and **Maven** installed:

```sh
mvn clean package
```

### **3️⃣ Run Locally (Without Docker)**

```sh
mvn spring-boot:run
```

OR, using the JAR file:

```sh
java -jar target/receipt-processor-0.0.1-SNAPSHOT.jar
```

### **4️⃣ Running with Docker**

#### **Build the Docker Image**

```sh
docker build -t receipt-processor .
```

#### **Run the Container**

```sh
docker run -p 8080:8080 receipt-processor
```

#### **Access the API**

```
http://localhost:8080
```

---

## 📌 API Endpoints

### **Submit a Receipt**

**Endpoint:** `POST /receipts/process`\
**Request:**

```json
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}
```

**Terminal Command to Test:**

```sh
curl -X POST "http://localhost:8080/receipts/process" -H "Content-Type: application/json" -d '{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    { "shortDescription": "Mountain Dew 12PK", "price": "6.49" },
    { "shortDescription": "Emils Cheese Pizza", "price": "12.25" },
    { "shortDescription": "Knorr Creamy Chicken", "price": "1.26" },
    { "shortDescription": "Doritos Nacho Cheese", "price": "3.35" },
    { "shortDescription": "Klarbrunn 12-PK 12 FL OZ", "price": "12.00" }
  ],
  "total": "35.35"
}'
```

**Response:**

```json
{
  "id": "ad0a37d0-2642-4542-9986-7f3e60737689"
}
```

### **Get Receipt Points**

**Endpoint:** `GET /receipts/{id}/points`\
**Terminal Command to Test:**

```sh
curl -X GET "http://localhost:8080/receipts/ad0a37d0-2642-4542-9986-7f3e60737689/points"
```

**Response:**

```json
{
  "points": 28
}
```

---

## ✅ Validations & Exception Handling

All input fields are validated to ensure **data integrity**, and appropriate exceptions are handled with meaningful error messages. Example:

```json
{
  "error": "Invalid total amount. Must be a positive number."
}
```

---

## 🧪 Running Tests

Run unit tests using:

```sh
mvn test
```

**Test Coverage:** ✅ 100% coverage ensured with JUnit & Mockito.

---

## 🌟 Key Features Implemented

- **Validations & Exception Handling**
- **100% Unit Test Coverage**
- **Spring Boot REST API**
- **Dockerized Deployment**
- **Scalable & Maintainable Code**
- **Currently using a HashMap for data storage, with future plans to integrate a database**

---

## 🎯 Future Enhancements

- Add **database persistence** for storing receipts.
- Implement **authentication & authorization**.
- Integrate with **external payment APIs**.

