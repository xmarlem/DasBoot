#!/bin/bash
#dockerize -wait tcp://192.168.99.102:8080 -timeout 240s
mvn -Dtest=SeleniumContainerTest.java -Dspring.profiles.active=test verify