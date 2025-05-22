
# docker/postgres.dockerfile
FROM postgres:15-alpine

# Instalar extensiones adicionales
RUN apk add --no-cache \
    postgresql-contrib \
    curl

# Crear directorio para scripts de inicialización
RUN mkdir -p /docker-entrypoint-initdb.d

# Copiar scripts de inicialización personalizados
COPY database/init-scripts/ /docker-entrypoint-initdb.d/


# Variables de entorno por defecto
ENV POSTGRES_DB=tenpo_db
ENV POSTGRES_USER=tenpo_user
ENV POSTGRES_PASSWORD=tenpo_password
ENV PGDATA=/var/lib/postgresql/data/pgdata

# Exponer puerto
EXPOSE 5432

# Health check personalizado
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=5 \
    CMD pg_isready -U $POSTGRES_USER -d $POSTGRES_DB || exit 1

# Punto de entrada
ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["postgres"]
