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
    └── controller
```

## ── ★ Dominio

Las clases del modelo de dominio están en `domain.model` y no contienen anotaciones de JPA.

Modelos actuales:

- `Pet`
- `Owner`
- `Vet`
- `Appointment`

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

## ── 🌐 Controladores

Los controladores están en `web.controller` y exponen la API REST.

Controladores actuales:

- `PetController`
- `OwnerController`
- `VetController`
- `AppointmentController`

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
  "especialidad": "Medicina Felina"
}
```

### Appointments

```text
GET    /Appointment
GET    /Appointment/{id}
GET    /Appointment/pet/{mascotaId}
POST   /Appointment
DELETE /Appointment/{id}
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

