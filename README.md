# DasBoot
poc for testing environments with Jenikins, Docker, ...terraform...Azure

The test spring boot application has got two profiles: DEV and TEST.


DEV environment:

- H2 in-memory database
- flyway framework (for db migration) --> migration files stored in resources/db/migration


    --> Unit Testing: ./mvn -Dtest=DasBootApplicationTests test




TEST environment (Integration Testing):

- Postgres --> user: postgres, password: postgres@123, ...
- flyway: disabled 


