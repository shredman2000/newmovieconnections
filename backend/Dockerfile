FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Copy source
COPY pom.xml .
COPY src ./src

# Build application
RUN mvn clean package -DskipTests

# Run the jar
EXPOSE 8080
CMD ["java", "-jar", "target/movieconnections-0.0.1-SNAPSHOT.jar"]
