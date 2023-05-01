package br.com.marcos.storegames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.storegames.entity.Categoria;
import br.com.marcos.storegames.repository.CategoriaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.status(200).body(categoriaRepository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			return ResponseEntity.status(200).body(categoria.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> getAllByDescricao(@PathVariable String descricao) {
		List<Categoria> categorias = categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao);
		
		return ResponseEntity.status(200).body(categorias);
	}
	
	@PostMapping()
	public ResponseEntity<Categoria> saveCategoria(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(201).body(categoriaRepository.save(categoria));
	}
	
	@PutMapping()
	public ResponseEntity<Categoria> updateCategoria(@Valid @RequestBody Categoria categoria) {
		Optional<Categoria> findCategoria = categoriaRepository.findById(categoria.getId());
		
		if (findCategoria.isPresent()) {
			return ResponseEntity.status(200).body(categoriaRepository.save(categoria));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("id/{id}")
	public ResponseEntity<Void> removeById(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if(categoria.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}