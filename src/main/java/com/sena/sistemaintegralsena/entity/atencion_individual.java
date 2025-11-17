package com.sena.sistemaintegralsena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "atencion_individual")
@Data
public class atencion_individual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profesional;

    private String nombres_apellidos;
    private String tipo_documento;
    private String numero_documento;
    private String edad;
    private String celular;
    private String correo;
    private String programa_formacion;
    private String ficha;
    private String coordinacion_academica;
    private String modalidad;
    private String jornada;
    private String categoria_desercion;

    private LocalDate fecha_primera;
    private LocalDate fecha_segunda;
    private LocalDate fecha_tercera;

    private String remitido_por;
    private String estado_caso;

    private String obs_primera;
    private String obs_segunda;
    private String obs_tercera;

    @ManyToOne
    @JoinColumn(name = "usuario_id")  // psicóloga que la realizó
    private usuario usuario;
}
