FROM openjdk:21-slim

WORKDIR /app

COPY . .

RUN ./gradlew clean bootJar

COPY build/libs/*.jar Tic-Tac-Toe.jar

EXPOSE 8080

CMD ["java", "-jar", "Tic-Tac-Toe.jar"]