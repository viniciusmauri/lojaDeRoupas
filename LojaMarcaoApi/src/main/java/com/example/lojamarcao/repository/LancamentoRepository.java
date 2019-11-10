package com.example.lojamarcao.repository;

import com.example.lojamarcao.model.Lancamento;
import com.example.lojamarcao.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
