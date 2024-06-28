# syntax=docker/dockerfile:1

FROM openjdk:17-alpine
WORKDIR /app
COPY . .
EXPOSE 8080
CMD ["java", "-jar", "your-application.jar"]