package com.example.lojamarcao.resource;

import com.example.lojamarcao.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.lojamarcao.event.RecursoCriadoEvent;
import com.example.lojamarcao.model.Pessoa;
import com.example.lojamarcao.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    // Método para Listar as pessoas
    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    // Método para cadastrar uma pessoa
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> cadastrarPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCod()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    // Método para Buscar uma pessoa pelo código
    @GetMapping("/{cod}")
    public ResponseEntity<Pessoa> buscaPeloCodPessoa(@PathVariable Long cod) {
        return this.pessoaRepository.findById(cod).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Método para deletar uma pessoa do Sistema
    @DeleteMapping("/{cod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cod) {
        this.pessoaRepository.deleteById(cod);
    }

    // Método para Atualizar se a pessoa está ativa ou não no sistema
    @PutMapping("/{cod}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivo(@PathVariable Long cod, @RequestBody Boolean ativo) {
        pessoaService.atualizarPropriedadeAtivo(cod, ativo);
    }

    // Método para atualizar uma pessoa
    @PutMapping("/{cod}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long cod, @Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaService.atualizar(cod, pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }
}