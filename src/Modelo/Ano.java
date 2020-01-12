package Modelo;

import java.util.ArrayList;

public class Ano implements Comparable<Ano>{
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
		return livros.add(l);
	}
	
	public boolean removerLivro(Livro l) {
		return livros.remove(l);
	}

	public int getQuantidade() {
		return livros.size();
	}
	
	public void exibirLivros() {
		for (int c = 0; c < livros.size(); c++) {
			System.out.println(c+1 + ". ");
			 livros.get(c).imprimir();
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
		return String.valueOf(ano);
	}
	
	@Override
	public int compareTo(Ano a) {
		if (this.ano > a.getAno())
			return 1;
		if (this.ano < a.getAno())
			return -1;
		return 0;			
	}
	
	
	
}
