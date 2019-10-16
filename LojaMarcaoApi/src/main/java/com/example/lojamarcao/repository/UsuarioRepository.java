package com.example.lojamarcao.repository;

import java.util.Optional;

import com.example.lojamarcao.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByEmail(String email); 
}