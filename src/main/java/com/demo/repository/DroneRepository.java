package com.demo.repository;

import com.demo.domain.Drone;
import com.demo.domain.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DroneRepository extends JpaRepository<Drone, UUID> {
    List<Drone> findByStateIn(List<DroneState> states);
}
