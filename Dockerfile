FROM openjdk:8
FROM openjdk:19-jdk
EXPOSE 8081
ADD target/cymplyfiapp.jar cymplyfiapp.jar
ENTRYPOINT ["java","-jar","/cymplyfiapp.jar"]
