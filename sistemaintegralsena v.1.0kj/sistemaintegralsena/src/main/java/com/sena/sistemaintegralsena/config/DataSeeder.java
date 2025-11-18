package com.sena.sistemaintegralsena.config;

import com.sena.sistemaintegralsena.entity.Rol;
import com.sena.sistemaintegralsena.entity.Usuario;
import com.sena.sistemaintegralsena.repository.RolRepository;
import com.sena.sistemaintegralsena.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void seedData() {

        // Crear roles si no existen
        if (rolRepository.findByNombre("ADMIN").isEmpty()) {
            rolRepository.save(new Rol("ADMIN"));
        }
        if (rolRepository.findByNombre("PSICOLOGA").isEmpty()) {
            rolRepository.save(new Rol("PSICOLOGA"));
        }
        if (rolRepository.findByNombre("T_SOCIAL").isEmpty()) {
            rolRepository.save(new Rol("T_SOCIAL"));
        }

        // Obtener rol real de BD
        Rol rolAdmin = rolRepository.findByNombre("ADMIN").get();

        // Crear admin 1
        if (!usuarioRepository.existsByEmail("admin1@sena.edu.co")) {

            Usuario admin1 = new Usuario();
            admin1.setNombre("Administrador Principal");
            admin1.setEmail("admin1@sena.edu.co");
            admin1.setPassword(passwordEncoder.encode("Nala123*"));

            // Rol para seguridad (ManyToMany)
            admin1.getRoles().add(rolAdmin);

            // Rol para mostrar en la tabla usuarios
            admin1.setRol("ADMIN");

            usuarioRepository.save(admin1);
        }

        // Crear admin 2
        if (!usuarioRepository.existsByEmail("admin2@sena.edu.co")) {

            Usuario admin2 = new Usuario();
            admin2.setNombre("Administrador Secundario");
            admin2.setEmail("admin2@sena.edu.co");
            admin2.setPassword(passwordEncoder.encode("Nala123*"));

            // Rol para seguridad (ManyToMany)
            admin2.getRoles().add(rolAdmin);

            // Rol para mostrar en la tabla usuarios
            admin2.setRol("ADMIN");

            usuarioRepository.save(admin2);
        }
    }
}
