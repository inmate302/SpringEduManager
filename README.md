# SpringEduManager

Una aplicación de administración académica para un bootcamp escrita en Java
usando una arquitectura basada en MVC con Springboot, Springboot Security para autorización y autenticación, Spring Data JPA y h2 para persistencia y finalmente thymeleaf para las vistas al mismo tiempo que exponemos una api REST.

## Guía rápida

### Pre-Requisitos

- Java SDK 21
- Maven 3.9.16

Desde tu línea de comandos, clona el repositorio:

```
git clone https://github.com/inmate302/SpringEduManager.git
```


Desde el directorio raíz del proyecto puedes correr el proyecto usando maven:


```
mvn spring-boot:run
```

O si prefieres puedes abrir la carpeta del proyecto (donde se encuentra el archivo pom.xml, no el código fuente src) en tu IDE de preferencia. 
Abre el archivo 'SpringEduManagerApplication.java' desde el explorador y:

- Haz click derecho y luego click en SpringEduManagerApplication.main()
 o presiona Ctrl + Shift + F10 (IntelliJ IDEA)
- Click en el botón Run ▶️ (Eclipse, VS Code)

Luego desde el navegador accedes desde la url:

```
http://localhost:8080/
```


La cuál te redireccionará a la pantalla de login-
Puedes ingresar usando las credenciales de demostración:

```
admin@bootcamp.net / admin2026 
alumno@bootcamp.net / 1234
```


## Premisa de caso

Se requiere de una webapp educativa que permita gestionar estudiantes, cursos y evaluaciones. Por lo tanto requeriremos los siguientes Modelos:

- Curso
- Estudiante
- Evaluacion

Estos son los modelos de dominio que manejarán nuestra lógica de negocio y darán origen a nuestras entidades, controladores y servicios.

También se considera dentro de los modelos el enum Rol, del cuál se desprenden los roles ADMIN y USER los cuáles tomarán importancia cuando hagamos uso de Spring Security.

### Modelos de dominio y entidades
#### Curso
    - id
    - nombre
    - descripcion
    - fechaInicio
    - fechaFin
    - evaluaciones
    - estudiantes

#### Estudiante
    - id
    - nombre
    - email
    - password
    - rol (USER asginada por defecto)
    - fechaRegistro

#### Evaluacion
    - id
    - nombre
    - descripcion
    - objetivo
    - ponderacion
    - fecha
    - estado
    - curso

### Controladores
    - AuthController -> maneja inicio de sesión
    - DashboardController -> maneja acceso al resto de las vistas
    - CursoController -> acceso a vista de cursos
    - EstudianteController -> acceso a vista de estudiantes
    - EvaluacionController -> acceso a vista de evaluaciones

Desde el inico de sesión, cada controlador permite acceso a su modelo o vista a través de su respectivo endpoint:
- /cursos
- /estudiantes
- /evaluaciones

En su respectiva vista:

    │       ├── vista
    │       │   ├── form.html <- formulario de creación
    │       │   └── list.html <- modelo y sus elementos

Quién puede acceder a qué y cómo depende de los endpoints configurados en SecurityConfig y como estos están protegidos en cada controlador con:

```Java
@PreAuthorize("hasRole('ADMIN')")
``` 



|       | Curso | Estudiante | Evaluacion |
|-------|-------|------------|------------|
| ADMIN |  RW   |     RW     |     RW     |
| USER  |  R    |     X      |     R      |

R = Lectura
W = Escritura

## REST API
De la misma manera se protegen los endpoints en los controladores rest para exponer Cursos y Estudiantes para operaciones CRUD con
mapeos Get, Post, Put y delete. Evaluaciones también se expone con un controlador REST pero tan sólo la función get y así brindar una lista de evaluaciones.

Los endpoints serían:
```
/api/v1/cursos
/api/v1/estudiantes
/api/v1/evaluaciones
```

Puedes probar los endpoints en tu línea de comandos usando curl:

### Cursos
#### Listar todos los cursos
```
curl -i -u 'admin@bootcamp.net:admin2026' \
  http://localhost:8080/api/v1/cursos
```

#### Obtener un curso
```
curl -i -u 'admin@bootcamp.net:admin2026' \
  http://localhost:8080/api/v1/cursos/1
```

