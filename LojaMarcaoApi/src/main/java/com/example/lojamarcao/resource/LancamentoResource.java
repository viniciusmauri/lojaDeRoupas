package com.example.lojamarcao.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojamarcao.event.RecursoCriadoEvent;
import com.example.lojamarcao.exceptionhandler.LojaMarcaoExceptionHandler;
import com.example.lojamarcao.model.Lancamento;
import com.example.lojamarcao.repository.LancamentoRepository;
import com.example.lojamarcao.repository.filter.LancamentoFilter;
import com.example.lojamarcao.repository.projection.ResumoLancamento;
import com.example.lojamarcao.service.LancamentoService;
import com.example.lojamarcao.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    // Método para pesquisar os lançamentos
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and $oauth2.hasScope('read')")
    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
	return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }

    // Método para pesquisar os resumos de lancamentos
    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and $oauth2.hasScope('read')")
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
	return lancamentoRepository.resumir(lancamentoFilter, pageable);
    }

    // Método para cadastrar um lançamento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and $oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> cadastrarLancamento(@Valid @RequestBody Lancamento lancamento,
	    HttpServletResponse response) {
	Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
	publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCod()));
	return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    // Método para buscar um lançamento
    @GetMapping("/{cod}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and $oauth2.hasScope('read')")
    public ResponseEntity<Lancamento> buscaPeloCodLancamento(@PathVariable Long cod) {
	return this.lancamentoRepository.findById(cod).map(lancamento -> ResponseEntity.ok(lancamento))
		.orElse(ResponseEntity.notFound().build());
    }

    // Método para deletar um lançamento
    @DeleteMapping("/{cod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and $oauth2.hasScope('write')")
    public void remover(@PathVariable Long cod) {
	this.lancamentoRepository.deleteById(cod);
    }

    // Método para atualizar um lançamento
    @PutMapping("/{cod}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and $oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long cod, @Valid @RequestBody Lancamento lancamento) {
	Lancamento lancamentoSalvo = lancamentoService.atualizar(cod, lancamento);
	return ResponseEntity.ok(lancamentoSalvo);
    }

    // Método para lançar exceção caso tente salvar um lançamento para uma pessoa
    // inexistente
    @ExceptionHandler({ PessoaInexistenteOuInativaException.class })
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException e) {
	String mensagemUsuario = messageSource.getMessage("pessoa-inexistente-ou-inativa", null,
		LocaleContextHolder.getLocale());
	String mensagemDev = e.toString();
	List<LojaMarcaoExceptionHandler.Erro> erros = Arrays
		.asList(new LojaMarcaoExceptionHandler.Erro(mensagemUsuario, mensagemDev));
	return ResponseEntity.badRequest().body(erros);
    }

}