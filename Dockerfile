# Etapa 1: build
FROM eclipse-temurin:21-jdk-jammy AS build

# Copia o pom.xml e a pasta src explicitamente da raiz do repositório
RUN mvn clean package -DskipTests
COPY . .

FROM eclipse-temurin:21-jdk-jammy

EXPOSE 8080

COPY --from=build / target/deploy_reder-1.0.0.jar/app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]

