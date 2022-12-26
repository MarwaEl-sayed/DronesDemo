package com.demo.service;

import com.demo.domain.DroneState;
import com.demo.web.data.DroneDto;
import com.demo.web.data.MedicationDto;

import java.util.List;
import java.util.UUID;

public interface DroneService {
    DroneDto createDrone(DroneDto droneDto);

    boolean droneExists(UUID id);

    DroneDto loadDroneMedication(UUID droneId, List<MedicationDto> medicationDtos);

    List<MedicationDto> getDroneMedications(UUID droneId);

    List<DroneDto> filterDrones(List<DroneState> droneStates);

    DroneDto getDroneById(UUID droneId);
}
