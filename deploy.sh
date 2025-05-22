#!/bin/bash

# Script automatizado para build y deploy
set -e

# Configuración
DOCKER_USERNAME="abelmiraval"
PROJECT_NAME="tenpo-springboot-react"
VERSION="latest"

echo "🚀 INICIANDO PROCESO AUTOMATIZADO DE BUILD Y DEPLOY"
echo "=================================================="

# Colores
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

# Función para logging
log() {
    echo -e "${BLUE}[$(date +'%Y-%m-%d %H:%M:%S')]${NC} $1"
}

success() {
    echo -e "${GREEN}✅ $1${NC}"
}

error() {
    echo -e "${RED}❌ $1${NC}"
    exit 1
}

warning() {
    echo -e "${YELLOW}⚠️ $1${NC}"
}

# 1. Verificar prerequisites
log "Verificando prerequisites..."
command -v docker >/dev/null 2>&1 || error "Docker no está instalado"
command -v docker-compose >/dev/null 2>&1 || error "Docker Compose no está instalado"
success "Prerequisites verificados"

# 2. Verificar estructura del proyecto
log "Verificando estructura del proyecto..."
[ -f "docker-compose.yml" ] || error "docker-compose.yml no encontrado"
[ -d "frontend" ] || error "Directorio frontend/ no encontrado"
[ -d "backend" ] || error "Directorio backend/ no encontrado"
[ -f "frontend/package.json" ] || error "frontend/package.json no encontrado"
success "Estructura del proyecto verificada"

# 3. Crear archivo .env si no existe
if [ ! -f ".env" ]; then
    log "Creando archivo .env..."
    cat > .env << EOF
# Base de datos
DB_NAME=tenpo_db
DB_USER=tenpo_user
DB_PASSWORD=tenpo_password
DB_PORT=5432

# Backend
BACKEND_PORT=8080
JAVA_OPTS=-Xms512m -Xmx1024m

# Frontend
FRONTEND_PORT=3000

# Docker
DOCKER_USERNAME=${DOCKER_USERNAME}
PROJECT_NAME=${PROJECT_NAME}
VERSION=${VERSION}
EOF
    success "Archivo .env creado"
fi

# 4. Limpiar contenedores existentes
log "Limpiando contenedores existentes..."
docker-compose down -v --remove-orphans 2>/dev/null || true
docker system prune -f >/dev/null 2>&1 || true
success "Limpieza completada"

# 5. Build de las imágenes
log "Construyendo imágenes Docker..."

# Frontend
log "Construyendo imagen del frontend..."
docker-compose build --no-cache frontend || error "Error construyendo frontend"
success "Frontend construido"

# Backend
log "Construyendo imagen del backend..."
docker-compose build --no-cache backend || error "Error construyendo backend"
success "Backend construido"

# Database
log "Construyendo imagen de la base de datos..."
docker-compose build --no-cache database || error "Error construyendo database"
success "Database construida"

# Nginx
log "Construyendo imagen de nginx..."
docker-compose build --no-cache nginx || error "Error construyendo nginx"
success "Nginx construido"

# 6. Iniciar servicios
log "Iniciando servicios..."
docker-compose up -d || error "Error iniciando servicios"

# 7. Esperar a que los servicios estén listos
log "Esperando a que los servicios estén listos..."
sleep 10

# Verificar que los servicios están corriendo
for service in database backend frontend nginx; do
    if docker-compose ps $service | grep -q "Up"; then
        success "$service está corriendo"
    else
        warning "$service no está corriendo correctamente"
        docker-compose logs $service
    fi
done

# 8. Verificar conectividad
log "Verificando conectividad..."

# Verificar frontend
if curl -s http://localhost:3000 >/dev/null; then
    success "Frontend accesible en http://localhost:3000"
else
    warning "Frontend no accesible en puerto 3000"
fi

# Verificar backend
if curl -s http://localhost:8080/actuator/health >/dev/null 2>&1; then
    success "Backend accesible en http://localhost:8080"
else
    warning "Backend no accesible en puerto 8080"
fi

# Verificar nginx
if curl -s http://localhost:80 >/dev/null; then
    success "Nginx accesible en http://localhost:80"
else
    warning "Nginx no accesible en puerto 80"
fi

# 9. Mostrar información final
echo ""
echo "🎉 DEPLOYMENT COMPLETADO"
echo "======================="
echo ""
echo "Servicios disponibles:"
echo "• Frontend: http://localhost:3000"
echo "• Backend:  http://localhost:8080"
echo "• Nginx:    http://localhost:80"
echo "• Database: localhost:5432"
echo ""
echo "Comandos útiles:"
echo "• Ver logs:           docker-compose logs -f"
echo "• Ver logs frontend:  docker-compose logs -f frontend"
echo "• Ver logs backend:   docker-compose logs -f backend"
echo "• Parar servicios:    docker-compose down"
echo "• Reiniciar:          docker-compose restart"
echo ""

# 10. Opcional: Push a Docker Hub
read -p "¿Deseas subir las imágenes a Docker Hub? (y/n): " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]; then
    log "Subiendo imágenes a Docker Hub..."

    # Login a Docker Hub
    echo "Ingresa tus credenciales de Docker Hub:"
    docker login || error "Error en login de Docker Hub"

    # Tag y push de cada imagen
    for service in frontend backend nginx; do
        log "Subiendo $service..."
        docker tag ${PROJECT_NAME}-${service} ${DOCKER_USERNAME}/${PROJECT_NAME}-${service}:${VERSION}
        docker push ${DOCKER_USERNAME}/${PROJECT_NAME}-${service}:${VERSION} || warning "Error subiendo $service"
    done

    success "Imágenes subidas a Docker Hub"
fi

echo ""
success "¡Proceso completado exitosamente!"
