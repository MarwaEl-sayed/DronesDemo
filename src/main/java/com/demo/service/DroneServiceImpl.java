package com.demo.service;

import com.demo.domain.DroneState;
import com.demo.domain.Medication;
import com.demo.repository.DroneRepository;
import com.demo.web.Exception.ApiBadRequestException;
import com.demo.web.data.DroneDto;
import com.demo.web.data.DroneMapper;
import com.demo.web.data.MedicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {
    @Autowired
    final DroneRepository droneRepository;

    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DroneDto createDrone(DroneDto droneDto) {
        var drone = droneRepository.save(DroneMapper.mapToDrone(droneDto));
        droneDto = DroneMapper.mapToDroneDto(drone);
        return droneDto;
    }

    @Override
    @Transactional
    public boolean droneExists(UUID id) {
        return droneRepository.existsById(id);
    }

    @Override
    @Transactional
    public DroneDto loadDroneMedication(UUID droneId, List<MedicationDto> medicationDtos) {
        var medications = medicationDtos.stream()
                .map(DroneMapper::mapToMedication)
                .collect(Collectors.toSet());

        var drone = droneRepository.findById(droneId);

        var currentLoadWeight = getMedicationWeightSum(drone.get().getMedications());
        var newLoadWeight = getMedicationWeightSum(medications);
        if (currentLoadWeight + newLoadWeight > drone.get().getWeight()) {
            throw new ApiBadRequestException("Load weight exceeds weight limit");
        }

        drone.get().getMedications().addAll(medications);
        droneRepository.save(drone.get());
        return DroneMapper.mapToDroneDto(drone.get());
    }

    @Override
    @Transactional
    public List<MedicationDto> getDroneMedications(UUID droneId) {
        var drone = droneRepository.findById(droneId);
        return drone.get().getMedications().stream()
                .map(DroneMapper::mapToMedicationDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<DroneDto> filterDrones(List<DroneState> droneStates) {
        return droneRepository.findByStateIn(droneStates).stream()
                .map(DroneMapper::mapToDroneDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DroneDto getDroneById(UUID droneId) {
        return DroneMapper.mapToDroneDto(droneRepository.findById(droneId).get());
    }

    private int getMedicationWeightSum(Set<Medication> medications) {
        return medications.stream()
                .map(Medication::getWeight)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
