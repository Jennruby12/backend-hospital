package com.hospital.controller;

import com.hospital.entity.Medico;
import com.hospital.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {



    @Autowired
    private MedicoRepository medicoRepository;


    @GetMapping
    public List<Medico> getAll() {
        return medicoRepository.findAll();
    }


    @PostMapping
    public Medico crear(@RequestBody Medico medico) {
        return medicoRepository.save(medico);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Medico> getById(@PathVariable Long id) {
        return medicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Medico> update(@PathVariable Long id, @RequestBody Medico nuevo) {
        return medicoRepository.findById(id)
                .map(medico -> {
                    medico.setNombreCompleto(nuevo.getNombreCompleto());
                    medico.setEspecialidad(nuevo.getEspecialidad());
                    medico.setNumeroLicencia(nuevo.getNumeroLicencia());
                    medico.setFechaIngreso(nuevo.getFechaIngreso());
                    medico.setEmailContacto(nuevo.getEmailContacto());
                    return ResponseEntity.ok(medicoRepository.save(medico));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (medicoRepository.existsById(id)) {
            medicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}