# 🍽️ Recipes API

Una REST API simple para gestionar recetas de cocina, ingredientes, pasos y categorías. Desarrollada con Java, Spring Boot, Hibernate y PostgreSQL.

🚀 **Deploy en producción**:  
https://restapi-recipes-railway-production.up.railway.app

## 📷 Diagrama ER
![Diagrama ER de App Recetas]('images/Diagrama ER de App Recetas.png')

## 📚 Tecnologías utilizadas

- Java 17
- Spring Boot
- Hibernate (JPA)
- PostgreSQL
- Docker (para despliegue)
- Railway (hosting)

## 📂 Estructura del proyecto

El proyecto sigue una arquitectura en capas, separando entidades, controladores, servicios y repositorios. También se utilizan DTOs para intercambiar datos de forma segura entre cliente y servidor.

## 📌 Endpoints disponibles

Todos los endpoints están versionados bajo el prefijo `/api/v1`.

### 📋 Recetas
- `GET /api/v1/recipes` - Listar todas las recetas
- `POST /api/v1/recipes` - Crear nueva receta
- `GET /api/v1/recipes/{id}` - Obtener receta por ID
- `PUT /api/v1/recipes/{id}` - Actualizar receta por ID
- `DELETE /api/v1/recipes/{id}` - Eliminar receta por ID

### 🧂 Ingredientes
- `GET /api/v1/ingredients` - Listar todos los ingredientes
- `GET /api/v1/ingredients/{id}` - Obtener ingrediente por ID
- `PUT /api/v1/ingredients/{id}` - Actualizar ingrediente por ID
- `DELETE /api/v1/ingredients/{id}` - Eliminar ingrediente por ID

### 🧾 Pasos
- `GET /api/v1/steps` - Listar todos los pasos
- `GET /api/v1/steps/{id}` - Obtener paso por ID
- `PUT /api/v1/steps/{id}` - Actualizar paso por ID
- `DELETE /api/v1/steps/{id}` - Eliminar paso por ID

### 🏷️ Categorías
- `GET /api/v1/categories` - Listar todas las categorías
- `GET /api/v1/categories/{id}` - Obtener categoría por ID
- `POST /api/v1/categories` - Crear nueva categoría
- `PUT /api/v1/categories/{id}` - Actualizar categoría por ID
- `DELETE /api/v1/categories/{id}` - Eliminar categoría por ID
---

## 🧪 Ejemplo de request JSON para crear una receta

```json
{
  "title": "Soufflé de queso",
  "description": "Soufflé esponjoso y dorado con queso gruyère.",
  "difficulty": "DIFICIL",
  "imageUrl": "https://images.unsplash.com/photo-1605522561233-768f753f5a4c?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
  "vegan": false,
  "portions": 4,
  "steps": [
    {
      "stepOrder": 1,
      "description": "Precalentar el horno a 200°C y engrasar moldes para soufflé."
    },
    {
      "stepOrder": 2,
      "description": "Preparar una bechamel con mantequilla, harina y leche."
    },
    {
      "stepOrder": 3,
      "description": "Agregar yemas de huevo, queso gruyère rallado y mezclar."
    },
    {
      "stepOrder": 4,
      "description": "Batir las claras a punto de nieve e incorporarlas con movimientos envolventes."
    },
    {
      "stepOrder": 5,
      "description": "Hornear por 20-25 minutos sin abrir el horno."
    }
  ],
  "ingredients": [
    {
      "name": "huevos",
      "quantity": 4,
      "unit": "unidad"
    },
    {
      "name": "queso gruyère",
      "quantity": 100,
      "unit": "g"
    },
    {
      "name": "leche",
      "quantity": 250,
      "unit": "ml"
    },
    {
      "name": "harina",
      "quantity": 40,
      "unit": "g"
    },
    {
      "name": "mantequilla",
      "quantity": 50,
      "unit": "g"
    }
  ],
  "categoryIds": [3, 5]
}
```

### 📌 Notas:

- El campo difficulty es un ENUM que puede ser: `FACIL, MEDIO, DIFICIL.`
- El campo unit es un ENUM que puede ser: `g, kg, ml, l, taza, cucharada, unidad.`
- Los categoryIds deben existir previamente en la base de datos.
- Cada receta puede tener múltiples pasos e ingredientes.

## 🛠️ Cómo ejecutar localmente

### Prerrequisitos
- Java 17+
- Maven
- PostgreSQL
- Docker (opcional)

### Clonar el proyecto

```bash
git clone https://github.com/Gherex/RecipesAPI.git
cd RecipesAPI
```
### Configurar base de datos
Editá el archivo src/main/resources/application.properties con tus credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/recipes_db
spring.datasource.username=postgres
spring.datasource.password=tu_password
```

Compilar y correr:

```bash
./mvnw spring-boot:run
```
O con Docker:

```bash
docker-compose up --build
```
## 📦 Producción
Este proyecto está desplegado en Railway con PostgreSQL y Docker.

### 🌐 API en línea:
https://restapi-recipes-railway-production.up.railway.app

### ✅ Funcionalidades actuales

- CRUD completo de recetas
- Asociación de recetas con ingredientes, pasos y categorías
- Validaciones básicas con DTOs
- Despliegue automático con Docker en Railway

## 👨‍💻 Autor
- Desarrollado por [Germán Lagger](https://www.linkedin.com/in/germanlagger/)
- GitHub: [@Gherex](https://github.com/Gherex?tab=repositories)

🧠 Inspirado en buenas prácticas de desarrollo de APIs REST con Spring Boot.
