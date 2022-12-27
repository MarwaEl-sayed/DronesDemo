package com.demo.web.controller;

import com.demo.domain.DroneState;
import com.demo.service.DroneService;
import com.demo.web.Exception.ApiEntityNotFoundException;
import com.demo.web.data.DroneDto;
import com.demo.web.data.MedicationDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(path = "/api/drones", produces = "application/json", consumes = "application/json")
public class DroneController {
    final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DroneDto createDrone(@Valid @RequestBody DroneDto droneDto) {
        return droneService.createDrone(droneDto);
    }

    @PostMapping("/{droneId}/medications")
    @ResponseStatus(HttpStatus.CREATED)
    public DroneDto loadDroneMedication(@RequestBody @Valid List<MedicationDto> medicationDtos,
                                        @PathVariable UUID droneId) {
        if (droneService.droneExists(droneId)) {
            return droneService.loadDroneMedication(droneId, medicationDtos);
        } else {
            throw new ApiEntityNotFoundException(String.format("No drone with id %s found", droneId));
        }
    }

    @GetMapping("/{droneId}/medications")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicationDto> getDroneMedications(@PathVariable UUID droneId) {
        if (droneService.droneExists(droneId)) {
            return droneService.getDroneMedications(droneId);
        } else {
            throw new ApiEntityNotFoundException(String.format("No drone with id %s found", droneId));
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DroneDto> getDroneMedications(@RequestParam List<DroneState> droneStates) {
        return droneService.filterDrones(droneStates);
    }

    @GetMapping("/{droneId}")
    @ResponseStatus(HttpStatus.OK)
    public DroneDto getDroneById(@PathVariable UUID droneId) {
        if (droneService.droneExists(droneId)) {
            return droneService.getDroneById(droneId);
        } else {
            throw new ApiEntityNotFoundException(String.format("No drone with id %s found", droneId));
        }
    }
}
