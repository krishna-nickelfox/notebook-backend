# Stage 1: Build the app
FROM gradle:8.3-jdk17-alpine AS builder

# Set working directory
WORKDIR /app

# Copy Gradle files
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle ./gradle

# Copy source code
COPY src ./src

# Build the JAR
RUN gradle clean bootJar --no-daemon

# Stage 2: Create the final runtime image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
