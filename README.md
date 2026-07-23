<div align="center">
<img src="images/logo2.png" width = "150" style="border-radius: 15%" align="center" hspace="20" height="150">

<h1><i> Veterinaria API ── Spring Boot ౨ৎ⋆˚｡⋆ </i></h1>
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

## ── 📁 Estructura del proyecto

```text
src/main/java/com/veterinaria/gestion_mascotas
├── domain
│   ├── repository
│   └── service
└── persistence
    ├── crud
    ├── entity
    └── mapper
```

## ── ★ Dominio

Las clases del dominio están en `domain.service` y no contienen anotaciones de JPA.

Modelos actuales:

- `Pet`
- `Owner`
- `Vet`
- `Appointment`

## ── ★ Repositorios de dominio

Las interfaces de repositorio estan en `domain.repository` y trabajan con modelos de dominio, no con entidades JPA.

Repositorios actuales:

- `MascotaRepository`
- `OwnerRepository`
- `VetRepository`
- `AppointmentRepository`

## ── 📈 Persistencia

Las entidades JPA están en `persistence.entity`.

Entidades actuales:

- `Mascota`
- `Tutor`
- `Veterinario`
- `Cita`

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

## ── 🔨 Compilación

Para compilar el proyecto:

```powershell
.\gradlew.bat compileJava
```

MapStruct genera las implementaciones en:

```text
build/generated/sources/annotationProcessor/java/main
```


