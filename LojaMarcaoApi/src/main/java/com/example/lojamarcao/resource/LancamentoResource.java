package com.example.lojamarcao.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.lojamarcao.model.Lancamento;
import com.example.lojamarcao.repository.LancamentoRepository;
import com.example.lojamarcao.service.LancamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    //Método para listar os lançamentos
    @GetMapping
    public List<Lancamento> listar() {
        return lancamentoRepository.findAll();
    }

    //Método para cadastrar um lançamento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Lancamento> cadastrarLancamento(@Valid @RequestBody Lancamento lancamento,
            HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cod}")
                .buildAndExpand(lancamentoSalvo.getCod()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(lancamentoSalvo);
    }

    //Método para buscar um lançamento
    @GetMapping("/{cod}")
    public ResponseEntity buscaPeloCodLancamento(@PathVariable Long cod) {
        return this.lancamentoRepository.findById(cod).map(lancamento -> ResponseEntity.ok(lancamento))
                .orElse(ResponseEntity.notFound().build());
    }

    //Método para deletar um lançamento
    @DeleteMapping("/{cod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cod){
        this.lancamentoRepository.deleteById(cod);
    }

    //Método para atualizar um lançamento
    @PutMapping("/{cod}")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long cod, @Valid @RequestBody Lancamento lancamento){
        Lancamento lancamentoSalvo = lancamentoService.atualizar(cod, lancamento);
        return ResponseEntity.ok(lancamentoSalvo);
    }
}