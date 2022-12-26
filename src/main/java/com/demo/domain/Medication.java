package com.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "[A-Za-z0-9_-]+")
    private String name;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "[A-Z0-9_]+")
    private String code;

    @Column(nullable = false)
    private int weight;

    private String imageUrl;
}
