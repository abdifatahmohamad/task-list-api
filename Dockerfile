#FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/task-list-api-0.0.1-SNAPSHOT.jar task-list-api.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","task-list-api.jar"]

#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /task-list-api
COPY . /task-list-api/
RUN mvn clean package

#
# Package stage
#
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /task-list-api/target/*.jar /task-list-api/task-list-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","task-list-api.jar"]