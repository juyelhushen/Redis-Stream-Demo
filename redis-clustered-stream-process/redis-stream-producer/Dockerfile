FROM openjdk:11
ADD target/docker-stream-producer.jar docker-stream-producer.jar
EXPOSE 9004
ENTRYPOINT ["java","-jar","docker-stream-producer.jar"]