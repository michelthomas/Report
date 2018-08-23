package br.com.cpsoftware;

public class Usuario {

	private Long id;
	private String nome;
	private double cpf;
	private Tipo tipo;

	public Usuario(Long id, String nome, double cpf, Tipo tipo) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.tipo = tipo;
	}
	
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
	public double getCpf() {
		return cpf;
	}
	public void setCpf(double cpf) {
		this.cpf = cpf;
	}


	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}
