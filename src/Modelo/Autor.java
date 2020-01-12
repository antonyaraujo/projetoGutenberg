package Modelo;

import java.util.ArrayList;

public class Autor implements Comparable<Autor>{
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
	
	public boolean removerLivro(Livro l) {
		return livros.remove(l);
	}

	public int getQuantidade() {
		return livros.size();
	}
	
	public void exibirLivros() {
		for (int c = 0; c < livros.size(); c++) {
			System.out.println(c+1 + ". " + livros.get(c).getTitulo());
		}
	}
	
	public String listagemLivros(){
		String texto = "";
		for(int c = 0; c < livros.size(); c++)
			texto += (livros.get(c).getTitulo() + "\n");
		return texto;		
	}
	
	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(Autor autor) {
		return nome.compareTo(autor.getNome());		
	}
}
