package com.example.lojamarcao.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> cadastrarPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
       publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCod()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{cod}")
    public ResponseEntity buscaPeloCodPessoa(@PathVariable Long cod) {
        return this.pessoaRepository.findById(cod).map(pessoa -> ResponseEntity.ok(pessoa))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cod){
        pessoaRepository.delete(cod);
    }

    @PutMapping("/{cod}")
    public Pessoa atualizar(@PathVariable Long cod, @Valid @RequestBody Pessoa pessoa){
        Pessoa pessoaSalva = this.pessoaRepository.findById(cod).orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(pessoa, pessoaSalva, "cod");
        return this.pessoaRepository.save(pessoaSalva);
    }
}