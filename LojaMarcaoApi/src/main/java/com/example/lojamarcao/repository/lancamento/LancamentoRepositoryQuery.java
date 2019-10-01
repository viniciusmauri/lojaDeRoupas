package com.example.lojamarcao.repository.lancamento;

import java.util.List;

import com.example.lojamarcao.model.Lancamento;
import com.example.lojamarcao.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}