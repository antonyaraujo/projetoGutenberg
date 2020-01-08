package Modelo;

public class No<T> {
	private String codigo;
	private T objeto;
	private No esquerda;
	private No direita;
	private int altura;
	
	public No(String codigo, T objeto) {		
		this.codigo = codigo;
		this.objeto = objeto;
		esquerda = direita = null;
		altura = 0;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public T getValor() {
		return objeto;
	}
	public void setValor(Object Object) {
		this.objeto = objeto;
	}
	public No getEsquerda() {
		return esquerda;
	}
	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}
	public No getDireita() {
		return direita;
	}
	public void setDireita(No direita) {
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
}
