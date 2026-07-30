<div align="center">
<img src="images/logo2.png" width = "150" style="border-radius: 15%" align="center" hspace="20" height="150">

<h1><i> Veterinaria API ── Spring Boot ౨ৎ⋆˚｡⋆ </i></h1>
<h3><small> by: Michelle Cámara </small></h3>
</div>

## ── ꩜ Objetivo 

Desacoplar la estructura de la base de datos de la logica de negocio mediante modelos de dominio e interfaces mapeadoras.

## ── 💻 Tecnologías

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- MapStruct
- Gradle
- Springdoc OpenAPI / Swagger UI
- Spring Security
- JWT

## ── 📁 Estructura del proyecto

```text
src/main/java/com/veterinaria/gestion_mascotas
├── domain
│   ├── model
│   ├── repository
│   └── service
├── persistence
│   ├── repository implementations
│   ├── crud
│   ├── entity
│   └── mapper
└── web
    ├── controller
    ├── dto
    └── security
```

## ── ★ Dominio

Las clases del modelo de dominio están en `domain.model` y no contienen anotaciones de JPA.

Modelos actuales:

- `Pet`
- `Owner`
- `Vet`
- `Appointment`

`Owner` contiene dos datos relacionados con mascotas:

- `mascotas`: lista de mascotas que puede recibirse al crear un tutor con cascade.
- `mascotaIds`: lista de IDs que se devuelve al consultar tutores.

## ── ★ Repositorios de dominio

Las interfaces de repositorio estan en `domain.repository` y trabajan con modelos de dominio, no con entidades JPA.

Repositorios actuales:

- `OwnerRepository`
- `PetRepository`
- `VetRepository`
- `AppointmentRepository`

Estas interfaces son contratos de dominio. No extienden `CrudRepository` directamente.

## ── ★ Implementaciones de persistencia

Las implementaciones concretas están en `persistence` y usan `@Repository`.

Implementaciones actuales:

- `MascotaRepository implements PetRepository`
- `TutorRepository implements OwnerRepository`
- `VeterinarioRepository implements VetRepository`
- `CitaRepository implements AppointmentRepository`

Estas clases conectan la capa de dominio con Spring Data JPA usando los repositorios `CrudRepository` y los mappers de MapStruct.

Ejemplo de flujo:

```text
PetService -> PetRepository -> MascotaRepository -> MascotaCrudRepository
```

Esto permite que los servicios trabajen con modelos de dominio y no con entidades JPA.

## ── 📈 Persistencia

Las entidades JPA están en `persistence.entity`.

Entidades actuales:

- `Mascota`
- `Tutor`
- `Veterinario`
- `Cita`

Repositorios CRUD actuales:

- `MascotaCrudRepository`
- `TutorCrudRepository`
- `VeterinarioCrudRepository`
- `CitaCrudRepository`

## ── 🗺️ Mappers

Los mappers están en `persistence.mapper` y usan MapStruct con:

```java
@Mapper(componentModel = "spring")
```

Mappers actuales:

- `PetMapper`
- `OwnerMapper`
- `VetMapper`
- `AppointmentMapper`

Los mappers convierten entre entidades JPA y modelos de dominio.

Ejemplo:

```text
Mascota <-> Pet
Tutor <-> Owner
Veterinario <-> Vet
Cita <-> Appointment
```

Cuando los nombres de campos son diferentes, se usa `@Mapping`.

Ejemplo:

```java
@Mapping(source = "idMascota", target = "mascotaId")
```

Cuando se convierte de dominio a entidad, se usa `@InheritInverseConfiguration` para reutilizar el mapeo en sentido inverso.

En el caso de `Owner`, el mapper convierte los datos normales y tambien permite convertir:

```text
Owner.mascotas -> Tutor.mascotas
```

Para mantener el codigo mas claro, `mascotaIds` no se llena directamente con MapStruct. Se llena manualmente en `TutorRepository`, recorriendo las mascotas del tutor y agregando cada `idMascota` a la lista de respuesta.

## ── 🌐 Controladores

Los controladores están en `web.controller` y exponen la API REST.

Controladores actuales:

- `PetController`
- `OwnerController`
- `VetController`
- `AppointmentController`
- `AuthController`

Los endpoints usan `ResponseEntity` para responder con codigos HTTP como:

- `200 OK`
- `201 CREATED`
- `404 NOT FOUND`

Tambien incluyen anotaciones de OpenAPI para documentar Swagger:

- `@Tag`
- `@Operation`
- `@ApiResponse`
- `@Parameter`
- `@ExampleObject`

## ── 🔐 Autenticación

La API usa Spring Security con JWT para proteger los endpoints de negocio.

El login se realiza con un veterinario registrado en la base de datos:

```text
POST /auth/login
```

Body:

```json
{
  "email": "julio.gonzalez@veterinaria.com",
  "contrasena": "1234"
}
```

Respuesta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Las contrasenas no se guardan en texto plano. En la tabla `veterinarios`, el campo `contrasena` almacena un hash BCrypt.

Despues de iniciar sesion, copia el token y pegalo en Swagger desde el boton **Authorize** con el formato:

```text
Bearer TU_TOKEN
```

Rutas publicas:

```text
/auth/**
/swagger-ui/**
/swagger-ui.html
/v3/api-docs/**
```

Todas las demas rutas requieren token JWT.

