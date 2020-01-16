package Modelo;

import java.util.Comparator;

import Modelo.No;

public class Arvore<T extends Comparable<T>> {
	private No<T> raiz;
	private Comparator<T> comparador;
	
	/**
	 * Metodo construtor da arvore que inicializa a raiz
	 * */
	public Arvore() {
		raiz = null;		
	}

	/**
	 * Permite a alteracao do do valor (No) que esta na raiz
	 * @param raiz- No que representa o novo valor (No) a ser atribuido a variavel raiz
	 * @return void - não há retorno 
	 */
	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	/**
	 * Metodo que retorna o No que esta contido na variavel raiz
	 * @param raiz - guarda o No que e a raiz ou o No raiz
	 */
	public No getRaiz() {
		return raiz;
	}

	/**
	 * Metodo que indica se a arvore esta vazia ou nao
	 * @return verdadeiro - se existir raiz
	 * @return false - se a raiz estiver vazia ou nao existir raiz
	 */
	public boolean vazia() {
		return raiz == null;
	}

	/**
	 * Determina e retorna a altura (int) da árvore a partir de um determinado no
	 * @param no - recebe o no a partir do qual a altura da arvore sera calculada
	 * @return -1 se o no for nulo
	 * @return a altura do no se o no existir
	 */
	private static int altura(No no) {
		if (no == null) // Se não existe no, neste ponto, retorna -1
			return -1;
		else
			return no.getAltura(); // Se o no existe, retorna-se a altura do no
	}

	/**
	 * Metodo que calcula e retorna o valor do fator de balanceamento (int)
	 * @param No - recebe o no a partir do qual o fator e calculado
	 * @return retorna o valor da subtracao do no que esta a esquerda pelo no que esta a direita do no recebido como parametro
	 * */
	private int fatorBalanceamento(No n) {		
			return altura(n.getEsquerda()) - altura(n.getDireita());		
	}

