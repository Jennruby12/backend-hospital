package com.hospital.controller;

import com.hospital.entity.AuditoriaConsulta;
import com.hospital.repository.AuditoriaConsultaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditorias-consultas")
public class AuditoriaConsultaController {

    private final AuditoriaConsultaRepository auditoriaConsultaRepository;

    public AuditoriaConsultaController(AuditoriaConsultaRepository auditoriaConsultaRepository) {
        this.auditoriaConsultaRepository = auditoriaConsultaRepository;
    }

    // GET - Mostrar todas las auditorías registradas
    @GetMapping
    public List<AuditoriaConsulta> getAll() {
        return auditoriaConsultaRepository.findAll();
    }
}
