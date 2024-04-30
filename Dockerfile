FROM amazoncorretto:17
ARG JAR_FILE=target/*.jar
COPY . .
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]

