package Modelo;

import java.util.ArrayList;

public class Autor implements Comparable<Autor>{
	private String nome;
	private ArrayList<Livro> livros;

	/**
	 * Metodo construtor
	 * @param nome - recebe o nome do autor para construir o objeto 
	 */
	public Autor(String nome) {
		this.nome = nome;
		this.livros = new ArrayList<Livro>();
	}

	/**
	 * Metodo que retorna o valor da variavel nome
	 * @return nome - retorna a String nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Permite a alteracao da String nome
	 * @param nome- representa a nova String a ser atribuida a variavel nome
	 * @return void - nao ha retorno 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo que permite a insercao de um livro na ArrayList de livros do autor
	 * @param l - Recebe um livro
	 * @return true - se o livro for inserido com sucesso na ArrayList
	 * @return false - se o livro nao for inserido ou existir erro de sua insercao na ArrayList
	 * */
	public boolean adicionarLivro(Livro l) {
		return livros.add(l);		
	}
	
	/**
	 * Metodo que permite a remocao de um livro na ArrayList de livros do autor
	 * @param l - Recebe um livro
	 * @return true - se o livro for encontrado e removido com sucesso
	 * @return false - se o livro nao for encontrado ou nao for removido
	 */
	public boolean removerLivro(Livro l) {
		return livros.remove(l);
	}

	/**  
	 * Metodo que retorna a quantidade de livros do autor
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
			System.out.println(c+1 + ". " + livros.get(c).getTitulo());
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
	 * @return nome - retorna o valor da String nome
	 * */
	@Override
	public String toString() {
		return nome;
	}

	/**
	 * Sobrescrita do metodo compareTo da interface Comparable
	 * @return retorna um valor inteiro (1, -1 ou 0) referente ao resultado de uma comparacao com outro objeto de mesmo tipo	 
	 */
	@Override
	public int compareTo(Autor autor) {
		return nome.compareTo(autor.getNome());		
	}
}
