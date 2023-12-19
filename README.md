## Overview

This is a Java Spring Boot application that serves as a RESTful API for a Student/Teacher Management System. 
The system allows you to store and manage information about students, teachers, groups, and courses in a PostgreSQL database. 
The application utilizes Hibernate for database interaction and provides RESTful endpoints for CRUD operations.

## Dependencies
There are several third-party dependencies used in the project. Browse the Maven pom.xml file for details of libraries and versions used.

## API Documentation
API documentation can be found at Swagger UI after the application is running.
Link to swaggerUI: http://localhost:8080/swagger-ui/index.html#/

## How to Run Locally

1. Clone the repository:

    ```bash
    git clone (https://github.com/NikolayHristovJ/LeadConsult-Interview.git)
    ```

2. Navigate to the project directory:

    ```bash
    cd LeadConsult-Interview
    ```

3. Build the project:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    java -jar target/intreview-0.0.1-SNAPSHOT.jar
    ```
   
## ERD
![ERD diagram](/erd/ERD%20Diagram%20for%20Lead_Consult%20app.png)

## Postman Collection
You can also find a postman collection file that you can import into your Postman to use this app.
[Link to Postman Collection in the repo](/postman_collection/LeadConsult.postman_collection.json)
