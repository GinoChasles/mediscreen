FROM amazoncorretto:11

WORKDIR /app


EXPOSE 8081
ARG JAR_FILE=target/patient-0.0.1-SNAPSHOT.jar


COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]