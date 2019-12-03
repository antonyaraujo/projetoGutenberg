package Modelo;

public class Autor {
	private String nome;
	private int quantidade;
	
	public Autor(String nome) {
		this.nome = nome;
		this.quantidade = 0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
