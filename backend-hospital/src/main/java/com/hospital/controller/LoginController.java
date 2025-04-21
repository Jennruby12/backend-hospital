package com.hospital.controller;

import com.hospital.entity.Medico;
import com.hospital.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class LoginController {

    @Autowired
    private MedicoRepository MedicoRepository;

    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("medico", new Medico());
        return "login"; // login.html
    }

    // Procesar inicio de sesión
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String emailContacto,
                                @RequestParam String contrasena,
                                Model model) {

        Medico medico = MedicoRepository.findByEmailContactoAndContrasena(emailContacto, contrasena);

        if (medico != null) {
            model.addAttribute("medico", medico);
            return "dashboard"; // Redirige al panel principal
        }

        model.addAttribute("error", "Credenciales incorrectas.");
        return "login";
    }

    // Procesar registro de médico
    @PostMapping("/registro")
    public String registrarMedico(@RequestParam String nombreCompleto,
                                  @RequestParam String especialidad,
                                  @RequestParam String numeroLicencia,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaIngreso,
                                  @RequestParam int departamentoId,
                                  @RequestParam String emailContacto,
                                  @RequestParam String contrasena,
                                  Model model) {

        boolean yaExiste = MedicoRepository.existsByEmailContactoOrNumeroLicencia(emailContacto, numeroLicencia);

        if (yaExiste) {
            model.addAttribute("error", "Ya existe un médico con ese email o licencia.");
            return "login";
        }

        Medico nuevo = new Medico();
        nuevo.setNombreCompleto(nombreCompleto);
        nuevo.setEspecialidad(especialidad);
        nuevo.setNumeroLicencia(numeroLicencia);
        nuevo.setFechaIngreso(fechaIngreso);
        nuevo.setDepartamentoId(departamentoId);
        nuevo.setEmailContacto(emailContacto);
        nuevo.setContrasena(contrasena);

        MedicoRepository.save(nuevo);

        model.addAttribute("registroExitoso", "Registro exitoso. Ya puedes iniciar sesión.");
        return "redirect:/login";
    }
}