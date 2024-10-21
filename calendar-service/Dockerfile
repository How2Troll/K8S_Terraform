FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the pre-built JAR file from the build context
#COPY calendar-service/target/*.jar app.jar
COPY target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
