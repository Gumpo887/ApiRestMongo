# JesusMartinezAlonso-Actividad2

Proyecto Spring Boot 3.x con MongoDB y autenticación JWT. El nombre del proyecto puede cambiarse desde el `pom.xml` y el paquete base `com.jesus.actividad2`.

## Ejecución local

```bash
mvn spring-boot:run
```

MongoDB local: `mongodb://localhost:27017/transporte`.

## Ejecución con Docker

```bash
docker compose up -d --build
```

## Swagger

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Endpoints clave

- Auth: `/api/auth/register`, `/api/auth/login`
- CRUD: `/api/usuarios`, `/api/camiones`, `/api/mercancias`, `/api/inscripciones`, `/api/incidencias`

## Ejemplos rápidos

### Register
```json
{
  "nombre": "Empresa Demo",
  "email": "empresa@demo.com",
  "password": "secret123",
  "rol": "EMPRESA"
}
```

### Login
```json
{
  "email": "empresa@demo.com",
  "password": "secret123"
}
```

### Crear mercancía
```json
{
  "descripcion": "Palets de madera",
  "origen": "Madrid",
  "destino": "Valencia",
  "pesoKg": 1200,
  "fechaEntregaEstimada": "2024-12-15",
  "estado": "PENDIENTE"
}
```

### Inscribirse a mercancía (CONDUCTOR)
```json
{
  "camionId": "CAMION_ID",
  "mercanciaId": "MERCANCIA_ID"
}
```
