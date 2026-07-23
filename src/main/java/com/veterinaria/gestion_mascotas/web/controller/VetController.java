package com.veterinaria.gestion_mascotas.web.controller;

import com.veterinaria.gestion_mascotas.domain.model.Vet;
import com.veterinaria.gestion_mascotas.domain.service.VetService;
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
@RequestMapping("/vet")
@Tag(name = "Vet", description = "Manage veterinarians in the clinic")
public class VetController {

    @Autowired
    private VetService vetService;

    @GetMapping("")
    @Operation(summary = "Get all vets", description = "Return a list of registered veterinarians")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of vets")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Vet>> getAll() {
        return new ResponseEntity<>(vetService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vet by ID", description = "Return a veterinarian by ID if it exists")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of vet")
    @ApiResponse(responseCode = "404", description = "Vet not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Vet> getById(
            @Parameter(description = "ID of the vet to retrieve", example = "1", required = true)
            @PathVariable("id") Integer vetId) {
        return vetService.getById(vetId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/license/{numLicense}")
    @Operation(summary = "Get vet by license number", description = "Return a veterinarian by license number if it exists")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of vet")
    @ApiResponse(responseCode = "404", description = "Vet not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Vet> getByLicenseNumber(
            @Parameter(description = "License number of the vet to retrieve", example = "1093658", required = true)
            @PathVariable String numLicense) {
        return vetService.getByLicenseNumber(numLicense).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialty/{specialty}")
    @Operation(summary = "Get vets by specialty", description = "Return veterinarians matching the provided specialty")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of vets")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Vet>> getBySpecialty(
            @Parameter(description = "Specialty of the vet to search", example = "Medicina Felina", required = true)
            @PathVariable String specialty) {
        return new ResponseEntity<>(vetService.getBySpecialty(specialty), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a new vet", description = "Register a new veterinarian and return the created vet", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    examples = @ExampleObject(
                            name = "Example vet",
                            value = """
                                    {
                                        "nombre": "Eduardo",
                                        "apellido": "Flores",
                                        "numLicencia": "1093658",
                                        "especialidad": "Medicina Felina"
                                    }
                                    """
                    )
            )
    ))
    @ApiResponse(responseCode = "201", description = "Successful creation of vet")
    @ApiResponse(responseCode = "400", description = "Invalid vet data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Vet> save(@RequestBody Vet vet) {
        return new ResponseEntity<>(vetService.save(vet), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a vet by ID", description = "Delete a veterinarian if it exists")
    @ApiResponse(responseCode = "200", description = "Successful delete of vet")
    @ApiResponse(responseCode = "404", description = "Vet not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the vet to delete", example = "1", required = true)
            @PathVariable("id") Integer vetId) {
        if (vetService.delete(vetId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
