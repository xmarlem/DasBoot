FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

ARG JAR_FILE=target/das-boot-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} das-boot-0.0.1-SNAPSHOT.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev",  "-jar","/das-boot-0.0.1-SNAPSHOT.jar"]
