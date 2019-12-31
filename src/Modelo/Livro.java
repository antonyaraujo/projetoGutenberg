package Modelo;

public class Livro{
	private int n_ebook;
	private String titulo;
	private Autor autor;
	private char[] mes = new char[3];
	private int ano;
	private String link;
	
	public Livro(int n_ebook, String titulo, String autor, char[] mes, int ano, String link) {
		this.n_ebook = n_ebook;
		this.titulo = titulo;
		this.autor = new Autor(autor);
		this.mes = mes;
		this.ano = ano;
		this.link = link;
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
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public void imprimir() {
		System.out.print("Código: " + n_ebook);
		System.out.print("Titulo: " + titulo);
		System.out.print("Autor: " + autor.getNome());		
	}
	
	public int compareTo(String c) {	
		int codigo = Integer.parseInt(c);
		if (n_ebook > codigo)
			return 1;
		else if (n_ebook < codigo)
			return -1;
		else
			return 0;
	}
}
