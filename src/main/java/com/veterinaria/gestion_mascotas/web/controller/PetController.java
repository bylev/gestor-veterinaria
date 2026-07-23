package com.veterinaria.gestion_mascotas.web.controller;

import com.veterinaria.gestion_mascotas.domain.model.Pet;
import com.veterinaria.gestion_mascotas.domain.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@Tag(name = "Pet", description = "Manage pets in the veterinary clinic")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("")
    @Operation(summary = "Get all pets", description = "Return a list of registered pets")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of pets")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Pet>> getAll() {
        return new ResponseEntity<>(petService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get pet by ID", description = "Return a pet by its ID if it exists")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of pet")
    @ApiResponse(responseCode = "404", description = "Pet not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Pet> getById(
            @Parameter(description = "ID of the pet to retrieve", example = "1", required = true)
            @PathVariable("id") Integer mascotaId) {
        return petService.getById(mascotaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get pets by name", description = "Return pets matching the provided name")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of pets")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Pet>> getByName(
            @Parameter(description = "Name of the pet to search", example = "Remi", required = true)
            @PathVariable String name) {
        return new ResponseEntity<>(petService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/age/{edad}")
    @Operation(summary = "Get pets by age", description = "Return pets with the provided age")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of pets")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Pet>> getByEdad(
            @Parameter(description = "Age of the pet to search", example = "8", required = true)
            @PathVariable Integer edad) {
        return new ResponseEntity<>(petService.getByEdad(edad), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a new pet", description = "Register a new pet and return the created pet", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    examples = @ExampleObject(
                            name = "Example pet",
                            value = """
                                    {
                                        "nombre": "Remi",
                                        "raza": "Chihuahua",
                                        "especie": "Perro",
                                        "sexo": "Macho",
                                        "peso": 4.5,
                                        "edad": 8
                                    }
                                    """
                    )
            )
    ))
    @ApiResponse(responseCode = "201", description = "Successful creation of pet")
    @ApiResponse(responseCode = "400", description = "Invalid pet data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Pet> save(@RequestBody Pet pet) {
        return new ResponseEntity<>(petService.save(pet), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a pet by ID", description = "Delete a pet if it exists")
    @ApiResponse(responseCode = "200", description = "Successful delete of pet")
    @ApiResponse(responseCode = "404", description = "Pet not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the pet to delete", example = "1", required = true)
            @PathVariable("id") Integer mascotaId) {
        if (petService.delete(mascotaId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
