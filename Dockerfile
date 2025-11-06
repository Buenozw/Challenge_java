# Etapa 1: build
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o pom.xml e a pasta src explicitamente da raiz do repositório
RUN mvn dependency:go-offline
COPY . .

RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]

