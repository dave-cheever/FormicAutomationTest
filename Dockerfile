# Use maven 3.6.0 with JDK 8 as the base image
FROM maven:3.6.0-jdk-8

# Set the working directory in the container to /app
WORKDIR /app

# Copy the pom.xml file into the working directory
COPY pom.xml .

# Download all required dependencies into one layer
RUN mvn dependency:go-offline -B

# Copy your source code into the container
COPY src ./src

# Build the project
RUN mvn package

# Expose the port your app runs on
EXPOSE 8080

# The command to run your application
CMD ["mvn", "exec:java"]
