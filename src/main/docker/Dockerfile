# Use an official OpenJDK runtime as a parent image
FROM openjdk:22-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target directory to the container
COPY ../../target/*.jar /app/bookstore.jar

# Expose the port the app runs on
EXPOSE 8090

# Run the JAR file
ENTRYPOINT ["java", "-jar", "bookstore.jar"]