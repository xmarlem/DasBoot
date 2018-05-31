#!/bin/sh
java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$ENVIRONMENT -jar /das-boot-0.0.1-SNAPSHOT.jar
