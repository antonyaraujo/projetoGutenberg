package Controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Ano;
import Modelo.Arvore;
import Modelo.Autor;
import Modelo.Livro;
import Modelo.No;

public class SistemaCatalogacao {
	private	Arvore<Livro> livros = new Arvore<Livro>();
	private Arvore<Autor> autores = new Arvore<Autor>();
	private	Arvore<Ano> anos = new Arvore<Ano>();
	private File arquivo = new File("base.csv");
	Autor autor;
	Ano ano;
	Livro livro;
	int qt = 0;
	public int getQt() {
		return qt;
	}

	/**
	 * Permite a inserção de um novo livro na árvore de livros
	 * 
	 * @param codigo
	 * @param titulo
	 * @param author
	 * @param mes
	 * @param year
	 * @param link
	 * @return True ou False, a depender do resultado da operacao
	 */
	public boolean cadastrarLivro(int codigo, String titulo, String author, char[] mes, int year, String link) {
		livro = new Livro(codigo, titulo, author, mes, year, link);

		livros.inserir(codigo, livro); // Insere o livro na arvore de livros
		qt++;
		// Insere o livro nos livros do autor indicado
		No<Autor> noAutor = autores.buscar(livro.getAutor().getNome());
		if (noAutor != null) {
			autor = noAutor.getObjeto();
			autor.adicionarLivro(livro);
		} else {
			// Se o autor nao existir, ele e criado e o livro entao inserido
			autor = new Autor(livro.getAutor().getNome());
			autor.adicionarLivro(livro);
			autores.inserir(autor.getNome(), autor); // insere o autor na arvore dos autores
		}

		// Insere o livro nos livros do ano indicado
		No<Ano> noAno = anos.buscar(livro.getAno());
		if (noAno != null) {
			ano = noAno.getObjeto();
			ano.adicionarLivro(livro);
		} else {
			// Se o ano nao existir ele e criado e o livro adicionado ao ano
			ano = new Ano(livro.getAno());
			ano.adicionarLivro(livro);
			anos.inserir(livro.getAno(), ano); // Insere o ano na arvore dos anos
		}
		return true;
	}

	/**
	 * Acrescenta a arvore de livros os livros existentes em um arquivo de texto	 *
	 * @param endereco
	 * @return verdadeiro ou falso a depender da realizacao da operacao
	 * @throws IOException
	 */
	public boolean carregarBase(String endereco) throws IOException {
		arquivo = new File(endereco);
		if (arquivo.exists()) {
			FileReader leitor = new FileReader(arquivo);
			BufferedReader buffer = new BufferedReader(leitor);
			int c = 0;
			while (buffer.ready()) {
				c++;
				String[] dados = buffer.readLine().split(";");
				try {
					livro = new Livro(Integer.parseInt(dados[0]), dados[1], dados[2], (dados[3]).toCharArray(),
							Integer.parseInt(dados[4]), dados[5]);
					livros.inserir(livro.getN_ebook(), livro);

					// Insere o livro nos livros do autor indicado
					No<Autor> noAutor = autores.buscar(livro.getAutor().getNome());
					if (noAutor != null) {
						autor = noAutor.getObjeto();
						autor.adicionarLivro(livro);
					} else {
						// Se o autor nao existir, ele e criado e o livro entao inserido
						autor = new Autor(livro.getAutor().getNome());
						autor.adicionarLivro(livro);
						autores.inserir(autor.getNome(), autor); // insere o autor na arvore dos autores
					}

					// Insere o livro nos livros do ano indicado
					No<Ano> noAno = anos.buscar(livro.getAno());
					if (noAno != null) {
						ano = noAno.getObjeto();
						ano.adicionarLivro(livro);
					} else {
						// Se o ano nao existir ele e criado e o livro adicionado ao ano
						ano = new Ano(livro.getAno());
						ano.adicionarLivro(livro);
						anos.inserir(livro.getAno(), ano); // Insere o ano na arvore dos anos
					}
					qt++;
				} catch (NumberFormatException ex) {
					System.out.println("Dados Inválidos na linha " + c);
				}

			}
		}
		return true;
	}

