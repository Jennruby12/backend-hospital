package com.hospital.controller;

import com.hospital.entity.AuditoriaPaciente;
import com.hospital.repository.AuditoriaPacienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditorias-pacientes")
public class AuditoriaPacienteController {

    private final AuditoriaPacienteRepository auditoriaPacienteRepository;

    public AuditoriaPacienteController(AuditoriaPacienteRepository auditoriaPacienteRepository) {
        this.auditoriaPacienteRepository = auditoriaPacienteRepository;
    }

    @GetMapping
    public List<AuditoriaPaciente> getAll() {
        return auditoriaPacienteRepository.findAll();
    }
}