# FROM maven:3.6.0-jdk-11-slim AS build
# COPY src ./src
# COPY pom.xml .
# RUN mvn clean install

FROM openjdk:8-jdk-alpine
COPY target/Authorization-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]