El filtro `JwtAuthenticationFilter` revisa cada peticion, lee el header `Authorization`, valida el token y registra al veterinario autenticado en Spring Security.

## ── 📌 Endpoints principales

### Pets

```text
GET    /pet
GET    /pet/{id}
GET    /pet/name/{name}
GET    /pet/age/{edad}
POST   /pet
DELETE /pet/{id}
```

Ejemplo para crear una mascota:

```json
{
  "ownerId": 1,
  "nombre": "Remi",
  "raza": "Chihuahua",
  "especie": "Perro",
  "sexo": "Macho",
  "peso": 4.5,
  "edad": 8
}
```

### Owners

```text
GET    /owner
GET    /owner/{id}
GET    /owner/pet/{mascotaId}
GET    /owner/name/{name}
GET    /owner/lastname/{lastName}
GET    /owner/email/{email}
POST   /owner
DELETE /owner/{id}
```

Ejemplo para crear un tutor:

```json
{
  "nombre": "Juan",
  "apellido": "Flores",
  "email": "owner@mail.com",
  "direccion": "Calle 123",
  "telefono": "5551234567"
}
```

Ejemplo para crear un tutor con mascotas usando persistencia en cascada:

```json
{
  "nombre": "Juan",
  "apellido": "Flores",
  "email": "owner@mail.com",
  "direccion": "Calle 123",
  "telefono": "5551234567",
  "mascotas": [
    {
      "nombre": "Toby",
      "raza": "Poodle",
      "especie": "Perro",
      "sexo": "Macho",
      "peso": 6.4,
      "edad": 3
    },
    {
      "nombre": "Mila",
      "raza": "Siamés",
      "especie": "Gato",
      "sexo": "Hembra",
      "peso": 3.1,
      "edad": 2
    }
  ]
}
```

En este caso `Tutor` funciona como registro maestro y `Mascota` como detalle. Gracias a `cascade = CascadeType.ALL`, al guardar el tutor tambien se guardan automaticamente sus mascotas en la tabla `mascotas`.

La relacion que permite esto esta en la entidad `Tutor`:

```java
@OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
@JoinColumn(name="id_tutor")
private List<Mascota> mascotas = new ArrayList<>();
```

El endpoint `POST /owner` recibe un `Owner`, el mapper lo convierte a `Tutor`, y `TutorRepository` lo guarda con:

```java
tutorCrudRepository.save(mapper.toTutor(owner));
```

Si el tutor trae mascotas, JPA persiste automaticamente esos registros detalle.

Al consultar tutores, la respuesta incluye `mascotaIds` con las mascotas relacionadas:

```json
{
  "ownerId": 1,
  "mascotaIds": [1, 2],
  "nombre": "Juan",
  "apellido": "Flores",
  "email": "owner@mail.com",
  "direccion": "Calle 123",
  "telefono": "5551234567"
}
```

### Vets

```text
GET    /vet
GET    /vet/{id}
GET    /vet/license/{numLicense}
GET    /vet/specialty/{specialty}
POST   /vet
DELETE /vet/{id}
```

Ejemplo para crear un veterinario:

```json
{
  "nombre": "Eduardo",
  "apellido": "Flores",
  "numLicencia": "1093658",
  "email": "veterinario@mail.com",
  "especialidad": "Medicina Felina"
}
```

### Appointments

```text
GET    /appointment
GET    /appointment/{id}
GET    /appointment/pet/{mascotaId}
GET    /appointment/status/{estado}
POST   /appointment
PATCH  /appointment/{id}/status/{estado}
DELETE /appointment/{id}
```

Ejemplo para crear una cita:

```json
{
  "mascotaId": 1,
  "veterinarioId": 1,
  "fecha": "2026-07-23T18:13:59",
  "motivo": "Diarrea",
  "descripcion": "Perro chihuahua con diarrea",
  "estado": "activo",
  "observaciones": "Es un perro muy desobediente"
}
```

Ejemplo para buscar citas por estado:

```text
GET /appointment/status/activo
```

Ejemplo para actualizar solo el estado de una cita:

```text
PATCH /appointment/1/status/cancelada
```

La respuesta devuelve la cita actualizada:

```json
{
  "citaId": 1,
  "mascotaId": 1,
  "veterinarioId": 1,
  "fecha": "2026-07-23T18:13:59",
  "fechaRegistro": "2026-07-23T18:13:59",
  "motivo": "Diarrea",
  "descripcion": "Perro chihuahua con diarrea",
  "estado": "cancelada",
  "observaciones": "Es un perro muy desobediente"
}
```

> Nota: los IDs principales como `mascotaId`, `vetId`, `ownerId` y `citaId` son generados por la base de datos. Al crear registros nuevos con `POST`, no es necesario enviarlos.

## ── 📖 Swagger

El proyecto incluye Springdoc OpenAPI para documentar y probar la API desde Swagger UI.

Para ejecutar la aplicación:

```powershell
.\gradlew.bat bootRun
```

Despues abre:

```text
http://localhost:8090/swagger-ui/index.html
```

La configuración del puerto se encuentra en:

```text
src/main/resources/application-dev.properties
```

## ── 🔨 Compilación

Para compilar el proyecto:

```powershell
.\gradlew.bat compileJava
```

Para correr las pruebas:

```powershell
.\gradlew.bat test
```

MapStruct genera las implementaciones en:

```text
build/generated/sources/annotationProcessor/java/main
```
