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

import Modelo.Livro;

public class SistemaCatalogacao {
	File arquivo = new File("base.csv");
	boolean existe = arquivo.exists();

	public ArrayList baseLivros() throws IOException {
		ArrayList base = new ArrayList();
		if (existe) {
			FileReader leitor = new FileReader(arquivo);
			BufferedReader buffer = new BufferedReader(leitor);
			while (buffer.ready()) {
				String[] dados = buffer.readLine().split(";");
				Livro livro = new Livro(Integer.parseInt(dados[0]), dados[1], dados[2], (dados[3]).toCharArray(),
						Integer.parseInt(dados[4]), dados[5]);
				base.add(livro);
			}
		}

		return base;
	}

	public Livro buscarLivro(int codigo) throws IOException {
		ArrayList livros = baseLivros();
		for (int c = 0; c < livros.size(); c++) {
			Livro l = (Livro) livros.get(c);
			if (l.getN_ebook() == codigo) {
				return l;
			}
		}
		return null;
	}

	public boolean cadastrarLivro(Livro l) throws IOException {
		String livro = l.getN_ebook() + ";" + l.getTitulo() + ";" + l.getAutor().getNome() + ";"
				+ String.copyValueOf(l.getMes()) + ";" + l.getAno() + ";" + l.getLink();
		if (existe) {
			if (buscarLivro(l.getN_ebook()) == null) {
				FileWriter escritor = new FileWriter(arquivo, true);
				BufferedWriter buffer = new BufferedWriter(escritor);
				buffer.write(livro);
				buffer.newLine();
				buffer.close();
				escritor.close();
				return true;
			}
		}
		return false;
	}
}
