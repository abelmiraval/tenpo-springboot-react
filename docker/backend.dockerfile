# docker/backend.dockerfile
# Imagen base para Spring Boot con Java 21
FROM openjdk:21-jdk-slim as builder

# Instalar Maven y herramientas necesarias
RUN apt-get update && apt-get install -y \
    maven \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración Maven
COPY backend/pom.xml .
COPY backend/src ./src

# Compilar la aplicación
RUN mvn clean package -DskipTests

# Etapa de producción
FROM openjdk:21-jdk-slim

# Instalar curl para healthcheck
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Crear usuario para ejecutar la aplicación
RUN groupadd -r spring && useradd -r -g spring spring

# Establecer directorio de trabajo
WORKDIR /app

# Crear directorio para logs
RUN mkdir -p /app/logs && chown -R spring:spring /app

# Copiar el JAR compilado
COPY --from=builder /app/target/*.jar app.jar

# Copiar archivos de configuración
COPY backend/src/main/resources/application*.yml ./config/

# Cambiar propietario
RUN chown -R spring:spring /app

# Cambiar a usuario no privilegiado
USER spring

# Exponer puerto
EXPOSE 8080

# Variables de entorno
ENV SPRING_PROFILES_ACTIVE=docker
ENV JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseContainerSupport"

# Punto de entrada
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1
