package com.hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pacienteId;

    private String nombreCompleto;
    private String fechaNacimiento;
    private String genero;
    private String direccion;
    private String telefono;
    private String email;
    private String tipoSangre;
    private String seguroMedico;
}

