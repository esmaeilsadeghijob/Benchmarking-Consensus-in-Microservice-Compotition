FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY target/content-service-1.0.0.jar app.jar
COPY src/main/resources/schema.sql schema.sql

RUN apt-get update && apt-get install -y sqlite3 && \
    sqlite3 content.db < schema.sql

ENTRYPOINT ["java", "-jar", "app.jar"]
