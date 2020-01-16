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
	private Arvore<Livro> livros = new Arvore<Livro>();
	private Arvore<Autor> autores = new Arvore<Autor>();
	private Arvore<Ano> anos = new Arvore<Ano>();
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

		livros.inserir(livro); // Insere o livro na arvore de livros
		qt++;
		// Insere o livro nos livros do autor indicado
		No<Autor> noAutor = autores.buscar(livro.getAutor());
		if (noAutor != null) {
			autor = noAutor.getObjeto();
			autor.adicionarLivro(livro);
		} else {
			// Se o autor nao existir, ele e criado e o livro entao inserido
			autor = new Autor(livro.getAutor().getNome());
			autor.adicionarLivro(livro);
			autores.inserir(autor); // insere o autor na arvore dos autores
		}

		// Insere o livro nos livros do ano indicado
		No<Ano> noAno = anos.buscar(livro.getAno());
		if (noAno != null) {
			ano = noAno.getObjeto();
			ano.adicionarLivro(livro);
		} else {
			// Se o ano nao existir ele e criado e o livro adicionado ao ano
			ano = new Ano(livro.getAno().getAno());
			ano.adicionarLivro(livro);
			anos.inserir(livro.getAno()); // Insere o ano na arvore dos anos
		}
		return true;
	}

	/**
	 * Acrescenta a arvore de livros os livros existentes em um arquivo de texto *
	 * 
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
					livros.inserir(livro);

					// Insere o livro nos livros do autor indicado
					No<Autor> noAutor = autores.buscar(livro.getAutor());
					if (noAutor != null) {
						autor = noAutor.getObjeto();
						autor.adicionarLivro(livro);
					} else {
						// Se o autor nao existir, ele e criado e o livro entao inserido
						autor = livro.getAutor();
						autor.adicionarLivro(livro);
						autores.inserir(autor); // insere o autor na arvore dos autores
					}

					// Insere o livro nos livros do ano indicado
					No<Ano> noAno = anos.buscar(livro.getAno());
					if (noAno != null) {
						ano = noAno.getObjeto();
						ano.adicionarLivro(livro);
					} else {
						// Se o ano nao existir ele e criado e o livro adicionado ao ano
						ano = (livro.getAno());
						ano.adicionarLivro(livro);
						anos.inserir(ano); // Insere o ano na arvore dos anos
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
	 * Permite a chamada do metodo recusivo para escrever as informacoes dos livros
	 * no arquivo 
	 * @return true - representa o sucesso da operacao
	 */
	public boolean gravarLivros() throws IOException {
		gravarLivros(livros.getRaiz());
		return true;
	}

	/**
	 * Metodo recursivo que permite a escrita de um livro em uma linha do arquivo
	 * @param n - recebe cada no existente da arvore
	 * @return void
	 */
	private void gravarLivros(No n) throws IOException {
		if (n != null) {
			Livro livro = (Livro) n.getObjeto();
			String livroTexto = livro.getN_ebook() + ";" + livro.getTitulo() + ";" + livro.getAutor().getNome() + ";"
					+ String.copyValueOf(livro.getMes()) + ";" + livro.getAno() + ";" + livro.getLink();
			if (arquivo.exists()) {
				if (buscarLivro(livro.getN_ebook()) == null) {
					FileWriter escritor = new FileWriter(arquivo);
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

	/**
	 * Metodo publico que serve para chamada do metodo recursivo de mesmo nome
	 * @return true - se a operacao de listagem for realizada com sucesso
	 * @return false - se ocorrer erro na listagem 
	 */
	public boolean listarAutoresQtde() {
		if (autores.getRaiz() != null) {
			System.out.println("Autor [Quantidade]");
			listarAutoresQtde(autores.getRaiz());
			return true;
		} else
			return false;
	}

	/**
	 * Metodo recursivo que permite a exibicao do nome do autor e a quantidade de livros que este possui
	 * @param n - representa um no da arvore que sera percorrida
	 * */
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

	/**
	 * Metodo que permite a exibicao dos livros de um dado autor e o armazenamento destes em um arquivo com o nome do autor+.csv
	 * @param nome - recebe uma String que representa o nome do Autor de referencia a ser buscado
	 * @return true - se todas as operacoes forem realizadas com sucesso
	 * @return false - caso ocorra algum erro nas operacoes de busca e escrita
	 * @throws IOException -  coleta erro de excecao do sistema para lidar com arquivos
	 */
	public boolean exibirLivroAutor(String nome) throws IOException {
		autor = new Autor(nome);
		No<Autor> no = autores.buscar(autor);
		if (no != null) {
			autor = no.getObjeto();
			System.out.println("Livros do Autor " + autor.getNome() + "\n");
			autor.exibirLivros();
			arquivo = new File(nome + ".csv");
			if (!arquivo.exists())
				arquivo.createNewFile();
			FileWriter escritor = new FileWriter(arquivo, true);
			BufferedWriter buffer = new BufferedWriter(escritor);
			buffer.write(autor.listagemLivros());
			buffer.close();
			escritor.close();
			return true;
		} else
			return false;

	}

	/**
	 * Permite a busca de um livro na arvore livros a partir de um dado codigo 
	 * @param codigo - valor inteiro que representa o n_ebook do livro que deseja-se ser encotnrado
	 * @return livro - caso exista um livro com o codigo informado na arvore livros o objeto livro correspondente e retornado
	 * @return null - caso o livro com o codigo informado nao exista na arvore livros
	 * @throws IOException
	 */
	public Livro buscarLivro(int codigo) throws IOException {
		livro = new Livro(codigo);
		No<Livro> n = livros.buscar(livro);
		if (n != null) {
			File arquivoLivro = new File(n.getObjeto().getTitulo() + ".csv");
			FileWriter escritor = new FileWriter(arquivoLivro);
			BufferedWriter buffer = new BufferedWriter(escritor);
			buffer.write(n.getObjeto().getLink());
			buffer.close();
			escritor.close();
			return n.getObjeto();
		} else
			return null;
	}

	/**
	 * Metodo que permite a exibicao dos livros publicados em um dado ano e o armazenamento destes em um arquivo com o nome do ano+.csv
	 * @param num_ano - recebe um inteiro que representa o ano de referencia a ser buscado
	 * @return true - se todas as operacoes forem realizadas com sucesso
	 * @return false - caso ocorra algum erro nas operacoes de busca e escrita
	 * @throws IOException -  coleta erro de excecao do sistema para lidar com arquivos
	 */
	public boolean exibirLivroAno(int num_ano) throws IOException {
		ano = new Ano(num_ano);
		No n = anos.buscar(ano);
		if (n != null) {
			ano = (Ano) n.getObjeto();
			System.out.println("\n\nLivros [" + ano.getAno() + "]");
			ano.exibirLivros();
			File arquivoAno = new File(num_ano + ".csv");
			if (!arquivoAno.exists())
				arquivoAno.createNewFile();
			FileWriter escritor = new FileWriter(arquivoAno);
			BufferedWriter buffer = new BufferedWriter(escritor);
			buffer.write(ano.listagemLivros());
			buffer.close();
			escritor.close();
			return true;
		} else
			return false;

	}

	/**
	 * Metodo que permite a exclusao de um livro na arvore livros a partir de um codigo inteiro
	 * @param codigo - inteiro que representa um n_ebook a ser buscado e excluido da arvore livros
	 * @return true - se o livro for excluido da arvore livros e das ArrayLists do seu autor e ano de publicacao
	 * @return false - se o livro nao for excluido da arvore livros e nem das ArrayLists de seu autor e ano de publicacao
	 * */
	public boolean excluirLivro(int codigo) throws IOException {
		livro = buscarLivro(codigo);
		if (livro != null) {
			livros.remover(livro);			
				autor = livro.getAutor();
				if (autor != null) {					
					autor.removerLivro(livro);
					if (autor.getQuantidade() == 0)
						autores.remover(autor);
				ano = livro.getAno();
				if (ano != null) {					
					ano.removerLivro(livro);
					if (ano.getQuantidade() == 0) {
						anos.remover(ano);
						return true;
					}
				}
				
			}
		}
		return false;
	}
	
	/**
	 * Metodo que chama a exibicao o metodo dos elementos da arvore em ordem
	 * */
	public void exibirEmOdem() {
		livros.emOrdem();
	}

	/**
	 * Metodo publico que serve para chamada do metodo recursivo de mesmo nome
	 */
	public void verificar() {
		verificar(livros.getRaiz());
	}

	/**
	 * Metodo recursivo que permite verificar manualmente e visualmente o balanceamento da arvore
	 * @param no - recebe um determinado no da arvore
	 * @return no 
	 */
	private No verificar(No no) {
		System.out.println("\nNo: " + no.getObjeto().toString());
		if (no.getEsquerda() != null)
			System.out.println("Esquerda: " + no.getEsquerda().getObjeto().toString());

		if (no.getDireita() != null)
			System.out.println("Direita: " + no.getDireita().getObjeto().toString());

		if (no.getEsquerda() != null)
			verificar(no.getEsquerda());

		if (no.getDireita() != null)
			verificar(no.getDireita());

		return no;
	}

}