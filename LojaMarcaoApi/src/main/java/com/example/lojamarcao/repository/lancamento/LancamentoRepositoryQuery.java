package com.example.lojamarcao.repository.lancamento;

import com.example.lojamarcao.model.Lancamento;
import com.example.lojamarcao.repository.filter.LancamentoFilter;
import com.example.lojamarcao.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
    
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
    

}