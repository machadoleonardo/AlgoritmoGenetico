import java.util.Arrays;

import controle.Problema;

public class algoritmo {

	public static void main(String args[]) {
		String nomeDoArquivo = "problema1.txt";
		Leitor leitor = new Leitor(nomeDoArquivo);

		Problema problema = leitor.lerArquivo(); // ler arquivo

		int auxMelhor[] = new int[Config.loop + 1];

		for (int x = 0; x <= Config.loop; x++) { // for 30 vezes

			// vetor para fo
			int fo_volume[] = new int[Config.numeroInd];
			int fo_custo[] = new int[Config.numeroInd];
			int fo[] = new int[Config.numeroInd];

			// inicializar populacao e avaliar
			int populacao[][] = new int[Config.numeroInd][problema.getQtdGenes()];

			for (int i = 0; i < Config.numeroInd; i++) {
				for (int j = 0; j < problema.getQtdGenes(); j++) {
					if (Math.random() < 0.5) {
						populacao[i][j] = 0;
					} else {
						populacao[i][j] = 1;
					}

					// System.out.print(populacao[i][j]);
				}
				// System.out.print("\n");
			}
			// gerando fun��o objetiva
			for (int i = 0; i < Config.numeroInd; i++) {
				int soma = 0;
				for (int j = 0; j < problema.getQtdGenes(); j++) {
					fo_volume[i] += populacao[i][j] * problema.getItens().get(j).getVolume();
					fo_custo[i] += populacao[i][j] * problema.getItens().get(j).getValor();
				}

			}
			// penalidade
			for (int i = 0; i < Config.numeroInd; i++) {
				fo[i] = fo_custo[i] - Config.alpha * Math.abs(Math.min(0, problema.getMochila() - fo_volume[i]));
				System.out.println("volume: " + fo_volume[i] + " valor: " + fo_custo[i] + " DEPOIS: " + fo[i]);
			}

			// processo evolucionario (recombinacao, mutacao e selecao)

			// cruzamento / recombina��o
			// random

			int aux;
			int aux_filho[][] = new int[Config.numeroInd][problema.getQtdGenes()];

			for (int i = 0; i < Config.numeroInd; i++) {
				// aux = nobj /2;
				aux = 5;
				for (i = 0; i < Config.numeroInd; i++) {
					for (int j = 0; j < problema.getQtdGenes(); j++) {
						if (i % 2 == 0) {
							if (j <= aux) {
								aux_filho[i][j] = populacao[i][j];
							} else {
								aux_filho[i][j] = populacao[i + 1][j];
							}
						}
						if (i % 2 != 0) {
							if (j <= aux) {
								aux_filho[i][j] = populacao[i][j];
							} else {
								aux_filho[i][j] = populacao[i - 1][j];
							}
						}
						// System.out.print(aux_filho[i][j]);
					}

					// System.out.print("\n");
				}

				// mutacao
				for (i = 0; i < Config.numeroInd; i++) {
					for (int j = 0; j < problema.getQtdGenes(); j++) {
						if (Math.random() < 0.1) {
							if (aux_filho[i][j] == 0) {
								aux_filho[i][j] = 1;
							} else {
								aux_filho[i][j] = 0;
							}
						}
					}
				}
				// selecao

				int maior = 0;

				for (i = 0; i < Config.numeroInd; i++) {
					for (int j = 0; j < problema.getQtdGenes(); j++) {
						fo_volume[i] += aux_filho[i][j] * problema.getItens().get(j).getVolume();
						fo_custo[i] += aux_filho[i][j] * problema.getItens().get(j).getValor();
					}
					// System.out.print("i: "+fo_volume[i]+"\n");
					if (fo_volume[i] <= problema.getMochila()) {
						if (fo_volume[i] > maior) {
							maior = fo_volume[i];
						}
					}

				}
				auxMelhor[x] = maior;
				// /System.out.print("gera��o " + x + " : ");
				// System.out.print("melhor: " + maior + "\n");
				// System.out.print(b);

				/*
				 * soma cada linha e depois multiplica pelo valor valor,
				 * gerar ranking dos melhores
				 */

			}

		} // fim do loop

		// gerando relatorio dos 30 individuos, o melhor de cada gera��o (elitismo)
		Arrays.sort(auxMelhor);
		int a;
		for (a = Config.loop; a >= 0; a--) {
			System.out.println("Melhor individuo: " + auxMelhor[a]);
		}

		// verificar se os parametros estao certos, volume e custo

	}

}
