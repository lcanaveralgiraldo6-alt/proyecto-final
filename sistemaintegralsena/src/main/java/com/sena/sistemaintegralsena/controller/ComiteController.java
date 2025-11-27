package com.sena.sistemaintegralsena.controller;

import com.sena.sistemaintegralsena.entity.Aprendiz;
import com.sena.sistemaintegralsena.entity.Comite;
import com.sena.sistemaintegralsena.entity.Usuario;
import com.sena.sistemaintegralsena.repository.AprendizRepository;
import com.sena.sistemaintegralsena.repository.UsuarioRepository;
import com.sena.sistemaintegralsena.service.ComiteService;
import jakarta.validation.Valid; // Importante
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Importante
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/comite")
@PreAuthorize("hasRole('ADMIN') or hasAnyRole('PSICOLOGA', 'T_SOCIAL')")
public class ComiteController {

    @Autowired
    private ComiteService comiteService;

    @Autowired
    private AprendizRepository aprendizRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("comites", comiteService.listarTodos());
        return "comite/lista";
    }

    // 2. VISTA CREAR
    @GetMapping("/crear")
    public String buscarParaCrear(@RequestParam(required = false) String documento, Model model) {
        if (!model.containsAttribute("comite")) {
            model.addAttribute("comite", new Comite());
        }
        
        if (documento != null && !documento.isEmpty()) {
            Aprendiz aprendiz = aprendizRepository.findByNumeroDocumento(documento);
            if (aprendiz != null) {
                model.addAttribute("aprendizEncontrado", aprendiz);
                cargarProfesionales(model);
            } else {
                model.addAttribute("errorBusqueda", "No se encontró aprendiz: " + documento);
            }
            model.addAttribute("busqueda", documento);
        }
        return "comite/crear";
    }

    // 3. GUARDAR
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Comite comite, 
                          BindingResult result,
                          @RequestParam Long aprendizId, 
                          Principal principal,
                          RedirectAttributes redirect,
                          Model model) {
        
        // Si hay errores de validación, recargamos la vista con los datos necesarios
        if (result.hasErrors()) {
            Aprendiz aprendiz = aprendizRepository.findById(aprendizId).orElse(null);
            model.addAttribute("aprendizEncontrado", aprendiz);
            cargarProfesionales(model);
            return "comite/crear"; 
        }

        try {
            comiteService.guardar(comite, aprendizId, principal.getName());
            redirect.addFlashAttribute("exito", "Comité registrado correctamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar: " + e.getMessage());
            Aprendiz aprendiz = aprendizRepository.findById(aprendizId).orElse(null);
            model.addAttribute("aprendizEncontrado", aprendiz);
            cargarProfesionales(model);
            return "comite/crear";
        }
        return "redirect:/comite";
    }

    // 4. VISTA EDITAR
    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        Comite comite = comiteService.buscarPorId(id);
        if (comite == null) return "redirect:/comite";

        model.addAttribute("comite", comite);
        model.addAttribute("aprendizEncontrado", comite.getAprendiz());
        cargarProfesionales(model);

        return "comite/editar";
    }

    // 5. ACTUALIZAR
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute Comite comite, 
                             BindingResult result,
                             @RequestParam Long aprendizId, 
                             Principal principal,
                             RedirectAttributes redirect,
                             Model model) {
        
        if (result.hasErrors()) {
            Aprendiz aprendiz = aprendizRepository.findById(aprendizId).orElse(null);
            model.addAttribute("aprendizEncontrado", aprendiz);
            cargarProfesionales(model);
            return "comite/editar";
        }

        try {
            comiteService.guardar(comite, aprendizId, principal.getName());
            redirect.addFlashAttribute("exito", "Comité actualizado correctamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            Aprendiz aprendiz = aprendizRepository.findById(aprendizId).orElse(null);
            model.addAttribute("aprendizEncontrado", aprendiz);
            cargarProfesionales(model);
            return "comite/editar";
        }
        return "redirect:/comite";
    }

    // 6. ELIMINAR
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        comiteService.eliminar(id);
        redirect.addFlashAttribute("exito", "Comité eliminado.");
        return "redirect:/comite";
    }

    private void cargarProfesionales(Model model) {
        try {
            List<String> rolesPermitidos = List.of("PSICOLOGA", "T_SOCIAL");
            List<Usuario> profesionales = usuarioRepository.findByRolIn(rolesPermitidos);
            model.addAttribute("listaProfesionales", profesionales);
        } catch (Exception e) {
            System.err.println("Error cargando profesionales: " + e.getMessage());
        }
    }
}