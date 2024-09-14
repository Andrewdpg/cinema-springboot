# Cinema Application

This is a Spring Boot application for managing cinema operations, including movie information, seat reservations, and ticketing.

## Description

The Cinema Application provides RESTful APIs to handle various cinema-related operations such as retrieving movie details, managing seat reservations, and processing ticket purchases. The application uses PostgreSQL as the database and integrates with JPA for data persistence.

## Prerequisites

- Java 17
- Gradle
- PostgreSQL

## Setup

1. **Clone the repository:**

   ```c
   git clone <repository-url>
   cd cinema
   ```
2. **Configure the database:**

   - Set the following environment variables with your database credentials:
     ```sh
     export DB_URL=<your-database-url>
     export TEST_DB_URL=<your-test-database-url>
     export DB_USER=<your-database-username>
     export DB_PASS=<your-database-password>
     ```
   - The [`application.properties`](command:_github.copilot.openRelativePath?%5B%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fa%3A%2FIcesi%2FS6%2FSemillero%2Fbackend%2Fcinema%2Fsrc%2Ftest%2Fresources%2Fapplication.properties%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%227ce99610-a301-4ec3-94cb-1a1e9732e967%22%5D "a:\Icesi\S6\Semillero\backend\cinema\src\test\resources\application.properties") file should have the following configuration:
     ```properties
     spring.application.name=cinema

     # DB Connection
     spring.datasource.url=${DB_URL}
     spring.datasource.username=${DB_USER}
     spring.datasource.password=${DB_PASS}

     # JPA Configuration
     spring.jpa.hibernate.ddl-auto=create-drop
     spring.jpa.properties.hibernate.format_sql=true
     spring.jpa.defer-datasource-initialization=true

     # SQL Initialization
     spring.sql.init.mode=always
     ```
3. **Build the project:**

   ```sh
   ./gradlew build
   ```

## Running the Application

1. **Run the application:**

   ```sh
   ./gradlew bootRun
   ```
2. **Access the application:**

   - The application will be available at [`http://localhost:8080`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fa%3A%2FIcesi%2FS6%2FSemillero%2Fbackend%2Fcinema%2Fsrc%2Fmain%2Fjava%2Fcom%2Fandrewpg%2Fcinema%2Fcontroller%2FMovieController.java%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A5%2C%22character%22%3A27%7D%7D%5D%2C%22a710f844-a235-45ac-a73f-9b19d2fda586%22%5D "Go to definition").

## Running Tests

1. **Execute tests:**
   ```sh
   ./gradlew test
   ```

## API Endpoints

### Movies

- **Get all movies:**

  ```
  GET /api/v1/movies
  ```

  **Response:**

  ```json
  [
      {
  	"movieId": "uuid",
          "title": "Movie Title",
          "director": "Movie director",
          "runtime": 120,
  	"releaseDate": "YYYY-MM-DD",
  	"image": "url"
      }
  ]
  ```

### Schedules

- **Get all schedules for a given movie:**

  ```
  GET /api/v1/schedules
  ```

  **Request Parameters:**

  - `movieId` (UUID): The movie ID.

  **Response:**

  ```json
  [
      {
          "scheduleId": "uuid",
          "movieId": "uuid",
  	"date": "YYYY-MM-DD",
  	"time": "hh:mm:ss",
  	"movieTitle": "Movies title",
  	"price": double (price per ticket)
      }
  ]
  ```

### Seats

- **Get all available seats for a given showtime:**

  ```
  GET /api/v1/seats
  ```

  **Request Parameters:**

  - `scheduleId` (UUID): The schedule ID to retrieve available seats for.

  **Response:**

  ```json
  [
      {
          "id": 1,
          "scheduleId": "uuid",
          "row": "A",
  	"column":1,
          "available": true
      }
  ]
  ```

### Tickets

- **Create a new ticket with associated reservations:**

  ```
  POST /api/v1/tickets
  ```
  **Request Body:**

  ```json
  {
      "customerEmail": "email@example.com",
      "scheduleId": "uuid",
      "reservations": [
          {
              "seatId": "uuid"
          }
      ]
  }
  ```
  **Response:**

  ```json
  {
      "ticketId": "uuid",
      "scheduleId": "uuid",
      "customerEmail": "email@example.com",
      "reservations": [
          {
              "reservationId": "uuid"
              "seatId": "uuid"
          }
      ]
  }
  ```
- **Delete a ticket with the given request:**

  ```
  DELETE /api/v1/tickets
  ```
  **Request Body:**

  ```json
  {
      "ticketId": "uuid"
  }
  ```
  **Response:**

  ```json
  {
      "message": "Ticket deleted successfully"
  }
  ```
