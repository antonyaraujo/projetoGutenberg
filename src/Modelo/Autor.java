package Modelo;

import java.util.ArrayList;

public class Autor {
	private String nome;
	private ArrayList<Livro> livros;

	public Autor(String nome) {
		this.nome = nome;
		this.livros = new ArrayList<Livro>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean adicionarLivro(Livro l) {
		livros.add(l);
		return true;
	}

	public int getQuantidade() {
		return livros.size();
	}
	
	public void exibirLivros() {
		for (int c = 0; c < livros.size(); c++) {
			System.out.println(c+1 + ". " + livros.get(c).getTitulo());
		}
	}
}
