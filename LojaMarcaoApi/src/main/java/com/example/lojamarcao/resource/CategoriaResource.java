package com.example.lojamarcao.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lojamarcao.event.RecursoCriadoEvent;
import com.example.lojamarcao.model.Categoria;
import com.example.lojamarcao.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria,
			HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCod()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@GetMapping("/{cod}")
	public ResponseEntity buscarPeloCod(@PathVariable Long cod) {
		return this.categoriaRepository.findById(cod).map(categoria -> ResponseEntity.ok(categoria))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{cod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cod){
        categoriaRepository.delete(cod);
    }

    @PutMapping("/{cod}")
    public Categoria atualizar(@PathVariable Long cod, @Valid @RequestBody Categoria categoria){
	    Categoria categoriaSalva = this.categoriaRepository.findById(cod).orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(categoria, categoriaSalva, "cod");
        return this.categoriaRepository.save(categoriaSalva);
    }
}
