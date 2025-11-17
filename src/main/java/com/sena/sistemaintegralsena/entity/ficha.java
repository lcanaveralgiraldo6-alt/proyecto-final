package com.sena.sistemaintegralsena.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ficha")
@Data
public class ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private programa programa;
}
