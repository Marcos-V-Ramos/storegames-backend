package br.com.marcos.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.marcos.blogpessoal.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List<Produto> findAllByDescricaoContainingIgnoreCase(@RequestParam String descricao);
}