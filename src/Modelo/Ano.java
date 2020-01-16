package Modelo;

import java.util.ArrayList;

public class Ano implements Comparable<Ano>{
	private int ano; // Armazena o valor numerico do ano
	private ArrayList<Livro> livros; // Contem os livros publicados no referido ano
	
	/** Metodo construtor da classe Ano
	 * @param ano - recebe um valor numerico para ano para instanciar um objeto do tipo Ano
	 */
	public Ano(int ano) {
		this.ano = ano;
		this.livros = new ArrayList<Livro>();
	}
	
	/**
	 * Metodo que retorna o valor do inteiro ano
	 * @return ano - valor inteiro referente ao ano
	 */
	public int getAno() {
		return ano;
	}
	
	/**
	 * Permite a alteracao do inteiro ano
	 * @param ano - representa o novo valor inteiro a ser atribuido a variavel ano
	 * @return void - nao ha retorno 
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	/**
	 * Metodo que permite a insercao de um livro na ArrayList de livros do ano
	 * @param l - Recebe um livro
	 * @return true - se o livro for inserido com sucesso na ArrayList
	 * @return false - se o livro nao for inserido, ja tiver sido inserido ou existir erro em sua insercao na ArrayList
	 * */
	public boolean adicionarLivro(Livro l) {
		return livros.add(l);
	}
		
	/**
	 * Metodo que permite a remocao de um livro na ArrayList de livros do ano
	 * @param l - Recebe um livro
	 * @return true - se o livro for encontrado e removido com sucesso
	 * @return false - se o livro nao for encontrado ou nao for removido
	 */
	public boolean removerLivro(Livro l) {
		return livros.remove(l);		
	}

	/**  
	 * Metodo que retorna a quantidade de livros do ano
	 * @return inteiro que retorna a quantidade de elementos no ArrayList livros 
	 * */
	public int getQuantidade() {
		return livros.size();
	}
	
	/**
	 * Metodo que exibe as informacoes de todos os objetos (livros) contidos no ArrayList livros
	 */
	public void exibirLivros() {
		for (int c = 0; c < livros.size(); c++) {
			System.out.print("\n"+ (c+1) + ". ");
			 livros.get(c).imprimir();
		}
	}

	/**
	 * Metodo que retorna uma String com os títulos de todos os objetos (livros) contidos no ArrayList livros
	 * @return titulos dos livros - Uma string com os titulos de todos os livros numerados
	 */
	public String listagemLivros(){
		String texto = "";
		for(int c = 0; c < livros.size(); c++)
			texto += (livros.get(c).getTitulo() + "\n");
		return texto;		
	}
	
	/**
	 * Sobrescrita do metodo toString
	 * @return ano - retorna o valor de ano como sendo do tipo String
	 * */
	@Override
	public String toString() {
		return String.valueOf(ano);
	}
	
	/**
	 * Sobrescrita do metodo compareTo da interface Comparable
	 * @return retorna um valor inteiro (1, -1 ou 0) referente ao resultado de uma comparacao com outro objeto de mesmo tipo	 
	 */
	@Override
	public int compareTo(Ano a) {
		if (this.ano > a.getAno())
			return 1;
		if (this.ano < a.getAno())
			return -1;
		return 0;			
	}	
}