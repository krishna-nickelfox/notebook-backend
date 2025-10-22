# Stage 1: Use OpenJDK 17 slim (lightweight, reliable)
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the built JAR into container
# Make sure you have run ./gradlew bootJar before this
COPY build/libs/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Environment variables placeholder (can be overridden on Render)
# Example for Postgres:
# ENV SPRING_DATASOURCE_URL=jdbc:postgresql://host:port/dbname
# ENV SPRING_DATASOURCE_USERNAME=youruser
# ENV SPRING_DATASOURCE_PASSWORD=yourpassword

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
