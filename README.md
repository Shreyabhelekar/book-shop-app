## Book Shop Application

This is a Java Servlet-based web application that allows users to register, view, search, edit, and delete books in a bookstore. It uses HTML for UI, JDBC for database connectivity, and runs on Apache Tomcat.

### Features:
  Register new books with name, edition, and price

  View all registered books in a clean table

  Search books by name and/or edition

  Edit existing book details

  Delete books

  Responsive & interactive UI using Bootstrap

### Technologies Used
Java (Servlets)

HTML

JDBC (MySQL)

Apache Tomcat (v10+)

Eclipse IDE

MySQL Database

Bootstrap 5 (for UI)

### Setup Instructions
1. Clone the Repository
   ```bash
   git clone https://github.com/Shreyabhelekar/book-shop-app.git
   cd book-shop-app
   ```
2. Create MySQL Database
   ```bash
    CREATE DATABASE book;
  
    USE book;
    
    CREATE TABLE BOOKDATA (
        ID INT PRIMARY KEY AUTO_INCREMENT,
        BOOKNAME VARCHAR(100),
        BOOKEDITION VARCHAR(50),
        BOOKPRICE FLOAT
    );
   ```
3. Configure Project
   Import the project into Eclipse as a Dynamic Web Project

   Make sure Tomcat server is added to Eclipse

   Update MySQL credentials in servlets:
   ```bash
   Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "your_password");
   ```
 4. Build & Run
    Right-click the project → Run on Server

    Open browser and go to:
    ```bash
      http://localhost:8080/BookWebApp/home.html
    ```

###  Project Structure  
```bash
BookWebApp/
│
├── src/
│   └── com.book.servlet/
│       ├── RegisterServlet.java
│       ├── BookListServlet.java
│       ├── EditServlet.java
│       ├── EditScreenServlet.java
│       ├── DeleteServlet.java
│       └── SearchServlet.java
│
├── WebContent/
│   ├── home.html
│   ├── search.html
│   ├── bookList.html
│   └── css/
│       └── bootstrap.css
│
└── web.xml (optional if using @WebServlet)
```
