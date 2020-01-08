package Modelo;

import Modelo.No;

public class Arvore<T> {
	private No raiz;

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

	public boolean inserir(String codigo, Object objeto) {
		raiz = inserir(codigo, objeto, raiz);
		return true;
	}

	private No inserir(String codigo, Object objeto, No n) {
		if (n == null)
			n = new No(codigo, objeto);
		else if (codigo.equalsIgnoreCase(n.getCodigo()))
			System.out.println("Código já utilizado");
		else if (codigo.compareToIgnoreCase(n.getCodigo()) < 0)
			n.setEsquerda(inserir(codigo, objeto, n.getEsquerda()));
		else if (codigo.compareToIgnoreCase(n.getCodigo()) > 0)
			n.setDireita(inserir(codigo, objeto, n.getDireita()));

		n = balancear(n);
		return n;
	}

	public boolean inserir(int codigo, Object objeto) {
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
	}

	// Encontra o no de menor código a direita do nó atual
	private No menorDireita(No n) {
		if (n.getEsquerda() == null) {
			return n;
		} else {
			n = n.getEsquerda();
			return menorDireita(n);
		}
	}

	private String buscarMaior(No n) {
		while (n.getDireita() != null) {
			n = n.getDireita();
		}
		System.out.println("Mairo elemento é: " + n.getValor());
		return n.getCodigo();
	}

	private String buscarMenor(No n) {
		while (n.getEsquerda() != null) {
			n = n.getEsquerda();
		}
		System.out.println("Menor elemento é: " + n.getValor());
		return n.getCodigo();
	}

	public No remover(String codigo) {
		raiz = remover(codigo, raiz);
		return raiz;
	}

	private No remover(String codigo, No n) {
		if (n == null) {
			System.out.println("Elemento não encontrado para remoção!");
			return null;
		}
		System.out.println("Percorrendo No" + n.getCodigo());
		if (codigo.compareToIgnoreCase(n.getCodigo()) < 0) {
			n.setEsquerda(remover(codigo, n.getEsquerda()));
			return balancear(n);
		} else if (codigo.compareToIgnoreCase(n.getCodigo()) > 0) {
			n.setDireita(remover(codigo, n.getDireita()));
			return balancear(n);
		} else {
			if (n.getEsquerda() == null && n.getDireita() == null) {
				return null;
			}
			if (n.getEsquerda() == null) {
				return n.getDireita();
			}

			if (n.getDireita() == null) {
				return n.getEsquerda();
			}

			// DOIS FILHOS
			else if (n.getEsquerda() != null && n.getDireita() != null) {
				n.setValor(menorDireita(n.getDireita()).getCodigo());
				n.setDireita(remover(n.getCodigo(), n.getDireita()));
			} else {
				if (n.getEsquerda() != null)
					n = n.getEsquerda();
				else
					n = n.getDireita();
			}
		}
		return balancear(n);
	}

	public No remover(int codigo) {
		raiz = remover(codigo, raiz);
		return raiz;
	}

	private No remover(int codigo, No n) {
		if (n == null) {
			System.out.println("Elemento não encontrado para remoção!");
			return null;
		}

		int codigoNo = Integer.parseInt(n.getCodigo());
		System.out.println("Percorrendo No" + n.getCodigo());
		if (codigo < codigoNo) {
			n.setEsquerda(remover(codigo, n.getEsquerda()));
			return balancear(n);
		} else if (codigo > codigoNo) {
			n.setDireita(remover(codigo, n.getDireita()));
			return balancear(n);
		} else {
			if (n.getEsquerda() == null && n.getDireita() == null) {
				return null;
			}
			if (n.getEsquerda() == null) {
				return n.getDireita();
			}

			if (n.getDireita() == null) {
				return n.getEsquerda();
			}

			// DOIS FILHOS
			else if (n.getEsquerda() != null && n.getDireita() != null) {
				n.setValor(menorDireita(n.getDireita()).getCodigo());
				n.setDireita(remover(n.getCodigo(), n.getDireita()));
			} else {
				if (n.getEsquerda() != null)
					n = n.getEsquerda();
				else
					n = n.getDireita();
			}
		}
		return balancear(n);
	}

	public No buscar(int codigo) {
		return buscar(raiz, codigo);
	}

	private No buscar(No n, int codigo) {
		while (n != null) {
			if (codigo == Integer.parseInt(n.getCodigo()))
				return n;
			else if (codigo < Integer.parseInt(n.getCodigo()))
				n = n.getEsquerda();
			else
				n = n.getDireita();
		}
		return null;
	}

	public No buscar(String codigo) {
		return buscar(raiz, codigo);
	}

	private No buscar(No n, String codigo) {
		while (n != null) {
			if (codigo.equalsIgnoreCase(n.getCodigo()))
				return n;
			else if (codigo.compareToIgnoreCase(n.getCodigo()) < 0)
				n = n.getEsquerda();
			else if (codigo.compareToIgnoreCase(n.getCodigo()) > 0)
				n = n.getDireita();
		}
		return null;
	}

	public void emOrdem() {
		emOrdem(raiz);
	}

	private void emOrdem(No n) {
		if (n != null) {
			Livro l = (Livro) n.getObjeto();
			System.out.println("[" + n.getCodigo() + "]" + " " + l.getTitulo());
		}

		if (n.getEsquerda() != null)
			emOrdem(n.getEsquerda());

		if (n.getDireita() != null)
			emOrdem(n.getDireita());
	}

}
