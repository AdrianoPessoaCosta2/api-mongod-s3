FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/apimongods3-1.0.0.jar /app/app.jar

ENV AWS_ACCESS_KEY_ID=test
ENV AWS_SECRET_ACCESS_KEY=test
ENV AWS_REGION=us-east-1

EXPOSE 8080

CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app/app.jar"]