#### Crear un curso
```
curl -i -X POST \ 
 -u 'admin@bootcamp.net:admin2026' \
 -H "Content-Type: application/json" \
 -d '{
"nombre": "Programación Java",
"descripcion": "Curso de Java y Spring Boot",
"fechaInicio": "2026-09-11",
"fechaFin": "2026-10-11"
}' \
http://localhost:8080/api/v1/cursos
```

#### Actualizar un curso
```
curl -i -X PUT \
  -u 'admin@bootcamp.net:admin2026' \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Programación Java",
    "descripcion": "Curso de Java y Spring Boot",
    "fechaInicio": "2026-09-22",
    "fechaFin": "2026-11-22"
  }' \
  http://localhost:8080/api/v1/cursos/1

```

#### Borrar un curso
```
curl -i -X DELETE \
  -u 'admin@bootcamp.net:admin2026' \
  http://localhost:8080/api/v1/cursos/1
```

### Estudiantes

#### Listar todos los estudiantes
```
curl -i -u 'admin@bootcamp.net:admin2026' \
  http://localhost:8080/api/v1/estudiantes
```

#### Obtener un estudiante
```
curl -i -u 'admin@bootcamp.net:admin2026' \
  http://localhost:8080/api/v1/estudiantes/1
```

#### Crear un estudiante
```
curl -i -X POST \
  -u 'admin@bootcamp.net:admin2026' \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Ana",
    "email": "ana@bootcamp.net",
    "password": "ana2026",
    "fechaRegistro": "2026-08-03"
  }' \
  http://localhost:8080/api/v1/estudiantes
```

#### Actualizar un estudiante
```
curl -i -X PUT \
  -u 'admin@bootcamp.net:admin2026' \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Ana",
    "email": "ana@bootcamp.net",
    "password": "ana2026",
    "fechaRegistro": "2026-08-03"
  }' \
  http://localhost:8080/api/v1/estudiantes/1
```

#### Borrar a un estudiante
```
curl -i -X DELETE \
  -u 'admin@bootcamp.net:admin2026' \
  http://localhost:8080/api/v1/estudiantes/1
```

### Evaluaciones

#### Listar todas las evaluaciones
```
curl -i -u 'admin@bootcamp.net:admin2026' \
  http://localhost:8080/api/v1/evaluaciones
```

## Estructura de archivos
```
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── cl
│   │   │       └── inmate302
│   │   │           └── springedumanager
│   │   │               ├── api
│   │   │               │   ├── CursoRestController.java
│   │   │               │   ├── EstudianteRestController.java
│   │   │               │   └── EvaluacionRestController.java
│   │   │               ├── config
│   │   │               │   └── DataInitializer.java
│   │   │               ├── domain
│   │   │               │   ├── Curso.java
│   │   │               │   ├── Estudiante.java
│   │   │               │   ├── Evaluacion.java
│   │   │               │   └── Rol.java
│   │   │               ├── dto
│   │   │               │   ├── CursoDTO.java
│   │   │               │   ├── EstudianteDTO.java
│   │   │               │   └── EvaluacionDTO.java
│   │   │               ├── repository
│   │   │               │   ├── CursoRepository.java
│   │   │               │   ├── EstudianteRepository.java
│   │   │               │   └── EvaluacionRepository.java
│   │   │               ├── security
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── service
│   │   │               │   ├── CursoService.java
│   │   │               │   ├── EstudianteService.java
│   │   │               │   └── EvaluacionService.java
│   │   │               ├── SpringEduManagerApplication.java
│   │   │               └── web
│   │   │                   ├── AuthController.java
│   │   │                   ├── CursoController.java
│   │   │                   ├── DashboardController.java
│   │   │                   ├── EstudianteController.java
│   │   │                   └── EvaluacionController.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       │   └── css
│   │       │       └── styles.css
│   │       └── templates
│   │           ├── cursos
│   │           │   ├── form.html
│   │           │   └── list.html
│   │           ├── dashboard.html
│   │           ├── estudiantes
│   │           │   ├── form.html
│   │           │   └── list.html
│   │           ├── evaluaciones
│   │           │   ├── form.html
│   │           │   └── list.html
│   │           ├── fragments.html
│   │           └── login.html
```