	/**
	 * Permite a chamada do metodo recusivo para escrever as informacoes dos livros no arquivo
	 * @param void
	 * @return True 
	 * */
	public boolean gravarLivros() throws IOException {
		gravarLivros(livros.getRaiz());
		return true;
	}

	/**
	 * Metodo recursivo que permite a escrita de um livro em uma linha do arquivo
	 * @return void
	 * */
	private void gravarLivros(No n) throws IOException {
		if (n != null) {
			Livro livro = (Livro) n.getObjeto();
			String livroTexto = livro.getN_ebook() + ";" + livro.getTitulo() + ";" + livro.getAutor().getNome() + ";"
					+ String.copyValueOf(livro.getMes()) + ";" + livro.getAno() + ";" + livro.getLink();
			if (arquivo.exists()) {
				if (buscarLivro(livro.getN_ebook()) == null) {
					FileWriter escritor = new FileWriter(arquivo, true);
					BufferedWriter buffer = new BufferedWriter(escritor);
					buffer.write(livroTexto);
					buffer.newLine();
					buffer.close();
					escritor.close();
				}
			}
			if (n.getEsquerda() != null) {
				gravarLivros(n.getEsquerda());
			}
			if (n.getDireita() != null) {
				gravarLivros(n.getDireita());
			}
		}
	}

	public boolean listarAutoresQtde() {
		if (autores.getRaiz() != null) {
			System.out.println("Autor [Quantidade]");
			listarAutoresQtde(autores.getRaiz());
			return true;
		}
		else
			return false;
	}

	private void listarAutoresQtde(No n) {
		if (n != null) {
			autor = (Autor) n.getObjeto();
			if (autor.getQuantidade() > 1)
				System.out.println(autor.getNome() + " [" + autor.getQuantidade() + " livros]");
			else
				System.out.println(autor.getNome() + " [" + autor.getQuantidade() + " livro]");
		}
		if (n.getEsquerda() != null)
			listarAutoresQtde(n.getEsquerda());

		if (n.getDireita() != null)
			listarAutoresQtde(n.getDireita());

	}

	public void exibirLivroAutor() {
		exibirLivroAutor(autores.getRaiz());
	}

	private void exibirLivroAutor(No n) {
		if (n != null) {
			autor = (Autor) n.getObjeto();
			System.out.println("\nLivros do Autor: " + autor.getNome());
			autor.exibirLivros();
		}

		if (n.getEsquerda() != null)
			exibirLivroAutor(n.getEsquerda());

		if (n.getDireita() != null)
			exibirLivroAutor(n.getDireita());

	}

	public void exibirLivros() {
		livros.emOrdem();
	}

	public Livro buscarLivro(int codigo) {
		No n = livros.buscar(codigo);
		if (n != null)
			return (Livro) n.getObjeto();
		else
			return null;
	}

	public void exibirLivroAno(int codigo) {
		No n = anos.buscar(codigo);
		if (n != null) {
			ano = (Ano) n.getObjeto();
			System.out.println("\n\nLivros [" + ano.getAno() + "]");
			ano.exibirLivros();
		} else {
			System.out.println("Não há livros no ano informado!");
		}
	}

	public void excluirLivro(int codigo) {
		livros.remover(String.valueOf(codigo));
	}

	public void verificar() {
		verificar(livros.getRaiz());
	}
	
	private No verificar(No n) {
		System.out.println("\nNo: " + n.getCodigo());
		if (n.getEsquerda() != null)
			System.out.println("Esquerda: " + n.getEsquerda().getCodigo());

		if (n.getDireita() != null) 
			System.out.println("Direita: " + n.getDireita().getCodigo());

		if (n.getEsquerda() != null)
		verificar(n.getEsquerda());
		
		if (n.getDireita() != null)
		verificar(n.getDireita());
		
		return n;
	}

}