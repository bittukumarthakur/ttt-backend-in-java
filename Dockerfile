FROM openjdk:21-slim as builder

WORKDIR /app
COPY . .
RUN  ./gradlew bootJar

FROM openjdk:21-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar ttt.jar
CMD ["java", "-jar", "ttt.jar"]