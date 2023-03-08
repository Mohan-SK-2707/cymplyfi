FROM openjdk:8
FROM openjdk:19-jdk
EXPOSE 8081
ADD target/cymplyfiapp-0.0.1.jar cymplyfiapp-0.0.1.jar
ENTRYPOINT ["java","-jar","/cymplyfiapp-0.0.1.jar"]
