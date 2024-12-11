FROM maven:3.6.3-openjdk-17-slim as builder

WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests -DfinalName=resource-service

FROM bellsoft/liberica-openjdk-alpine:17

COPY --from=builder /app/target/resource-service.jar /app/resource-service.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/resource-service.jar"]