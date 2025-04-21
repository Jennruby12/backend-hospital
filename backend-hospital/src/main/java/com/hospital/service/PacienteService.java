package com.hospital.service;

import com.hospital.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    List<Paciente> obtenerTodos();
    Optional<Paciente> obtenerPorId(Long id);
    Paciente guardar(Paciente paciente);
}