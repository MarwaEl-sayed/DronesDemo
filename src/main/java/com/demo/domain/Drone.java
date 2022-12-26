package com.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "drone")
public class Drone {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 100, nullable = false, unique = true)
    private String serialNumber;

    @Column(nullable = false)
    @Min(100)
    @Max(500)
    private int weight;

    @Column(nullable = false)
    @Max(100)
    private int batteryCapacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DroneState state;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medication_id", nullable = false)
    private Set<Medication> medications = new HashSet<>();
}
