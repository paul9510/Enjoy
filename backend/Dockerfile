# Étape 1 : Construction (Le build)
FROM eclipse-temurin:25-jdk-jammy AS build
WORKDIR /app

# On copie les outils Maven du sous-dossier backend
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# On copie le code source et on compile le fichier .jar
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Étape 2 : Image finale légère pour l'exécution
FROM eclipse-temurin:25-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]