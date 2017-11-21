import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import controle.Item;
import controle.Problema;

public class Leitor {

	private Scanner scanner;

	public Leitor(String nomeDoArquivo) {
		try {
			this.scanner = new Scanner(new File(nomeDoArquivo));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Problema lerArquivo() {
		Problema problema = null;

		int qtdGenes = this.scanner.nextInt();
		int mochila = this.scanner.nextInt();
		ArrayList<Item> itens = new ArrayList<>();
		while (itens.size() < qtdGenes) {
			String texto = this.scanner.next();
			String aux[] = texto.split(";");
			int valor = Integer.parseInt(aux[0]);
			int volume = Integer.parseInt(aux[1]);
			Item item = new Item(valor, volume);
			itens.add(item);
		}
		problema = new Problema(qtdGenes, mochila, itens);

		return problema;
	}

}
