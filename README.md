# PV207-API
PV207 backend API

Project overview:
- a REST API for Drones traffic used by the jBPM implementation (run on port 8081)
- a camel integration routes running on port 8082

- Requires Java JDK 12 (with JAVA_HOME environment variable set) and Maven

### Installation
Install the project with maven
```
mvn clean install
```
Then run the app
```
mvn spring-boot:run
```