package br.com.marcos.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcos.blogpessoal.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}