package com.example.lojamarcao.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojamarcao.event.RecursoCriadoEvent;
import com.example.lojamarcao.model.Categoria;
import com.example.lojamarcao.repository.CategoriaRepository;
import com.example.lojamarcao.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    // Método para listar as categorias
    @CrossOrigin(maxAge = 10)
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and $oauth2.hasScope('read')")
    public List<Categoria> listar() {
	return categoriaRepository.findAll();
    }

    // Método para criar uma categoria
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and $oauth2.hasScope('read')")
    public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria,
	    HttpServletResponse response) {
	Categoria categoriaSalva = categoriaRepository.save(categoria);
	publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCod()));
	return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    // Método para buscar uma Categoria
    @GetMapping("/{cod}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and $oauth2.hasScope('write')")
    public ResponseEntity<Categoria> buscarPeloCod(@PathVariable Long cod) {
	return this.categoriaRepository.findById(cod).map(categoria -> ResponseEntity.ok(categoria))
		.orElse(ResponseEntity.notFound().build());
    }

    // Método para deletar uma Categoria
    @DeleteMapping("/{cod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and $oauth2.hasScope('write')")
    public void remover(@PathVariable Long cod) {
	this.categoriaRepository.deleteById(cod);
    }

    // Método para atualizar uma Categoria
    @PutMapping("/{cod}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and $oauth2.hasScope('write')")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long cod, @Valid @RequestBody Categoria categoria) {
	Categoria categoriaSalva = categoriaService.atualizar(cod, categoria);
	return ResponseEntity.ok(categoriaSalva);
    }

    // Método para atualizar o nome de uma categoria
    @PutMapping("/{cod}/nome")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and $oauth2.hasScope('write')")
    public void atualizarNomeCategoria(@PathVariable Long cod, @RequestBody String nome) {
	categoriaService.atualizarNomeCategoria(cod, nome);
    }
}
