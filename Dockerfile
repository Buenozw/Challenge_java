# Etapa 1: build
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Copia o pom.xml e a pasta src explicitamente da raiz do repositório
RUN mvn apt-get update
RUN mvn apt-get install openjdk-21-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY --from=build / target/deploy_reder-1.0.0.jar/app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]

