package com.hospital.controller;

import com.hospital.entity.AuditoriaPaciente;
import com.hospital.entity.Paciente;
import com.hospital.repository.AuditoriaPacienteRepository;
import com.hospital.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hospital.service.PacienteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AuditoriaPacienteRepository auditoriaPacienteRepository;

    @Autowired
    private PacienteService pacienteService;


    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<?> crearPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente nuevo = pacienteService.guardar(paciente);
            return ResponseEntity.ok(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente nuevosDatos) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();

            paciente.setNombreCompleto(nuevosDatos.getNombreCompleto());
            paciente.setFechaNacimiento(nuevosDatos.getFechaNacimiento());
            paciente.setGenero(nuevosDatos.getGenero());
            paciente.setDireccion(nuevosDatos.getDireccion());
            paciente.setTelefono(nuevosDatos.getTelefono());
            paciente.setEmail(nuevosDatos.getEmail());
            paciente.setTipoSangre(nuevosDatos.getTipoSangre());
            paciente.setSeguroMedico(nuevosDatos.getSeguroMedico());

            Paciente actualizado = pacienteRepository.save(paciente);

            AuditoriaPaciente auditoria = new AuditoriaPaciente();
            auditoria.setPacienteId(actualizado.getPacienteId());
            auditoria.setAccion("ACTUALIZADO");
            auditoria.setUsuario("admin");
            auditoria.setFechaCambio(LocalDate.now().toString());
            auditoria.setCamposModificados("Todos los campos actualizados");

            auditoriaPacienteRepository.save(auditoria);

            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            pacienteRepository.deleteById(id);

            AuditoriaPaciente auditoria = new AuditoriaPaciente();
            auditoria.setPacienteId(paciente.getPacienteId());
            auditoria.setAccion("ELIMINADO");
            auditoria.setUsuario("admin");
            auditoria.setFechaCambio(LocalDate.now().toString());
            auditoria.setCamposModificados("Todos los datos eliminados");

            auditoriaPacienteRepository.save(auditoria);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


