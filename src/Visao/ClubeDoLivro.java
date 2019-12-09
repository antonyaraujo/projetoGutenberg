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
		System.out.println("Seja bem - vindo ao sistema de catalogação de livros - Comunidade da Batata");
		while (opcao != 0) {			
			System.out.println("\nEscolha uma opção do menu abaixo: \n" + "1. Cadastrar - Livro \n"
					+ "2. Carregar base de dados \n" + "3. Gravar arquivo \n" + "4. Listar - Autores/Quantidade \n"
					+ "5. Lista Autor/Livros \n" + "6. Listar - Livros\n" + "7. Buscar Livro \n"
					+ "8. Buscar Livro/Ano \n" + "9. Excluir Livro \n" + "0. Sair");
			System.out.print("---> ");
			Scanner leitor = new Scanner(System.in);
			opcao = Integer.parseInt(leitor.nextLine());
			switch (opcao) {
				case 1:	
					System.out.print("\nInforme o código: "); 
					int codigo = Integer.parseInt(leitor.nextLine());					
					System.out.print("Informe o título do livro: ");
					String titulo = leitor.nextLine();
					System.out.print("Informe o nome do Autor ");
					String autor = leitor.nextLine();
					System.out.print("Informe o mês de publicação: ");
					char[] mes = leitor.nextLine().toCharArray();
					System.out.print("Informe o ano de publicação: ");
					int ano = Integer.parseInt(leitor.nextLine());
					System.out.print("Informe o endereço eletrônico do livro: ");
					String link = leitor.nextLine();
					Livro livro = new Livro(codigo, titulo, autor, mes, ano, link);
					batata.cadastrarLivro(livro);					
				break;
				case 2: 
					System.out.print("Informe o endereço do aquivo CSV");
					break;
				case 3:
					
				case 0:
					System.exit(0);
					break;
			}
		}

	}
}
