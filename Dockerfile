FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/api_equipos-0.0.1.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.jar"]