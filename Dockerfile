# Etapa 1: build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e a pasta src explicitamente da raiz do repositório
COPY ./pom.xml /app/pom.xml
COPY ./src /app/src

RUN mvn -f /app/pom.xml dependency:go-offline
RUN mvn -f /app/pom.xml clean package -DskipTests

# Etapa 2: execução
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

