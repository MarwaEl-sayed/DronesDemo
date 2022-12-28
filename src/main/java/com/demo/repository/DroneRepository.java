package com.demo.repository;

import com.demo.domain.Drone;
import com.demo.domain.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DroneRepository extends JpaRepository<Drone, UUID> {
    List<Drone> findByStateIn(List<DroneState> states);
}
