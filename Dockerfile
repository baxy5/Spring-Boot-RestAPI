# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy maven wrapper files
COPY .mvn .mvn
COPY mvnw .

# Copy the pom.xml
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the source code into the working directory
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port 8080 to the outside
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/simpleRestApi-0.0.1-SNAPSHOT.jar"]