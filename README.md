# TestManagement API

## Overview
This project implements a RESTful API for managing multiple-choice questions (MCQs) using Spring Boot. It provides endpoints to perform CRUD operations on MCQ questions.

### Technologies Used
- Java 21
- Spring Boot 3 (latest version)
- PostgreSQL
- JUnit 5 (for testing)

## Project Structure

The project is structured as follows:
- Controller: Handles incoming HTTP requests, processes them, and returns a response.
- Service: Contains business logic.
- Model: Defines the structure of MCQ questions.
- Repository: Interfaces for database operations (not shown explicitly in provided code).

### Setup Instructions

To run the project locally, follow these steps:

1. Set Up PostgreSQL Database
   - Create a PostgreSQL database named `TestManagementDB`.
   - Update the database configuration in `application.properties` file.

2. Run the Application
   - Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
   - Run the `TestManagementApplication.java` class as a Java application.

3. Testing the API
   - Use Postman or any API testing tool to interact with the endpoints.
   - Import the provided Postman API collection to quickly test each endpoint.
### API Endpoints

#### Create MCQ Question
- Endpoint: `POST /testmangementapi/mcqquestions`
- Creates a new MCQ question in the database.

#### Update MCQ Question
- Endpoint: `PUT /testmangementapi/mcqquestions`
- Updates an existing MCQ question in the database.

#### Get All MCQ Questions
- Endpoint: `GET /testmangementapi/mcqquestions`
- Retrieves all MCQ questions stored in the database.

#### Get MCQ Question by ID
- Endpoint: `GET /testmangementapi/mcqquestions/{id}`
- Retrieves a specific MCQ question by its unique ID.

#### Delete MCQ Question
- Endpoint: `DELETE /testmangementapi/mcqquestions/{id}`
- Deletes a specific MCQ question by its unique ID.


### Contribution
Feel free to fork this repository, propose changes via pull requests, or report issues.


