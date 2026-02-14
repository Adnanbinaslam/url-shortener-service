# ---------- BUILD STAGE ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# copy project files
COPY pom.xml .
COPY src ./src

# build jar
RUN mvn clean package -DskipTests


# ---------- RUN STAGE ----------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Render provides PORT env variable
ENV PORT=10000
EXPOSE 10000

# activate prod profile
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","app.jar"]
