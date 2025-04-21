package com.hospital.repository;

import com.hospital.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Para el login
    Medico findByEmailContactoAndContrasena(String emailContacto, String contrasena);

    // Para verificar si ya existe un médico por email o licencia
    boolean existsByEmailContactoOrNumeroLicencia(String emailContacto, String numeroLicencia);
}