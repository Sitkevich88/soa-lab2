FROM openjdk:11-jre-slim
WORKDIR /app
COPY build/libs/gateway-0.0.1.jar /app/app.jar
EXPOSE 8765
CMD ["java", "-jar", "app.jar"]