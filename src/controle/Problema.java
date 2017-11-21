package controle;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Problema {
	int qtdGenes; // vai receber a quantidade de genes
	int mochila; // vai receber a capacidade da mochila
	ArrayList<Item> itens;

}
