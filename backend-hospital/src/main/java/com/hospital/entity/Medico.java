package com.hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicoId;

    private String nombreCompleto;
    private String especialidad;
    private String numeroLicencia;

    private LocalDate fechaIngreso;

    private String emailContacto;
    private String contrasena;
    private int departamentoId;
}