FROM openjdk:21-slim

WORKDIR /app

COPY . .

RUN ./gradlew clean bootJar

#COPY  app/build/libs/Tic-Tac-Toe-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", " build/libs/Tic-Tac-Toe-0.0.1-SNAPSHOT.jar"]