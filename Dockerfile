# Use the official Java image from DockerHub
FROM openjdk:17

# Set working directory inside the container
WORKDIR /app

# Copy the Java file into the container
COPY ArchitectureDetector.java .

# Compile Java file
RUN javac ArchitectureDetector.java

# Run the program
CMD ["java", "ArchitectureDetector"]
