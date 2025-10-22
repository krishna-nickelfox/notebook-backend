from ubuntu:latest As build

run api-get update
run api-get install openjdk-17-jdk -y
copy . .
run ./gradlew bootJar --no-daemon

from openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build build/libs/todos-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]