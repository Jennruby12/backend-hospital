package com.hospital.controller;

import com.hospital.entity.HistorialMedico;
import com.hospital.repository.HistorialMedicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiales")
public class HistorialMedicoController {

    private final HistorialMedicoRepository historialRepository;

    public HistorialMedicoController(HistorialMedicoRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    @GetMapping
    public List<HistorialMedico> getAll() {
        return historialRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialMedico> getById(@PathVariable Long id) {
        return historialRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HistorialMedico create(@RequestBody HistorialMedico historial) {
        return historialRepository.save(historial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialMedico> update(@PathVariable Long id, @RequestBody HistorialMedico nuevo) {
        return historialRepository.findById(id)
                .map(h -> {
                    h.setPaciente(nuevo.getPaciente());
                    h.setFechaRegistro(nuevo.getFechaRegistro());
                    h.setDescripcionCondicion(nuevo.getDescripcionCondicion());
                    h.setMedicamentos(nuevo.getMedicamentos());
                    h.setAlergias(nuevo.getAlergias());
                    return ResponseEntity.ok(historialRepository.save(h));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (historialRepository.existsById(id)) {
            historialRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
