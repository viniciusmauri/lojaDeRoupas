package com.example.lojamarcao.service;

import com.example.lojamarcao.model.Pessoa;
import com.example.lojamarcao.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	// Serviço para atualizar uma pessoa
	public Pessoa atualizar(Long cod, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloCod(cod);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "cod");
		return this.pessoaRepository.save(pessoaSalva);
	}

	// Serviço para buscar uma pessoa pelo código
	public Pessoa buscarPessoaPeloCod(Long cod) {
		Pessoa pessoaSalva = this.pessoaRepository.findById(cod)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pessoaSalva;
	}

	// Serviço para atualizar se a pessoa está ativa ou não no sistema
	public void atualizarPropriedadeAtivo(Long cod, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCod(cod);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
}
