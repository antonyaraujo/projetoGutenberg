package Modelo;

import java.util.Comparator;

public class No<T extends Comparable<T>> implements Comparable<T>{	
	private T objeto;
	private No<T> esquerda, direita;	
	private int altura;
	private Comparator<T> comparador;
	
	public No(T objeto, Comparator<T> comparador) {				
		this.objeto = objeto;
		this.comparador = comparador;
		esquerda = direita = null;
		altura = 0;
	}
	
	public T getValor() {
		return objeto;
	}
	
	public void setValor(Object Object) {
		this.objeto = objeto;
	}
	
	public No<T> getEsquerda() {
		return esquerda;
	}
	
	public void setEsquerda(No<T> esquerda) {
		this.esquerda = esquerda;
	}
	
	public No getDireita() {
		return direita;
	}
	public void setDireita(No<T> direita) {
		this.direita = direita;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}

	public T getObjeto() {
		return objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	@Override
	public int compareTo(T obj) {
		if (comparador == null) 			
			return objeto.compareTo(obj);
		return comparador.compare(objeto, obj);
	}

		
}
