# docker/frontend.dockerfile
# Etapa de construcción
FROM node:18-alpine as builder

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración de dependencias
COPY frontend/package*.json ./

# Instalar dependencias
RUN npm ci --silent

# Copiar código fuente
COPY frontend/ .

# Construir la aplicación
RUN npm run build

# Etapa de producción con nginx
FROM nginx:alpine

# Instalar curl para healthcheck
# RUN apk add --no-cache curl

# Crear usuario no privilegiado
RUN addgroup -g 1001 -S nodejs && \
    adduser -S react -u 1001 -G nodejs

# Eliminar configuración por defecto de nginx
# RUN rm /etc/nginx/conf.d/default.conf

# Copiar archivos construidos
COPY --from=builder /app/dist /usr/share/nginx/html

# Copiar configuración personalizada de nginx
# COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf

# Crear directorio para logs y cache
RUN mkdir -p /var/cache/nginx /var/log/nginx && \
    chown -R react:nodejs /var/cache/nginx && \
    chown -R react:nodejs /var/log/nginx && \
    chown -R react:nodejs /usr/share/nginx/html

# Exponer puerto
EXPOSE 80

# Health check
# HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
#     CMD curl -f http://localhost:80 || exit 1

# Comando de inicio
CMD ["nginx", "-g", "daemon off;"]
