package com.demo.web.data;

import com.demo.domain.DroneModel;
import com.demo.domain.DroneState;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DroneDto {

    private UUID id;

    @NotBlank
    private String serialNumber;

    @Min(100)
    @Max(500)
    private int weight;

    @Max(100)
    private int batteryCapacity;

    @NotNull
    private DroneModel model;

    @NotNull
    private DroneState state;

    private List<MedicationDto> medications;
}
