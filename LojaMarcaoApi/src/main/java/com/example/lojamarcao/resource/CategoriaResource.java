package com.example.lojamarcao.resource;

import com.example.lojamarcao.event.RecursoCriadoEvent;
import com.example.lojamarcao.model.Categoria;
import com.example.lojamarcao.repository.CategoriaRepository;
import com.example.lojamarcao.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
    private CategoriaService categoriaService;

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
        this.categoriaRepository.deleteById(cod);
    }

    @PutMapping("/{cod}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long cod, @Valid @RequestBody Categoria categoria){
	    Categoria categoriaSalva = categoriaService.atualizar(cod, categoria);
	    return ResponseEntity.ok(categoriaSalva);
    }
}
