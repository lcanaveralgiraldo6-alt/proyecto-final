package com.sena.sistemaintegralsena.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "programa")
@Data
public class programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
