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
	Arvore<Livro> livros = new Arvore<Livro>();
	Arvore<Autor> autores = new Arvore<Autor>();
	Arvore<Ano> anos = new Arvore<Ano>();
	File arquivo = new File("base.csv");
	boolean existe = arquivo.exists();
	Autor autor;
	Ano ano;
	Livro livro;

	/** Permite a inserção de um novo livro na árvore de livros
	 *  
	 * @param codigo
	 * @param titulo
	 * @param author
	 * @param mes
	 * @param year
	 * @param link
	 * @return True ou False, a depnder do resultado da operacao
	 */
	public boolean cadastrarLivro(int codigo, String titulo, String author, char[] mes, int year, String link) {
		livro = new Livro(codigo, titulo, author, mes, year, link);

		livros.inserir(codigo, livro); // Insere o livro na arvore de livros
		
		// Insere o livro nos livros do autor indicado
		No n = autores.buscar(livro.getAutor().getNome());
		if (n != null) {
			autor = (Autor) n.getObjeto();
			autor.adicionarLivro(livro);
		} else {
			// Se o autor nao existir, ele e criado e o livro entao inserido
			autor = new Autor(livro.getAutor().getNome());
			autor.adicionarLivro(livro);
			autores.inserir(autor.getNome(), autor);
		}
		
		// Insere o livro nos livros do ano indicado
		n = anos.buscar(livro.getAno());
		if (n != null) {
			ano = (Ano) n.getObjeto();
			ano.adicionarLivro(livro);
		} else {
			// Se o ano nao existir ele e criado e o livro adicionado ao ano
			ano = new Ano(livro.getAno());
			ano.adicionarLivro(livro);
			anos.inserir(livro.getAno(), ano);
		}
		return true;
	}

	/**
	 * Acrescenta a arvore de livros os livros existentes em um arquivo de texto
	 * @param endereco
	 * @return verdadeiro ou falso a depender da realizacao da operacao
	 * @throws IOException
	 */
	public boolean carregarBase(String endereco) throws IOException {
		File base = new File(endereco);
		if (base.exists()) {
			FileReader leitor = new FileReader(base);
			BufferedReader buffer = new BufferedReader(leitor);
			
			while (buffer.ready()) {
				String[] dados = buffer.readLine().split(";");
				System.out.println(dados[0]);
				try {
				Livro livro = new Livro(Integer.parseInt(dados[0]), dados[1], dados[2], (dados[3]).toCharArray(),
						Integer.parseInt(dados[4]), dados[5]);
				livros.inserir(livro.getN_ebook(), livro);
				No n = autores.buscar(livro.getAutor().getNome());
				if (n != null) {
					autor = (Autor) n.getObjeto();
					autor.adicionarLivro(livro);						
				} else {
					autor = new Autor(livro.getAutor().getNome());
					autor.adicionarLivro(livro);
					autores.inserir(autor.getNome(), autor);
				}
				
				n = anos.buscar(livro.getAno());
				if (n != null) {
					ano = (Ano) n.getObjeto();
					ano.adicionarLivro(livro);
				} else {
					ano = new Ano(livro.getAno());
					ano.adicionarLivro(livro);
					anos.inserir(livro.getAno(), ano);
				}
				
				} catch(NumberFormatException ex){
					System.out.println("Dados Inválidos");
				}
				
			}
		}
		return true;
	}

	public boolean gravarLivros() throws IOException {
		gravarLivros(livros.getRaiz());
		return true;
	}

	private void gravarLivros(No n) throws IOException {
		if (n != null) {
			Livro livro = (Livro) n.getObjeto();
			String livroTexto = livro.getN_ebook() + ";" + livro.getTitulo() + ";" + livro.getAutor().getNome() + ";"
					+ String.copyValueOf(livro.getMes()) + ";" + livro.getAno() + ";" + livro.getLink();
			if (existe) {
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

	public void listarAutoresQtde() {
		if (autores.getRaiz() != null)
			listarAutoresQtde(autores.getRaiz());
		else
			System.out.println("Base vazia");
	}

	private void listarAutoresQtde(No n) {
		if (n != null) {
			autor = (Autor) n.getObjeto();
			System.out.println(autor.getNome() + " (" + autor.getQuantidade() + ")");
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
		No n = livros.buscar(String.valueOf(codigo));
		if (n != null)
			return (Livro)n.getObjeto();
		else
			return null;
	}
		
	public void exibirLivroAno(int codigo) {
		No n = anos.buscar(codigo);
		if (n != null) {
			ano = (Ano)n.getObjeto();
			System.out.println("\n\nLivros [" + ano.getAno() + "]");
			ano.exibirLivros();
		} else {
			System.out.println("Não há livros no ano informado!");
		}				
	}
	
	public void excluirLivro(int codigo) {
		livros.remover(String.valueOf(codigo));
	}

	public Arvore<Livro> baseLivros() throws IOException {
		Arvore base = new Arvore();
		if (existe) {
			FileReader leitor = new FileReader(arquivo);
			BufferedReader buffer = new BufferedReader(leitor);
			while (buffer.ready()) {
				String[] dados = buffer.readLine().split(";");
				System.out.println(dados[0]);
				Livro livro = new Livro(Integer.parseInt(dados[0]), dados[1], dados[2], (dados[3]).toCharArray(),
						Integer.parseInt(dados[4]), dados[5]);
				base.inserir(livro.getN_ebook(), livro);
			}
		}

		return base;
	}

}