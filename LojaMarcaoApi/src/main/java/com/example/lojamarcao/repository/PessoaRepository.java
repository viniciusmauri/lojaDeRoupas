package com.example.lojamarcao.repository;

import com.example.lojamarcao.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository <Pessoa, Long>{

}