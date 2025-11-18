package com.sena.sistemaintegralsena.service;

import com.sena.sistemaintegralsena.entity.Usuario;

public interface UsuarioService {
    void guardar(Usuario usuario);
    Usuario buscarPorEmail(String email);

    // Nuevo método necesario para registro con rol seleccionado
    void guardarConRol(Usuario usuario, String rolNombre);
}
