BookStoreServer
Technologies Used
Spring Boot: The backend is developed with Spring Boot, which makes it easy to create stand-alone, production-grade Spring based Applications.
H2 Database: The H2 Database is used for persisting data. It's a fast in-memory database that supports regular disk-based tables as well.
Hibernate with Spring Data JPA: Hibernate and Spring Data JPA are used for persistence and transaction management.
Liquibase: Liquibase is used for tracking, managing and applying database schema changes.
How to Start the Service
Ensure that you have JDK installed (recommended version: 22)
Open a terminal (or command prompt) and navigate to the project's root directory.
Run the command: ./mvnw spring-boot:run for Unix/Linux/MacOS or mvnw spring-boot:run for Windows.
The service should start and be accessible at http://localhost:8090. H2-console is available at http://localhost:8090/h2-ui during development.

some of the endpoints used can be:
- http://localhost:8090/books/{id}
- http://localhost:8090/books/id/all
