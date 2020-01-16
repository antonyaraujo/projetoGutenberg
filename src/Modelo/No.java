package Modelo;

import java.util.Comparator;

public class No<T extends Comparable<T>> implements Comparable<T> {
	private T objeto;
	private No<T> esquerda, direita;
	private int altura;
	private Comparator<T> comparador;

	/**
	 * Metodo construtor
	 * 
	 * @param objeto     - recebe um objeto do tipo T
	 * @param comparador - recebe uma interface comparador do tipo T
	 */
	public No(T objeto, Comparator<T> comparador) {
		this.objeto = objeto;
		this.comparador = comparador;
		esquerda = direita = null;
		altura = 0;
	}

	/**
	 * Metodo que retorna o valor do no a esquerda
	 * @return esquerda - no posicionado a esquerda do no atual
	 */
	public No<T> getEsquerda() {
		return esquerda;
	}

	/**
	 * Permite a alteracao do no a esquerda
	 * @param esquerda - representa o novo no a ser atribuido a ao no a esquerda do atual
	 * @return void - não há retorno 
	 */
	public void setEsquerda(No<T> esquerda) {
		this.esquerda = esquerda;
	}

	/**
	 * Metodo que retorna o valor da variavel direita
	 * 
	 * @return direita - no posicionado a direita do no atual
	 */
	public No getDireita() {
		return direita;
	}

	/**
	 * Permite a alteracao do no a direita
	 * @param direita - representa o novo no a ser atribuido ao no a direita do atual
	 * @return void - não há retorno 
	 */
	public void setDireita(No<T> direita) {
		this.direita = direita;
	}

	/**
	 * Metodo que retorna o valor da altura do no em relacao a arvore
	 * 
	 * @return altura - valor inteiro que representa a quantidade de nos existentes
	 *         entre a raiz e o no em questao
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Permite a alteracao do inteiro altura
	 * @param altura - representa o novo valor inteiro do inteiro altura
	 * @return void - não há retorno 
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Metodo que retorna o um objeto do tipo T
	 * 
	 * @return objeto - objeto do tipo T
	 */
	public T getObjeto() {
		return objeto;
	}

	/**
	 * Permite a alteracao do objeto do tipo T
	 * @param objeto - representa o novo objeto do tipo T a ser atribuido ao objeto do no
	 * @return void - não há retorno 
	 */
	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	/**
	 * Sobrescrita do metodo compareTo da interface Comparable
	 * @return retorna um valor inteiro (1, -1 ou 0) referente ao resultado de uma comparacao com outro objeto de mesmo tipo	 
	 */
	@Override
	public int compareTo(T obj) {
		if (comparador == null)
			return objeto.compareTo(obj);
		return comparador.compare(objeto, obj);
	}

}
