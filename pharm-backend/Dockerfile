# Use an official OpenJDK runtime as a parent image
FROM openjdk:21

# Set the working directory in the container
WORKDIR /c/Users/HP/backend_pharm/pharm-backend

# Copy the packaged jar file into the container
COPY /target/"pharm-backend-0.0.1-SNAPSHOT.jar" app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
