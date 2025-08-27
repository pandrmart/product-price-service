# API REST de Precios de Productos

API diseñada para consultar el precio de un producto en una fecha y hora específicas, aplicando un conjunto de reglas de
negocio.

## 💡 Visión General del Proyecto

Este proyecto implementa una API REST para gestionar precios de productos, siguiendo los principios de **Arquitectura
Hexagonal (Puertos y Adaptadores)** y **Domain-Driven Design (DDD)**. Esta elección arquitectónica promueve una **clara
separación de preocupaciones**, **bajo acoplamiento** y facilita la **mantenibilidad, testeo y escalabilidad**.

## 🛠️ Built With

- [![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white&style=flat)](https://www.java.com/es/)
- [![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white&style=flat)](https://spring.io/projects/spring-boot)
- [![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white&style=flat)](https://maven.apache.org/)
- [![JPA](https://img.shields.io/badge/JPA-blue?style=for-the-badge&logo=eclipse-ide&logoColor=white&style=flat)](https://jakarta.ee/specifications/persistence/3.0/)
- [![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white&style=flat)](https://hibernate.org/)
- [![JUnit 5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white&style=flat)](https://junit.org/junit5/)
- [![Mockito](https://img.shields.io/badge/Mockito-9C27B0?style=for-the-badge&style=flat)](https://site.mockito.org/)
- [![Karate](https://img.shields.io/badge/Karate-2C3E50?style=for-the-badge&style=flat)](https://www.karatelabs.io/)

## 🏗️ Arquitectura y Módulos

La solución está estructurada como un proyecto multi-módulo Maven, donde cada módulo representa una capa de la
arquitectura hexagonal.

> **Nota:** Este proyecto sigue un enfoque **API-first**: la API se define primero mediante OpenAPI (`openapi.yaml`) y
> se utiliza para generar interfaces, DTOs y controladores.

* **`parent`**: Módulo raíz que centraliza la gestión de dependencias (`<dependencyManagement>`) y la configuración
  global para todos los submódulos. Define la versión de Spring Boot (BOM) y otras dependencias comunes, evitando
  duplicidad de versiones en los hijos.

* **`domain`**:

    * **Propósito**: El **núcleo de la aplicación**. Contiene las **entidades de negocio**, los **Value Objects** y las
      **interfaces de los puertos** (tanto de entrada como de salida) que definen el contrato con las capas externas.

    * **Dependencias**: **No depende de ningún otro módulo** del proyecto.

* **`application`**:

    * **Propósito**: Implementa los **casos de uso (Use Cases)** de la aplicación. Contiene la lógica de negocio
      principal y orquesta el flujo de datos entre el dominio y los puertos. Lanza la excepción de dominio
      ProductPriceNotFoundException cuando no existe un precio aplicable y valida los parámetros internos mediante
      IllegalArgumentException.

    * **Dependencias**: Depende del módulo `domain`.

* **`apirest`**:

    * **Propósito**: Actúa como **adaptador de entrada REST** dentro de la arquitectura hexagonal.  
      Contiene los **controladores REST**, los **DTOs** (Data Transfer Objects) y la **definición OpenAPI** (
      `openapi.yaml`).  
      Se encarga de la validación de la entrada HTTP, de traducir las peticiones web a llamadas a los casos de uso
      (puertos de entrada) y de exponer la API como contrato.
      Traduce excepciones y errores a respuestas HTTP coherentes mediante un GlobalExceptionHandler.

    * **Dependencias**: Depende del módulo `domain`.

    * **Nota**: El **contrato OpenAPI** podría extraerse a un módulo independiente `api-contract`, lo que permitiría
      publicarlo y versionarlo de forma aislada.

* **`infra-jpa`**:

    * **Propósito**: Actúa como **adaptador de salida**. Contiene la lógica para interactuar con la base de datos usando
      **Spring Data JPA/Hibernate**.  
      Capa de infraestructura. Implementa los **puertos de salida del dominio** y se encarga de mapear entidades y
      repositorios. Originalmente se utilizó la convención de nombres de Spring Data JPA para generar queries
      automáticamente, pero el método resultó demasiado largo. Por claridad y elegancia, se optó por utilizar JPQL.

    * **Dependencias**: Depende del módulo `domain` y de las librerías de persistencia (Spring Boot Starter Data JPA, H2
      para tests).

* **`boot`**:

    * **Propósito**: Este es el módulo que **inicia la aplicación Spring Boot**.  
      Contiene la clase `main`, la configuración principal (`application.properties`), los recursos como `data.sql` para
      la carga inicial de la base de datos H2 y los **tests de integración End-to-End (E2E) con Karate**.

    * **Dependencias**: Depende de los módulos `application`, `infra-jpa` y `apirest` para arrancar y probar la
      aplicación completa, además de añadir la dependencia con la base de datos en memoria H2.  
      Además, dispone del plugin `spring-boot-maven-plugin` para empaquetar y ejecutar la aplicación como un `fat jar`.

## ⚙️ Tecnologías Utilizadas

* **Java 21**: Lenguaje de programación.

* **Maven**: Herramienta de gestión de proyectos y dependencias.

* **Spring Boot (3.x)**: Framework para el desarrollo rápido de aplicaciones web y microservicios.

* **Lombok**: Librería para reducir el boilerplate code (constructores, getters, setters, etc.) mediante anotaciones.

* **JPA / Hibernate**: Para la persistencia de datos relacionales.

* **Base de datos H2**: Base de datos en memoria utilizada para desarrollo y pruebas.

* **JUnit 5 y Mockito**: Para pruebas unitarias y de integración.

* **Karate**: Para pruebas de integración y End-to-End (E2E) de la API.

* **SLF4J y Logback**: Para una gestión de logging robusta y configurable.

## 🚀 Cómo Ejecutar el Proyecto

Asegúrate de tener **Maven** y **Java 21** instalados en tu sistema.

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/pandrmart/product-price-service.git
   cd product-price-service
   ```

2. **Configura JAVA_HOME**:
   Asegúrate de que la variable de entorno **`JAVA_HOME`** apunte a tu instalación de **Java 21** para evitar problemas
   de compilación.

3. **Compila y empaqueta el proyecto**:  
   **Nota:** Este proyecto utiliza OpenAPI Generator para crear las interfaces de la API y DTOs. Puedes generar las
   fuentes con:
   ```bash
   mvn clean generate-sources
   ```
   Desde el directorio raíz del proyecto (donde se encuentra el `pom.xml` padre), ejecuta:
   ```bash
   mvn clean install
   ```

4. **Ejecuta la aplicación**:
   Una vez compilado, puedes ejecutar la aplicación Spring Boot desde el módulo `boot`:
   ```bash
   java -jar boot/target/boot-0.0.1-SNAPSHOT.jar
   ```
   La aplicación se iniciará en `http://localhost:8080`.

## 🛢️️Base de Datos para la Prueba de Código

Este proyecto usa una base de datos **H2 en memoria** para simplificar la ejecución de la prueba de código.

* Spring Boot está configurado con `spring.jpa.hibernate.ddl-auto=create`, lo que significa que las tablas se crean
  automáticamente a partir de las entidades al iniciar la aplicación.
* Se ejecuta el archivo `data.sql` para precargar datos de prueba.
* Esta configuración es **solo para pruebas y demos**, no debe usarse en producción, ya que recrea la base de datos en
  cada arranque.

> Nota: Si deseas simular un entorno más cercano a producción, se podría reemplazar `ddl-auto=create` por un script SQL
> explícito (`schema.sql`).

## 📌 Endpoints de la API

### 1. Obtener Precio de Producto

Retorna el precio aplicable para un producto y marca en una fecha y hora determinadas.

> **Nota:** Se ha optado por usar `product-price` en singular en la URL, en lugar de seguir estrictamente la convención
> RESTful de recursos plurales, porque este endpoint devuelve **exactamente un precio** y no una colección de precios.

* **Método**: `GET`

* **URL**: `/api/v1/product-price`

* **Parámetros de Query**:

    * `productId`: ID único del producto (tipo `Long`). **Requerido y debe ser > 0**.

    * `brandId`: ID de la marca (tipo `Long`). **Requerido y debe ser > 0**.

    * `applicationDate`: Fecha y hora para la cual se busca el precio (formato `yyyy-MM-dd'T'HH:mm:ss`). **Requerido**.

* **Ejemplo de Petición**:

    ```
    http://localhost:8080/api/v1/product-price?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00
    ```

* **Ejemplo de Respuesta Exitosa (HTTP 200 OK)**:

    ```json
    {
        "productId": 35455,
        "brandId": 1,
        "priceList": 1,
        "startDate": "2020-06-14T00:00:00",
        "endDate": "2020-12-31T23:59:59",
        "price": 35.50,
        "currency": "EUR"
    }
    ```

## 🚦 Gestión de Errores

La API utiliza un **`GlobalExceptionHandler`** para proporcionar respuestas de error consistentes y seguras. Esto evita
la exposición de información sensible como las trazas de error.

* **`400 Bad Request`**: Se devuelve para **errores de validación de la API** (parámetros nulos, formato incorrecto,
  `productId`/`brandId` no válidos) gestionados por el controlador.

* **`404 Not Found`**: Se devuelve cuando la lógica de negocio (en el servicio) determina que **no se ha encontrado un
  precio** aplicable para los criterios proporcionados (`ProductPriceNotFoundException`).

* **`500 Internal Server Error`**: Se devuelve para **errores inesperados** o fallos técnicos internos del servidor (ej.
  problemas de conexión a la base de datos).

## 🧪 Testing

El proyecto cuenta con una sólida estrategia de testing, incluyendo:

* **Tests Unitarios**: Utilizando **JUnit 5 y Mockito** para verificar la lógica de negocio de los diferentes módulos.

* **Tests de Repositorio**: Se utilizan tests de Spring Boot específicos para la capa de persistencia (`@DataJpaTest`)
  para validar el comportamiento del `ProductPriceRepository`.

* **Tests de Integración**: Empleando **Karate** para asegurar que el API REST funciona correctamente de extremo a
  extremo, verificando los códigos de estado HTTP y los cuerpos de respuesta para escenarios exitosos y de error.
  Como puntualización, los cinco primeros casos de prueba del endpoint podrían compactarse usando un Scenario Outline.
  Estos tests se encuentran en el módulo **`boot`**.
    * **Ejecución**: Los tests de Karate se ejecutan automáticamente como parte del ciclo de vida de Maven (`test` o
      `verify`). Puedes ejecutarlos desde la raíz del proyecto o desde el módulo `boot` con `mvn clean verify` o
      `mvn test`. Alternativamente, puedes **ejecutar directamente `TestRunner` desde tu IDE** como una prueba JUnit
      normal.

## 📝 Documentación y Logging

* **Javadoc**: Se utiliza en interfaces (puertos) y clases públicas para documentar el propósito, parámetros, retornos
  y excepciones de los métodos, facilitando la comprensión y el mantenimiento del código. En las implementaciones se
  hereda la documentación de los métodos definida en los puertos.

* **Logging**: Implementado con **SLF4J y Logback**, con una estrategia de logging por capa:

    * **`DEBUG`**: Para trazas detalladas del flujo de ejecución en las capas `application` e `infra-jpa`.

    * **`INFO`**: Para eventos importantes de inicio/fin de operaciones de negocio.

    * **`WARN`**: Para errores de cliente (`400`, `404`) en el `GlobalExceptionHandler`, indicando uso incorrecto de la
      API.

    * **`ERROR`**: Para fallos críticos del sistema (`500`) en el `GlobalExceptionHandler` y errores técnicos en la capa
      `infra-jpa` (adaptadores), registrando la traza de error completa para depuración interna.