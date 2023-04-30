package br.com.marcos.blogpessoal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatório.")
	@Size(min = 5, max = 60, message = "O atributo nome deve ter entre 5 e 60 caracteres.")
	private String nome;
	
	@NotBlank(message = "O atributo descricao é obrigatório.")
	@Size(min = 5, max = 200, message = "O atributo descricao deve ter entre 5 e 200 caracteres")
	private String descricao;
	
	@NotNull
	private Double valor;
	
	private String imagem;
	
	private int estoque;
	
	@NotBlank(message = "O atributo codigo é obrigatório.")
	@Size(min = 5, max = 30, message = "O atributo codigo deve ter entre 5 e 30 caracteres.")
	private String codigo;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getImagem() {
		return imagem;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
}