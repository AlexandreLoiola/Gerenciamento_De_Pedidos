FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/docker-spring-boot.jar /app
ENTRYPOINT ["java", "-jar", "docker-spring-boot.jar"]