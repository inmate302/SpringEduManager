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

Quién puede acceder a qué y cómo depende de los endpoints configuradoes en SecurityConfig y como estos están protegidos en cada controlador con:

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
