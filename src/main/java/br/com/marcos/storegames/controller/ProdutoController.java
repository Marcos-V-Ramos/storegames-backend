package br.com.marcos.storegames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import br.com.marcos.storegames.entity.Categoria;
import br.com.marcos.storegames.entity.Produto;
import br.com.marcos.storegames.repository.CategoriaRepository;
import br.com.marcos.storegames.repository.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Produto>> getAllByDescricao(@PathVariable String descricao) {
		List<Produto> produto = produtoRepository.findAllByDescricaoContainingIgnoreCase(descricao);
		
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto (@Valid @RequestBody Produto produto) {
		
		Optional<Categoria> categoria = categoriaRepository.findById(produto.getCategoria().getId());
		if (categoria.isPresent()) {
			return ResponseEntity.status(201).body(produtoRepository.save(produto));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe nenhuma categoria associada à este ID!.");
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto (@Valid @RequestBody Produto produto) {
		
		Optional<Categoria> findCategoria = categoriaRepository.findById(produto.getCategoria().getId());
		
		if (findCategoria.isPresent()) {
		
			Optional<Produto> findProduto = produtoRepository.findById(produto.getId());
			
			if (findProduto.isPresent()) {
				return ResponseEntity.status(201).body(produtoRepository.save(produto));
			}
			return ResponseEntity.notFound().build();
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe nenhuma categoria associada à este ID!");
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isPresent()) {
			produtoRepository.delete(produto.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}