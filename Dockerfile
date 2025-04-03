#FROM eclipse-temurin:17-jdk-alpine
#
#VOLUME /tmp
#COPY build/libs/chat-app-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# Use OpenJDK as base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy built app JAR
COPY build/libs/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
