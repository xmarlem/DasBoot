FROM openjdk:8u171-jdk-slim-stretch as BASE


RUN apt-get update && apt-get install -y wget

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

RUN apt-get install -y maven

# Add a volume pointing to /tmp
VOLUME /tmp

COPY . .

ARG JAR_FILE=target/das-boot-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} das-boot-0.0.1-SNAPSHOT.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080


#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev",  "-jar","/das-boot-0.0.1-SNAPSHOT.jar"]

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=${ENVIRONMENT}", "-jar","/das-boot-0.0.1-SNAPSHOT.jar"]

#ENTRYPOINT ["./start.sh"]
