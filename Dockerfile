FROM openjdk:11
ADD /target/University.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]