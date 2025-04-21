package com.hospital.controller;

import com.hospital.entity.Departamento;
import com.hospital.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")

public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;


    @GetMapping
    public List<Departamento> getAll() {
        return departamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getById(@PathVariable Long id) {
        return departamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Departamento create(@RequestBody Departamento d) {
        return departamentoRepository.save(d);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> update(@PathVariable Long id, @RequestBody Departamento nuevo) {
        return departamentoRepository.findById(id)
                .map(dep -> {
                    dep.setNombreDepartamento(nuevo.getNombreDepartamento());
                    dep.setUbicacion(nuevo.getUbicacion());
                    dep.setJefeDepartamento(nuevo.getJefeDepartamento());
                    return ResponseEntity.ok(departamentoRepository.save(dep));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (departamentoRepository.existsById(id)) {
            departamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
