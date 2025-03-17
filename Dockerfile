# Używamy obrazu JDK do budowania aplikacji
FROM openjdk:23
ARG JAR_FILE=target/*.jar
COPY ./target/tsk_insider_backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]