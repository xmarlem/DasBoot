#!/bin/bash
dockerize -wait tcp://192.168.99.102:5432 java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=test -jar /das-boot-0.0.1-SNAPSHOT.jar &