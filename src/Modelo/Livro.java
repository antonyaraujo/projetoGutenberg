package Modelo;

public class Livro implements Comparable<Livro>{
	private int n_ebook;
	private String titulo;
	private Autor autor;
	private char[] mes = new char[3];
	private Ano ano;
	private String link;
	
	public Livro(int n_ebook, String titulo, String autor, char[] mes, int ano, String link) {
		this.n_ebook = n_ebook;
		this.titulo = titulo;
		this.autor = new Autor(autor);
		this.mes = mes;
		this.ano = new Ano(ano);
		this.link = link;
	}	
	
	public Livro(int n_ebook) {
		this.n_ebook = n_ebook;
		this.titulo = null;
		this.autor = null;
		this.mes = null;
		this.ano = null;
		this.link = null;
	}
	
	public int getN_ebook() {
		return n_ebook;
	}
	public void setN_ebook(int n_ebook) {
		this.n_ebook = n_ebook;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public char[] getMes() {
		return mes;
	}
	public void setMes(char[] mes) {
		this.mes = mes;
	}
	public Ano getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano.setAno(ano);;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public void imprimir() {
		System.out.println("[" + n_ebook + "] [" + titulo + "]");
		System.out.println("Autor: [" + autor.getNome() + "]");
		System.out.println("Lançamento: [" + String.valueOf(mes) + " de " + ano + "]");
		System.out.println("Disponível em: [" + link + "]");
	}

	@Override
	public String toString() 
	{
		return n_ebook + " " + titulo;
	}
	
	@Override
	public int compareTo(Livro livro) {
		if (n_ebook > livro.getN_ebook())
			return 1;
		if (n_ebook < livro.getN_ebook())
			return -1;
		return 0;		
	}
	
}
