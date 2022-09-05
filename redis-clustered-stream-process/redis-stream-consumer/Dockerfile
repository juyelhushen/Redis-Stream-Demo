FROM openjdk:11
ADD target/docker-stream-consumer.jar docker-stream-consumer.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","docker-stream-consumer.jar"]