# docker/nginx.dockerfile
FROM nginx:alpine

# Instalar curl para healthcheck
RUN apk add --no-cache curl

# Crear usuario no privilegiado
RUN adduser -D -s /bin/sh nginx-user

# Crear directorios necesarios
RUN mkdir -p /var/cache/nginx /var/log/nginx /etc/nginx/ssl

# Copiar configuración personalizada
COPY nginx/nginx.conf /etc/nginx/nginx.conf
# COPY nginx/conf.d/ /etc/nginx/conf.d/

# Configurar permisos
RUN chown -R nginx-user:nginx-user /var/cache/nginx && \
    chown -R nginx-user:nginx-user /var/log/nginx && \
    chown -R nginx-user:nginx-user /etc/nginx/conf.d

# Crear página de salud personalizada
RUN echo '<!DOCTYPE html><html><head><title>Health Check</title></head><body><h1>OK</h1><p>Nginx is running</p></body></html>' > /usr/share/nginx/html/health.html

# Exponer puertos
EXPOSE 80 443

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
    CMD curl -f http://localhost:80/health.html || exit 1

# Comando de inicio
CMD ["nginx", "-g", "daemon off;"]
