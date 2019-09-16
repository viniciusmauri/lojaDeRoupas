package com.example.lojamarcao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojamarcao.model.Pessoa;

public interface PessoaRepository extends JpaRepository <Pessoa, Long>{

}