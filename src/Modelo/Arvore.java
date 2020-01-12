package Modelo;

import java.util.Comparator;

import Modelo.No;

public class Arvore<T extends Comparable<T>> {
	private No<T> raiz;
	private Comparator<T> comparador;
	
	public Arvore() {
		raiz = null;		
	}

	
	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public No getRaiz() {
		return raiz;
	}

	public boolean vazia() {
		return raiz == null;
	}

	/**
	 * Determina e retorna a altura (int) da árvore a partir de um determinado nó
	 */
	private static int altura(No n) {
		if (n == null) // Se não existe árvore neste ponto, retorna -1
			return -1;

		if (n.getEsquerda() == null && n.getDireita() == null) // Se não há filhos a esquerda e a direita, retorna nulo
			return 0;

		else if (n.getEsquerda() == null)
			return 1 + altura(n.getDireita()); // Se não há filho à esquerda retorna-se +1 a altura e verifica-se o
												// filho a direita

		else if (n.getDireita() == null)
			return 1 + altura(n.getEsquerda()); // Se não há filho à direita retorna-se +1 a altura e verifica-se o
												// filho a esquerda

		else
			// Caso para quando quer ter-se a altura de toda a árvore, buscando qual o maior
			// caminho entre esquerda e direita
			return 1 + Math.max(altura(n.getEsquerda()), altura(n.getDireita()));
	}

	private int fatorBalanceamento(No n) {
		if (n != null)
			return altura(n.getEsquerda()) - altura(n.getDireita());
		else
			return 0;
	}

	public No balancear(No n) {
		if (fatorBalanceamento(n) == 2) {
			if (fatorBalanceamento(n.getEsquerda()) > 0) {
				System.out.println("Rotação simples para direita");
				n = rotacaoDireita(n);
			} else {
				System.out.println("Rotação dupla para a direita");
				n = rotacaoDireita2(n);
			}
		} else if (fatorBalanceamento(n) == -2) {
			if (fatorBalanceamento(n.getDireita()) < 0) {
				System.out.println("Rotação simples para a esquerda");
				n = rotacaoEsquerda(n);
			} else {
				System.out.println("Rotação dupla para a esquerda");
				n = rotacaoEsquerda2(n);
			}
		}
		n.setAltura(Math.max(altura(n.getEsquerda()), altura(n.getDireita())) + 1);
		return n;
	}

	private static No rotacaoDireita(No n) {
		No aux = n.getEsquerda();
		No help = aux.getDireita();

		aux.setDireita(n);
		n.setEsquerda(help);

		n.setAltura(Math.max(altura(n.getEsquerda()), altura(n.getDireita())) + 1);
		aux.setAltura(Math.max(altura(aux.getEsquerda()), n.getAltura()) + 1);

		return aux;
	}

	private static No rotacaoEsquerda(No n) {
		No aux = n.getDireita();
		No help = aux.getEsquerda();

		aux.setEsquerda(n);
		n.setDireita(help);

		n.setAltura(Math.max(altura(n.getEsquerda()), altura(n.getDireita())) + 1);
		aux.setAltura(Math.max(altura(aux.getDireita()), n.getAltura()) + 1);

		return aux;
	}

	private static No rotacaoDireita2(No n) {
		n.setEsquerda(rotacaoEsquerda(n.getEsquerda()));
		return rotacaoDireita(n);
	}

	private static No rotacaoEsquerda2(No n) {
		if (n != null)
			n.setDireita(rotacaoDireita(n.getDireita()));
		return rotacaoEsquerda(n);
	}

	public boolean inserir(T objeto) {
		raiz = inserir(objeto, raiz);
		return true;
	}

	private No inserir(T objeto, No<T> no) {
		if (no == null)
			no = new No(objeto, comparador);
		else if (no.compareTo(objeto) == 0)
			System.out.println("Código já utilizado");
		else if (no.compareTo(objeto) > 0)
			no.setEsquerda(inserir(objeto, no.getEsquerda()));
		else if (no.compareTo(objeto) < 0)
			no.setDireita(inserir(objeto, no.getDireita()));

		no = balancear(no);
		return no;
	}

	/**public boolean inserir(int codigo, T objeto) {
		raiz = inserir(codigo, objeto, raiz);
		return true;
	}

	public No inserir(int codigo, Object objeto, No<T> n) {
		if (n == null)
			n = new No(Integer.toString(codigo), objeto);
		else if (codigo == Integer.parseInt(n.getCodigo()))
			System.out.println(codigo + "Código de livro já utilizado!");
		else if (codigo < Integer.parseInt(n.getCodigo()))
			n.setEsquerda(inserir(codigo, objeto, n.getEsquerda()));
		else if (codigo > Integer.parseInt(n.getCodigo()))
			n.setDireita(inserir(codigo, objeto, n.getDireita()));

		n = balancear(n);
		return n;
	}*/

	public No<T> buscarMaior(){
		return buscarMaior(raiz);
	}
	
	private No<T> buscarMaior(No<T> n) {
		while (n.getDireita() != null) {
			n = n.getDireita();
		}
		System.out.println("Maior elemento é: " + n.getValor());
		return n;
	}

	public No<T> buscarMenor(){
		return buscarMenor(raiz);
	}
	
	private No<T> buscarMenor(No<T> n) {
		while (n.getEsquerda() != null) {
			n = n.getEsquerda();
		}
		System.out.println("Menor elemento é: " + n.getValor());
		return n;
	}


	public No remover(T codigo) {
		raiz = remover(codigo, raiz);
		return raiz;
	}

	private No remover(T codigo, No<T> no) {
		if (no == null) {
			System.out.println("Elemento não encontrado para remoção!");
			return null;
		}
		
		System.out.println("Percorrendo No" + no.getObjeto());
		if (no.compareTo(codigo) < 0) {
			no.setEsquerda(remover(codigo, no.getEsquerda()));
			return balancear(no);
		} else if (no.compareTo(codigo) > 0) {
			no.setDireita(remover(codigo, no.getDireita()));
			return balancear(no);
		} else {
			if (no.getEsquerda() == null && no.getDireita() == null) {
				return null;
			}
			else if (no.getEsquerda() == null) {
				return no.getDireita();
			}
			else if (no.getDireita() == null) {
				return no.getEsquerda();
			}

			// DOIS FILHOS
			else if (no.getEsquerda() != null && no.getDireita() != null) {
				no.setValor(buscarMaior(no.getDireita()).getObjeto());
				no.setDireita(remover(no.getObjeto(), no.getDireita()));
			} else {
				if (no.getEsquerda() != null)
					no = no.getEsquerda();
				else
					no = no.getDireita();
			}
		}
		return balancear(no);
	}

	public No buscar(T codigo) {
		return buscar(raiz, codigo);
	}

	private No buscar(No no, T codigo) {
		while (no != null) {
			if (no.compareTo(codigo) == 0)
				return no;
			else if (no.compareTo(codigo) < 0)
				no = no.getEsquerda();
			else
				no = no.getDireita();
		}
		return null;
	}

	public void emOrdem() {
		emOrdem(raiz);
	}

	private void emOrdem(No<T> no) {
		if (no != null) {
			Livro l = (Livro) no.getObjeto();
			System.out.println("[" + l.getN_ebook() + "]" + " " + l.getTitulo());
		}

		if (no.getEsquerda() != null)
			emOrdem(no.getEsquerda());

		if (no.getDireita() != null)
			emOrdem(no.getDireita());
	}

}
