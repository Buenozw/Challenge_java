# Etapa 1: Build com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e baixa dependências (para aproveitar cache)
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Copia o restante do projeto e empacota
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa 2: Rodar o app
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o jar gerado do build anterior
COPY --from=build /app/target/*-runner.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
