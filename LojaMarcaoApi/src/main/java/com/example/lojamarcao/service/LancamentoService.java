package com.example.lojamarcao.service;

import com.example.lojamarcao.model.Lancamento;
import com.example.lojamarcao.repository.LancamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    //Serviço para atualizar um lançamento
    public Lancamento atualizar(Long cod, Lancamento lancamento){
        Lancamento lancamentoSalvo = buscarLancamentoPeloCod(cod);
        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "cod");
        return this.lancamentoRepository.save(lancamentoSalvo);
    }

    //Serviço para buscar os lançamentos pelo código
    private Lancamento buscarLancamentoPeloCod(Long cod){
        Lancamento lancamentoSalvo = this.lancamentoRepository.findById(cod).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return lancamentoSalvo;
    }
}
