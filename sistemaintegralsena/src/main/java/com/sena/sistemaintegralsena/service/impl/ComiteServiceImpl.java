package com.sena.sistemaintegralsena.service.impl;

import com.sena.sistemaintegralsena.entity.Aprendiz;
import com.sena.sistemaintegralsena.entity.Comite;
import com.sena.sistemaintegralsena.entity.Usuario;
import com.sena.sistemaintegralsena.repository.AprendizRepository;
import com.sena.sistemaintegralsena.repository.ComiteRepository;
import com.sena.sistemaintegralsena.repository.UsuarioRepository;
import com.sena.sistemaintegralsena.service.ComiteService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ComiteServiceImpl implements ComiteService {

    private final ComiteRepository comiteRepository;
    private final AprendizRepository aprendizRepository;
    private final UsuarioRepository usuarioRepository;

    public ComiteServiceImpl(ComiteRepository comiteRepository, 
                             AprendizRepository aprendizRepository,
                             UsuarioRepository usuarioRepository) {
        this.comiteRepository = comiteRepository;
        this.aprendizRepository = aprendizRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Comite> listarTodos() {
        return comiteRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public void guardar(Comite comite, Long aprendizId, String emailProfesional) {
        // 1. Asociar Aprendiz
        Aprendiz aprendiz = aprendizRepository.findById(aprendizId)
                .orElseThrow(() -> new RuntimeException("Aprendiz no encontrado"));
        comite.setAprendiz(aprendiz);

        // 2. Asociar Profesional
        Usuario profesional = usuarioRepository.findByEmail(emailProfesional)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));
        
        // 🔑 CORRECCIÓN: Usar 'setProfesional' para coincidir con la Entidad Comite
        comite.setProfesional(profesional); 

        comiteRepository.save(comite);
    }

    @Override
    @Transactional(readOnly = true)
    public Comite buscarPorId(Long id) {
        return comiteRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        comiteRepository.deleteById(id);
    }
}