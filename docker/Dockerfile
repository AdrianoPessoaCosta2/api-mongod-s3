FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/apimongods3-1.0.0.jar app.jar

ENV AWS_ACCESS_KEY_ID=test
ENV AWS_SECRET_ACCESS_KEY=test
ENV AWS_REGION=ap-northeast-1

EXPOSE 8080 5005

CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
