package com.demo.web.data;

import com.demo.domain.Drone;
import com.demo.domain.Medication;
import org.modelmapper.ModelMapper;

public class DroneMapper {

    private DroneMapper() {
    }

    public static DroneDto mapToDroneDto(Drone gateway) {
        var modelMapper = new ModelMapper();
        var dtoMapper = modelMapper.createTypeMap(Drone.class, DroneDto.class);
        return dtoMapper.map(gateway);
    }

    public static Drone mapToDrone(DroneDto droneDtoDto) {
        var modelMapper = new ModelMapper();
        var dtoMapper = modelMapper.createTypeMap(DroneDto.class, Drone.class);
        return dtoMapper.map(droneDtoDto);
    }

    public static MedicationDto mapToMedicationDto(Medication medication) {
        var modelMapper = new ModelMapper();
        var dtoMapper = modelMapper.createTypeMap(Medication.class, MedicationDto.class);
        return dtoMapper.map(medication);
    }

    public static Medication mapToMedication(MedicationDto medicationDto) {
        var modelMapper = new ModelMapper();
        var dtoMapper = modelMapper.createTypeMap(MedicationDto.class, Medication.class);
        return dtoMapper.map(medicationDto);
    }
}
