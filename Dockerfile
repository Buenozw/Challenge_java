# Etapa 1: build
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o pom.xml e baixa dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copia o JAR da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Porta da aplicação
EXPOSE 8080

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "app.jar"]

