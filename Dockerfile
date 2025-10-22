#from ubuntu:latest As build
#
#run apt-get update
#run apt-get install openjdk-17-jdk -y
#copy . .
#run ./gradlew bootJar --no-daemon
#
#from openjdk:17-jdk-slim
#EXPOSE 8080
#COPY --from=build build/libs/todos-0.0.1-SNAPSHOT.jar app.jar
#
#ENTRYPOINT ["java","-jar","app.jar"]

# Stage 1: Build the app
FROM ubuntu:latest AS build

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk wget unzip && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and build files first
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts settings.gradle.kts ./

# Make gradlew executable
RUN chmod +x gradlew

# Copy source code
COPY src ./src

# Build the JAR
RUN ./gradlew clean bootJar --no-daemon

# Stage 2: Runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080

# Copy the JAR from builder stage
COPY --from=build /app/build/libs/todos-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
