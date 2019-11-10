package com.example.lojamarcao.service;

import com.example.lojamarcao.model.Lancamento;
import com.example.lojamarcao.model.Pessoa;
import com.example.lojamarcao.repository.LancamentoRepository;
import com.example.lojamarcao.repository.PessoaRepository;
import com.example.lojamarcao.repository.filter.LancamentoFilter;
import com.example.lojamarcao.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
        return lancamentoRepository.findAll();
    }

    // Serviço para atualizar um lançamento
    public Lancamento atualizar(Long cod, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscarLancamentoPeloCod(cod);
        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "cod");
        return this.lancamentoRepository.save(lancamentoSalvo);
    }

    // Serviço para buscar os lançamentos pelo código
    private Lancamento buscarLancamentoPeloCod(Long cod) {
        Lancamento lancamentoSalvo = this.lancamentoRepository.findById(cod)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return lancamentoSalvo;
    }

    // Serviço para salvar lançamentos
    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCod())
                .orElseThrow(() -> new PessoaInexistenteOuInativaException());
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }
}
