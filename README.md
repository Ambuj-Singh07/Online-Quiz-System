# 🧠 Online Quiz System (Java + MySQL)

Welcome to the **Online Quiz System** — a Java-based desktop application that allows users to take timed quizzes, track scores, and store results using a MySQL database.

---

## 💻 Tech Stack

- **Java (Swing GUI)**
- **MySQL** for storing questions, users, and results
- **Maven** for dependency management
- **JDBC** for database connectivity

---

## 📌 Features

- 🔐 **Login & Signup** functionality
- 🧾 **Randomized Questions** from the database
- 🕒 **60-second Timer** per quiz session
- ✅ **Multiple-choice answers**
- 📊 **Score Tracking and Result Storage**
- 🧾 **Result Summary Screen**

---
## OnlineQuizSystem/
          │
          ├── src/
          │ └── main/
          │ └── java/
          │ └── org/
          │ └── example/
          │ ├── db/
          │ │ └── DBConnection.java
          │ └── gui/
          │ ├── LoginScreen.java
          │ ├── SignupScreen.java
          │ ├── QuizScreen.java
          │ ├── ResultScreen.java
          │ └── Main.java
          ├── pom.xml
          └── README.md



---

## 🛠 Setup Instructions

### ✅ 1. Prerequisites

- Java 11+ installed
- MySQL Server running
- Maven installed

---

### 🔧 2. Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE quiz_system;
USE quiz_system;

2. Create required tables:
-- Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);

-- Questions Table
CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question TEXT,
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    option4 VARCHAR(255),
    answer INT
);

-- Results Table
CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    score INT,
    time_taken INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

3. Insert some sample questions:

INSERT INTO questions (question, option1, option2, option3, option4, answer) VALUES
('Which language is used to develop Android Apps?', 'Java', 'Kotlin', 'Python', 'C++', 2),
('What is the capital of France?', 'Paris', 'London', 'Berlin', 'Madrid', 1),
('Which company created Java?', 'Oracle', 'Microsoft', 'Sun Microsystems', 'Google', 3);

## ⚙️ 3. Configure Database in Code
In DBConnection.java, update with your database credentials:

            private static final String URL = "jdbc:mysql://localhost:3306/quiz_system";
            private static final String USER = "your_username";
            private static final String PASSWORD = "your_password";
            
            
✨ Future Enhancements
Add admin panel to manage questions

Export results to PDF or Excel

Add user leaderboard

Difficulty-level quiz generation


🙋‍♂️ Author
Ambuj Kumar Singh
🛠️ Java Developer | 📘 Passionate about learning & teaching