	/**
	 * @param no - recebe o no a partir do qual a arvore sera balanceada
	 * @return no - retorna o n com a atualizacao do seu balanceamento
	 * */
	public No balancear(No n) {
		if (fatorBalanceamento(n) == 2) // Significa que ha mais nos na esquerda da arvore
			{
			if (fatorBalanceamento(n.getEsquerda()) > 0)  // Se o fator de balanceamento do no a esquerda for maior que 0 (1 ou 2) entao rotaciona-se a direita
				{
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

	/**
	 * Metodo que realiza a rotacao direita da arvore
	 * @param no - recebe o no a partir do qual a rotacao sera realizada
	 * @return aux - retorna o no que devera assumir o lugar do no que foi rotacionado
	 * */
	private static No rotacaoDireita(No no) {
		No aux = no.getEsquerda();		
		no.setEsquerda(aux.getDireita());
		aux.setDireita(no);		

		no.setAltura(Math.max(altura(no.getEsquerda()), altura(no.getDireita())) + 1);
		aux.setAltura(Math.max(altura(aux.getEsquerda()), no.getAltura()) + 1);

		return aux;
	}

	/**
	 * Metodo que realiza a rotacao esquerda da arvore
	 * @param no - recebe o no a partir do qual a rotacao sera realizada
	 * @return aux - retorna o no que devera assumir o lugar do no que foi rotacionado
	 * */
	private static No rotacaoEsquerda(No n) {
		No aux = n.getDireita();		
		n.setDireita(aux.getEsquerda());
		aux.setEsquerda(n);		

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

	/**
	 * Metodo publico que serve para chamada do metodo recursivo de mesmo nome	  
	 * @return no - retorna a nova raiz, a depender do balanceamento, ou a mesma raiz
	 */
	public boolean inserir(T objeto) {
		raiz = inserir(objeto, raiz);
		return true;
	}

	/**
	 * Metodo recursivo que insere um objeto em um no e insere esse mesmo no na arvore, caso o objeto ainda nao esteja em nenhum outro no
	 * @param objeto - objeto que pretende-se inserir na arvore
	 * @param no - no que recebe todos os nos da arvore para verificacao de armazenando do objeto
	 * @return no - retorna a raiz da arvore
	 * */
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

	/**
	 * Metodo publico que serve para chamada do metodo recursivo de mesmo nome	  
	 * @return no - retorna o no de maior valor no identificador sendo buscado a partir da raiz
	 */
	public No<T> buscarMaior(){
		return buscarMaior(raiz);
	}
	
	/**
	 * Metodo recursivo que busca e retorna o no de maior valor de identificador na arvore
	 * @param no - o a partir do qual o maior no sera buscado indo sempre a direita onde estao os valores menores
	 * @return no - o maior no de toda a arvore
	 * */
	private No<T> buscarMaior(No<T> n) {
		while (n.getDireita() != null) {
			n = n.getDireita();
		}
		System.out.println("Maior elemento é: " + n.getObjeto().toString());
		return n;
	}

	/**
	 * Metodo publico que serve para chamada do metodo recursivo de mesmo nome	  
	 * @return no - retorna o no de menor valor no identificador sendo buscado a partir da raiz
	 */
	public No<T> buscarMenor(){
		return buscarMenor(raiz);
	}
	
	/**
	 * Metodo recursivo que busca e retorna o no de menor valor de identificador na arvore
	 * @param no - o a partir do qual o menor no sera buscado indo sempre a esquerda onde estao os valores menores
	 * @return no - o menor no de toda a arvore
	 */
	private No<T> buscarMenor(No<T> no) {
		while (no.getEsquerda() != null) {
			no = no.getEsquerda();
		}
		System.out.println("Menor elemento é: " + no.getObjeto().toString());
		return no;
	}

	/**
	 * Metodo publico que serve para chamada do metodo recursivo de mesmo nome
	 * @param objeto - recebe um objeto que contem apenas um identificador para que se realize a remocao atraves deste
	 * @return no - retorna o no que sera a nova ou a mesma raiz que anterior a depender da remocao e balanceamento
	 * */
	public No remover(T objeto) {
		raiz = remover(objeto, raiz);
		return raiz;
	}

	/**
	 * Metodo recursivo que permite a remocao de um no que contem um objeto de mesmo identificador de um outro objeto
	 * @param objeto - recebe um objeto contendo apenas um identificador
	 * @param no - recebe um no que tera o identificador do seu objeto comparado
	 * @return null - caso percorra a arvore inteira e nao encontre no com objeto de mesmo identificador
	 * @return no - retorna a nova ou mesma raiz da arvore a depender do balanceamento e remocao
	 * */
	private No remover(T objeto, No<T> no) {
		if (no == null) {
			System.out.println("Elemento não encontrado para remoção!");
			return null;
		}
			
		System.out.println("Percorrendo No" + no.getObjeto().toString());
		if (no.compareTo(objeto) == 0) // Verifica se o no contem o objeto de identificador igual ao que deseja-se excluir 
		{
			System.out.println("Encontradoow!");
			if(no.getEsquerda() == null && no.getDireita() == null) // Verifica-se se o no nao possui filhos
				return null;			
			else if(no.getEsquerda() == null) // Caso de remocao em que o no nao possui filho a esquerda
				return no.getDireita();			
			else if(no.getDireita() == null) // Caso de remocao em que o no nao possui filho a direita 
				return no.getEsquerda();			
			else if(no.getEsquerda() != null && no.getDireita() != null) // caso de remocao em que o no possui filhos a esquerda e a direita
				{
				no.setObjeto(buscarMaior(no.getEsquerda()).getObjeto());
				no.setEsquerda(remover(no.getObjeto(), no.getEsquerda()));
			}
			else 
				if (no.getEsquerda() != null) // Caso em que o no possui filho a esquerda
					no = no.getEsquerda();
				else // Caso em que o no possui filho a direita
					no = no.getDireita(); 
			return balancear(no);
							
			}
		else if(no.compareTo(objeto) > 0) { // Se o identificador do objeto for menor que o identificador do identificador do objeto do no atual move-se a busca de remocao para o no a esquerda
			no.setEsquerda(remover(objeto, no.getEsquerda()));
			return balancear(no);
		} else { // do contrario, move-se a direita
			no.setDireita(remover(objeto, no.getDireita()));
			return balancear(no);
		}		
	}

	/**
	 * Metodo publico que serve para chamada do metodo recursivo de mesmo nome
	 * @param objeto - recebe um objeto que contem apenas um identificador para que se realize a busca atraves deste
	 * @return no - retorna o no que contem o objeto de mesmo identificador do objeto passado como parametro 
	 * */
	public No buscar(T objeto) {
		return buscar(raiz, objeto);
	}

	/**
	 * Metodo recursivo que permite encontrar um elemento existente na arvore
	 * @param no<tipogenerico> - no atual na comparacao da busca
	 * @param objeto - Objeto que simula elemento que quer ser encontrado
	 * @return no - se o objeto do no possuir identificador igual ao identificador do objeto passado como parametro retorna-se o tal no
	 * @return null - se nao for encontrado nenhum no que contenha um objeto de identificador semelhante 
	 * */
	private No buscar(No<T> no, T objeto) {
		while (no != null) {
			if (no.compareTo(objeto) == 0)
				return no;
			else if (no.compareTo(objeto) > 0)
				no = no.getEsquerda();
			else
				no = no.getDireita();
		}
		return null;
	}

	/**
	 * Metodo publico que serve para chamada do metodo recursivo de mesmo nome
	 * @return void - nao ha retorno
	 * */
	public void emOrdem() {
		emOrdem(raiz);
	}

	/**
	 * Metodo recursivo que percorre e exibe os nos da arvore em ordem crescente do menor valor ao maior
	 * @param no - recebe um No com um tipo especifico a partir do qual sera exibido primeiramente todos os menores posteriormente todos os maiores
	 * @return void - nao ha retorno 
	 * */
	private void emOrdem(No<T> no) {
		
		if (no.getEsquerda() != null)
			emOrdem(no.getEsquerda());
		
		if (no != null) {
			Livro l = (Livro) no.getObjeto();
			System.out.println("[" + l.getN_ebook() + "]" + " " + l.getTitulo());
		}	

		if (no.getDireita() != null)
			emOrdem(no.getDireita());
	}

}