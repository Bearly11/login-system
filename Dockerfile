FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

COPY login-app/pom.xml  ./login-app/
COPY login-command/pom.xml ./login-command/

RUN mvn dependency:go-offline -B

COPY login-app/src ./login-app/src
COPY login-command/src ./login-command/src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/login-app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
