# MCC Account Service

Microservicio de gestión de cuentas bancarias para una arquitectura de microservicios bancaria. Este servicio es responsable de crear, actualizar, listar y eliminar las cuentas de los clientes dados de alta en el sistema.

## 📋 Descripción General

El presente repositorio forma parte de un total de 6 repositorios que constituyen una **arquitectura de microservicios para un sistema bancario**. El microservicio `mcc-account-service` cumple funciones críticas de CRUD (Create, Read, Update, Delete) para la gestión de cuentas bancarias en el ecosistema de microservicios.

## 🏗️ Arquitectura de Microservicios

El microservicio se integra en la siguiente arquitectura:

![Arquitectura de Microservicios MCC](https://github.com/victordaniel123rt-lang/mcc-account-service/raw/master/docs/architecture.png)

### Microservicios Relacionados

- **mcc-gateway-service**: Punto de entrada único para todas las solicitudes
- **mcc-account-service**: Gestión de cuentas bancarias ⭐ (Este repositorio)
- **mcc-customer-service**: Gestión de clientes
- **mcc-credit-disbursement-service**: Gestión de desembolsos de crédito
- **mcc-security-service**: Autenticación y autorización
- **mcc-notification-service**: Envío de notificaciones

## 🛠️ Tecnologías

- **Java 17**: Lenguaje de programación principal
- **Spring Boot 4.0.7**: Framework web y base de datos
- **Spring Cloud 2025.1.2**: Componentes para arquitectura de microservicios
- **Spring Data JPA**: ORM con Hibernate para persistencia
- **Spring Cloud OpenFeign**: Cliente HTTP declarativo para comunicación inter-microservicios
- **Resilience4j**: Circuit breaker para resiliencia
- **Spring Boot Actuator**: Monitoreo y métricas
- **MariaDB**: Base de datos relacional
- **Lombok**: Generación automática de getters, setters y constructores
- **SpringDoc OpenAPI**: Documentación automática con Swagger/OpenAPI
- **Maven**: Gestor de dependencias y construcción

## 📦 Dependencias Principales

```xml
<!-- Spring Boot Starters -->
- spring-boot-starter-actuator
- spring-boot-starter-data-jpa
- spring-boot-starter-webmvc

<!-- Spring Cloud -->
- spring-cloud-starter-openfeign        (Comunicación inter-microservicios)
- spring-cloud-starter-circuitbreaker-resilience4j  (Resiliencia)

<!-- Base de Datos -->
- mariadb-java-client

<!-- Utilidades -->
- lombok

<!-- API Documentation -->
- springdoc-openapi-starter-webmvc-ui (v3.0.3)

<!-- Testing -->
- spring-boot-starter-actuator-test
- spring-boot-starter-data-jpa-test
- spring-boot-starter-webmvc-test
```

## 🌟 Características Principales

### Comunicación Inter-Microservicios
Utiliza **Spring Cloud OpenFeign** para comunicarse de manera declarativa con otros microservicios:
- Integración con `mcc-customer-service` para validar clientes
- Llamadas asíncronas y síncronas

### Resiliencia
Implementa **Resilience4j** como circuit breaker para:
- Tolerancia a fallos en servicios dependientes
- Manejo automático de reintentos
- Fallback automático

### Monitoreo
**Spring Boot Actuator** proporciona:
- Endpoints de salud (`/actuator/health`)
- Métricas de rendimiento
- Información del estado de la aplicación

## 🚀 Instalación y Configuración

### Requisitos Previos

- JDK 17 o superior
- Maven 3.8+
- MariaDB 10.4+
- Docker (opcional, para containerización)

### Clonar el Repositorio

```bash
git clone https://github.com/victordaniel123rt-lang/mcc-account-service.git
cd mcc-account-service
```

### Compilar el Proyecto

```bash
mvn clean install
```

### Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8081` (puerto configurable).

## 📖 Documentación de API

Una vez la aplicación esté en ejecución, puedes acceder a la documentación interactiva de la API:

- **Swagger UI**: `http://localhost:8081/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8081/v3/api-docs`

## 🐳 Docker

### Construir la Imagen

```bash
docker build -t mcc-account-service:latest .
```

### Ejecutar en Contenedor

```bash
docker run -d \
  --name mcc-account \
  -p 8081:8081 \
  -e SPRING_DATASOURCE_URL=jdbc:mariadb://mariadb:3306/accounts \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=password \
  mcc-account-service:latest
```

## 🔧 Configuración

Las propiedades de configuración se encuentran en `application.properties` o `application.yml`:

```yaml
spring:
  application:
    name: mcc-account-service
  datasource:
    url: jdbc:mariadb://localhost:3306/accounts
    username: root
    password: password
    driver-class-name: org.mariadb.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MariaDBDialect

server:
  port: 8081

# Spring Cloud OpenFeign
feign:
  client:
    config:
      mcc-customer-service:
        connectTimeout: 5000
        readTimeout: 5000

# Resilience4j Circuit Breaker
resilience4j:
  circuitbreaker:
    instances:
      mcc-customer-service:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5000
        recordExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
  retry:
    instances:
      mcc-customer-service:
        maxAttempts: 3
        waitDuration: 1000

# Actuator
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always
```

## 📁 Estructura del Proyecto

```
mcc-account-service/
├── src/
│   ├── main/
│   │   ├── java/com/vdgarcia/
│   │   │   ├── controller/      # Controladores REST
│   │   │   ├── service/         # Lógica de negocio
│   │   │   ├── repository/      # Acceso a datos (JPA)
│   │   │   ├── entity/          # Entidades JPA
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── client/          # Clientes Feign para otros microservicios
│   │   │   ├── config/          # Configuración
│   │   │   └── exception/       # Excepciones personalizadas
│   │   └── resources/
│   │       └── application.yml   # Configuración
│   └── test/
│       └── java/                # Tests unitarios e integración
├── pom.xml                       # Configuración Maven
├── Dockerfile                    # Configuración Docker
└── README.md                     # Este archivo
```

## 🧪 Testing

Ejecutar las pruebas unitarias:

```bash
mvn test
```

Ejecutar las pruebas de integración:

```bash
mvn verify
```

Ejecutar las pruebas con cobertura:

```bash
mvn test jacoco:report
```

## 📊 Monitoreo y Salud

Ver el estado de la aplicación:

```bash
curl http://localhost:8081/actuator/health
```

Ver métricas:

```bash
curl http://localhost:8081/actuator/metrics
```

## 🤝 Contribución

Las contribuciones son bienvenidas. Para reportar problemas o proponer mejoras:

1. Abre un **Issue** describiendo el problema o mejora
2. Crea una rama con tu característica (`git checkout -b feature/AmazingFeature`)
3. Realiza commit de tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un **Pull Request**

## 📝 Licencia

Este proyecto está bajo licencia por definir. Ver el archivo LICENSE para más detalles.

## 👨‍💻 Autor

- **victordaniel123rt-lang** - Desarrollador principal

## 📞 Soporte

Para reportar problemas o solicitar ayuda, abre un Issue en este repositorio.

---

**Nota**: Este microservicio forma parte de un ecosistema completo de microservicios. Asegúrate de que:
- El `mcc-customer-service` esté disponible para validaciones
- La base de datos MariaDB esté configurada correctamente
- El gateway service esté configurado para enrutar las solicitudes
