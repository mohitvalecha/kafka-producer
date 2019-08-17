FROM openjdk:8
ADD target/docker-kafka-producer.jar docker-kafka-producer.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "docker-kafka-producer.jar"]
