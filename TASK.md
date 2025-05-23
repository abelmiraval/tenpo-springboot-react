
1. Agregar arquitectura de diagrama
- Agregar diagrama de secuencia
- Agregar diagrama de componentes
2. Agregar README.md
3. Agregar curl para ejecutar los servicios
4. Cobertura de sonarqube
5. Usar mapstruct
6. Entities en dominio no hay





GetAllProductsQuery: curl -v http://localhost:9010/product
GetProductByIdQuery: curl -v http://localhost:9010/product/{productId}
AddProductCommand: curl -v -X PUT http://localhost:9010/product -H "Content-Type: application/json" -d '{"name": "New Product", "unitPrice": "10.99", "description": "New Product description"}'
