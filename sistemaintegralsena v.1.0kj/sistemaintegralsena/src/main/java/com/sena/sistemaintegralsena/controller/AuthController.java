package com.sena.sistemaintegralsena.controller;

import com.sena.sistemaintegralsena.entity.Usuario;
import com.sena.sistemaintegralsena.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @ModelAttribute("usuario") Usuario usuario,
            @RequestParam("rol") String rolSeleccionado,
            Model model) {

        // Validar que no exista el correo
        if (usuarioService.buscarPorEmail(usuario.getEmail()) != null) {
            model.addAttribute("error", "El correo ya está registrado");
            return "registro";
        }

        // Normalizar el rol recibido
        String rol = rolSeleccionado.toUpperCase().trim();

        usuarioService.guardarConRol(usuario, rol);

        return "redirect:/login?registrado=true";
    }
}
