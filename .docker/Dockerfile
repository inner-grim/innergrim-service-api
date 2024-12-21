# Use an official Java runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file to the container
COPY ../module-api/build/libs/module-api-0.0.1-SNAPSHOT.jar /app/module-api-0.0.1-SNAPSHOT.jar

# Specify the command to run your app
ENTRYPOINT ["java", "-Xms128m", "-Xmx256m", "-XX:+UseG1GC", "-jar", "/app/module-api-0.0.1-SNAPSHOT.jar"]