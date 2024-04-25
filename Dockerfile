FROM openjdk:21
WORKDIR /app
COPY ./target/miniProject-0.0.1-SNAPSHOT.jar /app/application.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/application.jar"]

