FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY target/reviews-service-1.0.0.jar app.jar
COPY src/main/resources/db/database.sqlite ./db/database.sqlite

ENTRYPOINT ["java", "-jar", "app.jar"]
