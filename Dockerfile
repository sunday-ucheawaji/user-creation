# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copy Maven wrapper and set permissions
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy pom.xml separately to leverage Docker caching
COPY pom.xml .

# Download dependencies before copying source code
RUN ./mvnw dependency:go-offline

# Copy the entire project source
COPY . .

# Build the project
RUN ./mvnw package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
