package com.sena.sistemaintegralsena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "taller")
@Data
public class taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre_profesional;
    private String nombre_taller;
    private String ficha;
    private String programa_formacion;
    private String jornada;

    private LocalDate fecha_inicio;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;

    private Integer cupo_solicitado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private usuario usuario;
}
