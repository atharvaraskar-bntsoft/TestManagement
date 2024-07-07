# TestManagement API

## Overview
TestManagement simplifies the management of multiple-choice questions (MCQs) through a RESTful API implemented with Spring Boot. It enables CRUD operations on MCQs stored in a PostgreSQL database, providing robust features for efficient handling and ensuring streamlined operations. 

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
- Repository: Interfaces for database operations .

### Setup Instructions

To run the project locally, follow these steps:

1. Set Up PostgreSQL Database
   - Create a PostgreSQL database named `TestManagementDB`.
   - Update the database configuration in `application.properties` file.

2. Run the Application
   - Open the project in your preferred IDE (e.g., IntelliJ IDE, Eclipse).
   - Run the `TestManagementApplication.java` class as a Java application.


## Clone the repository
git clone https://github.com/atharvaraskar-bntsoft/TestManagement.git

## Navigate into the cloned directory
cd TestManagement

## Build the project using Gradle wrapper
./gradlew build

## Run the application
After building the project, you can run the application

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

### Category Management API Endpoints

#### Create Category
- Endpoint: `POST /testmangementapi/category`
- Creates a new category with the specified name and description.

#### Update Category
- Endpoint: `PUT /testmangementapi/category`
- Updates an existing category with the new name and description.

#### Get All Categories
- Endpoint: `GET /testmangementapi/category`
- Retrieves a list of all categories.

#### Get Category by ID
- Endpoint: `GET /testmangementapi/category/{id}`
- Retrieves the category details for the specified ID.

#### Delete Category
- Endpoint: `DELETE /testmangementapi/category/{id}`
- Deletes the category identified by the specified ID.

### Subcategory Management API Endpoints

#### Create Subcategory
- Endpoint: `POST /testmangementapi/subcategory`
- Creates a new subcategory with the specified details.

#### Update Subcategory
- Endpoint: `PUT /testmangementapi/subcategory`
- Updates an existing subcategory with the new details.

#### Get All Subcategories
- Endpoint: `GET /testmangementapi/subcategory`
- Retrieves a list of all subcategories.

#### Get Subcategory by ID
- Endpoint: `GET /testmangementapi/subcategory/{id}`
- Retrieves the details of a specific subcategory by its ID.

#### Delete Subcategory
- Endpoint: `DELETE /testmangementapi/subcategory/{id}`
- Deletes a specific subcategory by its ID.


### Contribution
Feel free to fork this repository, propose changes via pull requests, or report issues.


