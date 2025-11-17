package com.sena.sistemaintegralsena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "comite")
@Data
public class comite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Datos del aprendiz NO como clase
    private String nombres_apellidos;
    private String tipo_documento;
    private String numero_documento;
    private String correo;
    private String celular;
    private String ficha;
    private String programa_formacion;
    private String coordinacion_academica;
    private String etapa_formacion;
    private String modalidad;
    private String jornada;

    private LocalDate fecha_comite;
    private LocalTime hora_comite;

    private String tipo_falta;
    private String motivo_citacion;
    private String enlace_ingreso;
    private String profesional_asiste;

    private String recomendacion;
    private String fecha_plazo;
    private String observaciones;
    private String representante_asiste;
    private String paz_y_salvo;
    private String profesional_cargo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")  // psicóloga que lo registró
    private usuario usuario;
}
