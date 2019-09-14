package com.example.lojamarcao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojamarcao.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    void delete(Long cod);
}
