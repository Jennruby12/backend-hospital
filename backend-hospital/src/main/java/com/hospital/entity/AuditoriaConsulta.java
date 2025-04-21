package com.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditoriaId;

    private Long consultaId;
    private String accion;
    private String usuario;
    private String fechaCambio;
    private String detallesCambio;
}