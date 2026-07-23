package com.veterinaria.gestion_mascotas.web.controller;

import com.veterinaria.gestion_mascotas.domain.model.Owner;
import com.veterinaria.gestion_mascotas.domain.service.OwnerService;
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
@RequestMapping("/owner")
@Tag(name = "Owner", description = "Manage pet owners in the veterinary clinic")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping("")
    @Operation(summary = "Get all owners", description = "Return a list of registered pet owners")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of owners")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Owner>> getAll() {
        return new ResponseEntity<>(ownerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get owner by ID", description = "Return an owner by ID if it exists")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of owner")
    @ApiResponse(responseCode = "404", description = "Owner not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Owner> getOwnerById(
            @Parameter(description = "ID of the owner to retrieve", example = "1", required = true)
            @PathVariable Integer id) {
        return ownerService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pet/{mascotaId}")
    @Operation(summary = "Get owners by pet ID", description = "Return owners related to the provided pet ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of owners")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Owner>> getByMascotaId(
            @Parameter(description = "ID of the pet to search owners for", example = "1", required = true)
            @PathVariable Integer mascotaId) {
        return new ResponseEntity<>(ownerService.getByMascotaId(mascotaId), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get owners by name", description = "Return owners matching the provided name")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of owners")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Owner>> getByName(
            @Parameter(description = "Name of the owner to search", example = "Juan", required = true)
            @PathVariable String name) {
        return new ResponseEntity<>(ownerService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/lastname/{lastName}")
    @Operation(summary = "Get owners by last name", description = "Return owners matching the provided last name")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of owners")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Owner>> getByLastName(
            @Parameter(description = "Last name of the owner to search", example = "Flores", required = true)
            @PathVariable String lastName) {
        return new ResponseEntity<>(ownerService.getByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get owner by email", description = "Return an owner by email if it exists")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of owner")
    @ApiResponse(responseCode = "404", description = "Owner not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Owner> getByEmail(
            @Parameter(description = "Email of the owner to retrieve", example = "owner@mail.com", required = true)
            @PathVariable String email) {
        return ownerService.getByEmail(email).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Add a new owner", description = "Register a new owner and return the created owner", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    examples = @ExampleObject(
                            name = "Example owner",
                            value = """
                                    {
                                        "nombre": "Juan",
                                        "apellido": "Flores",
                                        "email": "owner@mail.com",
                                        "direccion": "Calle 123",
                                        "telefono": "5551234567"
                                    }
                                    """
                    )
            )
    ))
    @ApiResponse(responseCode = "201", description = "Successful creation of owner")
    @ApiResponse(responseCode = "400", description = "Invalid owner data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Owner> save(@RequestBody Owner owner) {
        return new ResponseEntity<>(ownerService.save(owner), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an owner by ID", description = "Delete an owner if it exists")
    @ApiResponse(responseCode = "200", description = "Successful delete of owner")
    @ApiResponse(responseCode = "404", description = "Owner not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the owner to delete", example = "1", required = true)
            @PathVariable Integer id) {
        if (ownerService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
