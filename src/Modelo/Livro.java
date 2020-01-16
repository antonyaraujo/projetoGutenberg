package Modelo;

public class Livro implements Comparable<Livro>{
	private int n_ebook; 
	private String titulo;
	private Autor autor; 
	private char[] mes = new char[3];
	private Ano ano; 
	private String link;
	
	/** 
	 * Metodo construtor com parametros para todas as variaveis
	 * */
	public Livro(int n_ebook, String titulo, String autor, char[] mes, int ano, String link) {
		this.n_ebook = n_ebook;
		this.titulo = titulo;
		this.autor = new Autor(autor);
		this.mes = mes;
		this.ano = new Ano(ano);
		this.link = link;
	}	
	
	/**
	 * Metodo construtor com parametro apenas para o numero do ebook
	 * */
	public Livro(int n_ebook) {
		this.n_ebook = n_ebook;		
	}
	
	/**
	 * Metodo que retorna o valor da variavel n_ebook
	 * @return n_ebook - valor inteiro referente ao n_ebook
	 */
	public int getN_ebook() {
		return n_ebook;
	}
	
	/**
	 * Permite a alteracao do valor do n_ebook
	 * @param n_ebook - inteiro que representa o novo valor a ser atribuido a variavel n_ebook
	 * @return void - não há retorno 
	 */
	public void setN_ebook(int n_ebook) {
		this.n_ebook = n_ebook;
	}
	
	/**
	 * Metodo que retorna o valor da variavel titulo
	 * @return titulo - valor do tipo String referente ao titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Permite a alteracao do valor do titulo
	 * @param titulo - String que representa o novo valor a ser atribuido a variavel titulo
	 * @return void - não há retorno 
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 * Metodo que retorna o valor da variavel autor
	 * @return autor - valor referente ao autor que e um objeto do tipo Autor
	 */
	public Autor getAutor() {
		return autor;
	}
	
	/**
	 * Permite a alteracao do valor do autor
	 * @param autor - objeto do tipo Autor que representa o novo valor a ser atribuido a variavel autor
	 * @return void - não há retorno 
	 */
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	/**
	 * Metodo que retorna o valor do vetor mes
	 * @return mes - retorna o vetor mes do tipo char 
	 */
	public char[] getMes() {
		return mes;
	}
	
	/**
	 * Permite a alteracao do valor do vetor mes
	 * @param ano - vetor do tipo char que representa o novo vetor a ser atribuido ao vetor mes
	 * @return void - não há retorno 
	 */
	public void setMes(char[] mes) {
		this.mes = mes;
	}
	
	/**
	 * Metodo que retorna o valor da variavel ano
	 * @return ano - valor referente ao ano que e um objeto do tipo Ano
	 */
	public Ano getAno() {
		return ano;
	}
	
	/**
	 * Permite a alteracao do valor de ano
	 * @param ano - inteiro que representa o novo valor a ser atribuido a variavel inteira ano do objeto ano
	 * @return void - não há retorno 
	 */
	public void setAno(int ano) {
		this.ano.setAno(ano);;
	}
	
	/**
	 * Metodo que retorna o valor da variavel link
	 * @return link - valor do tipo String referente ao link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * Permite a alteracao do valor de link
	 * @param link - String  que representa o novo valor a ser atribuido a variavel link
	 * @return void - não há retorno 
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * Metodo que exibe as informacoes do livro no terminal
	 * @return void - nao ha retorno
	 * */
	public void imprimir() {
		System.out.println("[" + n_ebook + "] [" + titulo + "]");
		System.out.println("Autor: [" + autor.getNome() + "]");
		System.out.println("Lançamento: [" + String.valueOf(mes) + " de " + ano + "]");
		System.out.println("Disponível em: [" + link + "]");
	}

	/**
	 * Sobrescrita do metodo toString
	 * @return n_ebook e titulo - retorna o valor do n_ebook e titulo ambos em uma unica String
	 * */
	@Override
	public String toString() 
	{
		return n_ebook + " " + titulo;
	}
	
	/**
	 * Sobrescrita do metodo compareTo da interface Comparable
	 * @return retorna um valor inteiro (1, -1 ou 0) referente ao resultado de uma comparacao com outro objeto de mesmo tipo	 
	 */
	@Override
	public int compareTo(Livro livro) {
		if (n_ebook > livro.getN_ebook())
			return 1;
		if (n_ebook < livro.getN_ebook())
			return -1;
		return 0;		
	}
	
}