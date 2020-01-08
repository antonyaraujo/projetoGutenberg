package Visao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Controle.SistemaCatalogacao;
import Modelo.Livro;

public class ClubeDoLivro {

	public static void main(String[] args) throws IOException {
		SistemaCatalogacao batata = new SistemaCatalogacao();

		int opcao = 9;
		boolean verificador = false, salvar = false;

		System.out.println("Seja bem - vindo ao sistema de catalogação de livros - Comunidade da Batata");
		while (opcao != 0) {
			System.out.println("\nEscolha uma opção do menu abaixo: \n" + "1. Cadastrar - Livro \n"
					+ "2. Carregar base de dados \n" + "3. Gravar arquivo \n" + "4. Listar - Autores/Quantidade \n"
					+ "5. Lista Autor/Livros \n" + "6. Listar - Livros\n" + "7. Buscar Livro \n"
					+ "8. Buscar Livro/Ano \n" + "9. Excluir Livro \n" + "0. Sair");
			if (salvar)
				System.out.println("As alterações realizadas ainda não foram salvas em um arquivo!!");
			System.out.print("---> ");
			Scanner leitor = new Scanner(System.in);
			opcao = Integer.parseInt(leitor.nextLine());
			switch (opcao) {
			case 1:
				/** Cadastrar - Livro: permite a inserção de um livro no sistema da batata */
				System.out.print("\nInforme o código: ");
				int codigo = Integer.parseInt(leitor.nextLine());
				System.out.print("Informe o título do livro: ");
				String titulo = leitor.nextLine();
				System.out.print("Informe o nome do Autor: ");
				String autor = leitor.nextLine();
				System.out.print("Informe o mês de publicação: ");
				char[] mes = leitor.nextLine().toCharArray();
				System.out.print("Informe o ano de publicação: ");
				int ano = Integer.parseInt(leitor.nextLine());
				System.out.print("Informe o endereço eletrônico do livro: ");
				String link = leitor.nextLine();
				verificador = batata.cadastrarLivro(codigo, titulo, autor, mes, ano, link);
				if (verificador) {
					System.out.println("Cadastrado com sucesso!");
					salvar = true;
				} else
					System.out.println("Livro não cadastrado!!");
				break;

			case 2:
				/** Carregar base de dados */
				System.out.print("Informe o endereço do aquivo CSV: ");
				String endereco = leitor.nextLine();
				if (batata.carregarBase(endereco))
					System.out.println("Base de Arquivos carregada com sucesso!;");
				else
					System.out.println("Falha em carregar a base de arquivos");
				break;
			case 3:
				/** Gravar arquivo */
				if (salvar)
					if (batata.gravarLivros()) {
						System.out.println("Alterações realizadas com sucesso!");
						salvar = false;
					} else
						System.out.println("Não há nada a ser salvo");
				break;
			case 4:
				/** Listar Autores Quantidade */
				batata.listarAutoresQtde();
				break;
			case 5:
				/** Listar Autor/Livros */
				batata.exibirLivroAutor();
				break;
			case 6:
				/** Listar - Livros */
				System.out.println("Todos os Livros cadastrados");
				batata.exibirLivros();
				break;
			case 7:
				/** Buscar Livro */
				System.out.println("Informe o nº do ebook: \n--> ");
				int nebook = Integer.parseInt(leitor.nextLine());
				Livro l = batata.buscarLivro(nebook);
				if (l == null)
					System.out.println("Livro nao encontrado na base de dados!");
				else
					System.out.println(l.getTitulo() + " disponível em: \n" + l.getLink());
				break;
			case 8:
				/** Buscar Livro/Ano */
				System.out.println("Informe um ano, xuxuzinho: ");
				ano = Integer.parseInt(leitor.nextLine());
				batata.exibirLivroAno(ano);
				break;
			case 9:
				/** Excluir Livro */
				System.out.println("Informe o nº do ebook a ser excluído");
				codigo = Integer.parseInt(leitor.nextLine());
				batata.excluirLivro(codigo);
				break;
			case 0:
				if (salvar) {
					System.out.println("Há alterações ainda não salvas, tem certeza que deseja sair sem salvá-las?");
					opcao = 23;
					String opc = leitor.nextLine();
					if (opc.equalsIgnoreCase("S"))
						System.exit(0);
				} else
					System.exit(0);
				break;
			}
		}

	}
}
