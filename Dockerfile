FROM openjdk:21-jdk
WORKDIR /app
COPY target/GitChatBridge-0.0.1.jar GitChatBridge-0.0.1.jar
EXPOSE 8080
CMD ["java", "-jar", "GitChatBridge-0.0.1.jar"]
