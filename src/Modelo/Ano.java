package Modelo;

import java.util.ArrayList;

public class Ano {
	private int ano;
	private ArrayList<Livro> livros;
	
	public Ano(int ano) {
		this.ano = ano;
		this.livros = new ArrayList<Livro>();
	}
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
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
			System.out.println("\n" + (c+1) + ". [" + livros.get(c).getN_ebook() + "] [" + livros.get(c).getTitulo() + "]");
			System.out.println("Autor: [" + livros.get(c).getAutor().getNome() + "]");
			System.out.println("Lançamento: [" + String.valueOf(livros.get(c).getMes()) + " de " + livros.get(c).getAno() + "]");
			System.out.println("Disponível em: [" + livros.get(c).getLink() + "]");						
		}
	}
	
}
