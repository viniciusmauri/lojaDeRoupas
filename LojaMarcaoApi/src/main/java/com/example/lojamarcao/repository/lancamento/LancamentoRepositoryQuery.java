package com.example.lojamarcao.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.lojamarcao.model.Lancamento;
import com.example.lojamarcao.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}