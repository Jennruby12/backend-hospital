package com.hospital.service.impl;

import com.hospital.entity.Paciente;
import com.hospital.repository.PacienteRepository;
import com.hospital.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> obtenerTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> obtenerPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        if (paciente.getNombreCompleto() == null || paciente.getNombreCompleto().isBlank()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio.");
        }


        boolean yaExiste = pacienteRepository.findAll().stream().anyMatch(p ->
                p.getNombreCompleto().equalsIgnoreCase(paciente.getNombreCompleto()) &&
                        p.getEmail().equalsIgnoreCase(paciente.getEmail()));

        if (yaExiste) {
            throw new IllegalArgumentException("El paciente ya está registrado.");
        }

        return pacienteRepository.save(paciente);
    }
}