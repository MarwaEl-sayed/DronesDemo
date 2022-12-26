package com.demo.web.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
public class MedicationDto {

    private UUID id;

    @Pattern(regexp = "[A-Za-z0-9_-]+")
    private String name;

    @Pattern(regexp = "[A-Z0-9_]+")
    private String code;

    @NotNull
    private Integer weight;

    private String imageUrl;
}
