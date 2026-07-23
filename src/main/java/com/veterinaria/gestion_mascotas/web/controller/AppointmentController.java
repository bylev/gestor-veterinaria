package com.veterinaria.gestion_mascotas.web.controller;

import com.veterinaria.gestion_mascotas.domain.model.Appointment;
import com.veterinaria.gestion_mascotas.domain.service.AppointmentService;
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
@RequestMapping("/Appointment")
@Tag(name = "Appointment", description = "Manage appointments in the veterinary clinic")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("")
    @Operation(summary = "Get all appointments", description = "Return a list of registered appointments")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of appointments")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Appointment>> getAll() {
        return new ResponseEntity<>(appointmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment by ID", description = "Return an appointment by ID if it exists")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of appointment")
    @ApiResponse(responseCode = "404", description = "Appointment not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Appointment> getCitaById(
            @Parameter(description = "ID of the appointment to retrieve", example = "1", required = true)
            @PathVariable("id") Integer citaId) {
        return appointmentService.getCitaById(citaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pet/{mascotaId}")
    @Operation(summary = "Get appointments by pet ID", description = "Return appointments related to the provided pet ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of appointments")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Appointment>> getByMascotaId(
            @Parameter(description = "ID of the pet to search appointments for", example = "1", required = true)
            @PathVariable Integer mascotaId) {
        return new ResponseEntity<>(appointmentService.getByMascotaId(mascotaId), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a new appointment", description = "Register a new appointment and return the created appointment", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    examples = @ExampleObject(
                            name = "Example appointment",
                            value = """
                                    {
                                        "mascotaId": 1,
                                        "veterinarioId": 1,
                                        "fecha": "2026-07-23T18:13:59",
                                        "motivo": "Diarrea",
                                        "descripcion": "Perro chihuahua con diarrea",
                                        "estado": "activo",
                                        "observaciones": "Es un perro muy desobediente"
                                    }
                                    """
                    )
            )
    ))
    @ApiResponse(responseCode = "201", description = "Successful creation of appointment")
    @ApiResponse(responseCode = "400", description = "Invalid appointment data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Appointment> save(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(appointmentService.save(appointment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an appointment by ID", description = "Delete an appointment if it exists")
    @ApiResponse(responseCode = "200", description = "Successful delete of appointment")
    @ApiResponse(responseCode = "404", description = "Appointment not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the appointment to delete", example = "1", required = true)
            @PathVariable("id") Integer citaId) {
        if (appointmentService.delete(citaId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
