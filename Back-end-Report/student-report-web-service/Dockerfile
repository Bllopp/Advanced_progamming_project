FROM maven:latest AS BUILD

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src src

COPY src/main/resources/application.properties src/main/resources/

RUN mvn package -DskipTests

FROM openjdk:17

COPY --from=build /app/target/student-report-web-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]