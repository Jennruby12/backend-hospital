package com.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditoriaId;

    private Long pacienteId;
    private String accion;
    private String usuario;
    private String fechaCambio;
    private String camposModificados;
}
