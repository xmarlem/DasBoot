# DasBoot
poc for testing environments with Jenikins, Docker, ...terraform...Azure

The test spring boot application has got two profiles: DEV and TEST.


<b>DEV environment</b>:

- H2 in-memory database
- flyway framework (for db migration) --> migration files stored in resources/db/migration


    --> Unit Testing: ./mvn -Dtest=DasBootApplicationTests test

A) after running Unit Tests, start the application with:</br>
    <i>java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev -jar target/das-boot-0.0.1-SNAPSHOT.jar</i>


<b>TEST environment (Integration Testing)</b>:

- Postgres --> user: postgres, password: postgres@123, ...
- flyway: disabled 

A) First start the docker compose for Postgres:
    docker-compose up (--build if required)

B) Then start the application with:</br> 
    <i>java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=test -jar target/das-boot-0.0.1-SNAPSHOT.jar</i>

