package com.example.lojamarcao.resource;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.lojamarcao.event.RecursoCriadoEvent;
import com.example.lojamarcao.exceptionhandler.LojaMarcaoExceptionHandler;
import com.example.lojamarcao.model.Lancamento;
import com.example.lojamarcao.repository.LancamentoRepository;
import com.example.lojamarcao.service.LancamentoService;

import com.example.lojamarcao.service.exception.PessoaInexistenteOuInativaException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LojaMarcaoExceptionHandler lojaMarcaoExceptionHandler;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    //Método para listar os lançamentos
    @GetMapping
    public List<Lancamento> listar() {
        return lancamentoRepository.findAll();
    }

    //Método para cadastrar um lançamento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Lancamento> cadastrarLancamento(@Valid @RequestBody Lancamento lancamento,
            HttpServletResponse response){
        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCod()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
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

    //Método para lançar exceção caso tente salvar um lançamento para uma pessoa inexistente
    @ExceptionHandler({ PessoaInexistenteOuInativaException.class})
        public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException e){
            String mensagemUsuario = messageSource.getMessage("pessoa-inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
            String mensagemDev = e.toString();
            List<LojaMarcaoExceptionHandler.Erro> erros = Arrays.asList(new LojaMarcaoExceptionHandler.Erro(mensagemUsuario, mensagemDev));
            return ResponseEntity.badRequest().body(erros);
        }
}