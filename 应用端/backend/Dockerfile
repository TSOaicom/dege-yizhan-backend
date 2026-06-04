FROM eclipse-temurin:8-jdk-focal AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -P mysql -DskipTests

FROM eclipse-temurin:8-jre-focal
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN apt-get update && apt-get install -y tesseract-ocr tesseract-ocr-chi-sim && rm -rf /var/lib/apt/lists/*
EXPOSE 6789
ENTRYPOINT ["java", "-jar", "app.jar"]
