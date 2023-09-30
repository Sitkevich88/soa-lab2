FROM openjdk:11-jre-slim
WORKDIR /app
COPY build/libs/util_service-0.0.1.jar /app/app.jar
EXPOSE 7008
CMD ["java", "-jar", "app.jar"]