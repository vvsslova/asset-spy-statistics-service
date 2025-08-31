FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/statistics-service-0.0.1-SNAPSHOT.jar /app/statistics-service.jar

ENTRYPOINT ["java", "-jar", "/app/statistics-service.jar"]

EXPOSE 8089