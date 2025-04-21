package com.hospital.controller;

import com.hospital.entity.Consulta;
import com.hospital.entity.AuditoriaConsulta;
import com.hospital.repository.ConsultaRepository;
import com.hospital.repository.AuditoriaConsultaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;
    private final AuditoriaConsultaRepository auditoriaConsultaRepository;


    public ConsultaController(
            ConsultaRepository consultaRepository,
            AuditoriaConsultaRepository auditoriaConsultaRepository
    ) {
        this.consultaRepository = consultaRepository;
        this.auditoriaConsultaRepository = auditoriaConsultaRepository;
    }


    @GetMapping
    public List<Consulta> getAll() {
        return consultaRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getById(@PathVariable Long id) {
        return consultaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Consulta create(@RequestBody Consulta consulta) {
        Consulta nuevaConsulta = consultaRepository.save(consulta);

        AuditoriaConsulta auditoria = new AuditoriaConsulta();
        auditoria.setConsultaId(nuevaConsulta.getConsultaId());
        auditoria.setAccion("CREADO");
        auditoria.setUsuario("admin");
        auditoria.setFechaCambio(LocalDate.now().toString());
        auditoria.setDetallesCambio("Consulta creada con diagnóstico: " + consulta.getDiagnostico());

        auditoriaConsultaRepository.save(auditoria);

        return nuevaConsulta;
    }


    @PutMapping("/{id}")
    public ResponseEntity<Consulta> update(@PathVariable Long id, @RequestBody Consulta nueva) {
        return consultaRepository.findById(id)
                .map(c -> {
                    c.setPaciente(nueva.getPaciente());
                    c.setMedico(nueva.getMedico());
                    c.setFechaConsulta(nueva.getFechaConsulta());
                    c.setDiagnostico(nueva.getDiagnostico());
                    c.setTratamiento(nueva.getTratamiento());
                    c.setEstadoConsulta(nueva.getEstadoConsulta());
                    return ResponseEntity.ok(consultaRepository.save(c));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}