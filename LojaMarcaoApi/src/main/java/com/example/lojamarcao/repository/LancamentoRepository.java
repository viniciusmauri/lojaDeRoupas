package com.example.lojamarcao.repository;

import com.example.lojamarcao.model.Lancamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
    
}
