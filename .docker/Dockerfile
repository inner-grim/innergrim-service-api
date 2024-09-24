# Use an official Java runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file to the container
COPY ../module-app/build/libs/module-app-0.0.1-SNAPSHOT.jar /app/module-app-0.0.1-SNAPSHOT.jar

# Specify the command to run your app
ENTRYPOINT ["java", "-jar", "/app/module-app-0.0.1-SNAPSHOT.jar"]