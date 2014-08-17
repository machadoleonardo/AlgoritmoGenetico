import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

import org.omg.CORBA.PUBLIC_MEMBER;

public class algoritmo {

	public static void main(String args[]) {

		String nomeDoArquivo = "problema2.txt";
		int b = 0; // vai receber a capacidade da mochila
		int nobj = 0; // vai receber a quantidade de genes
		int beneficio[] = null;
		int peso[] = null;
		int loop = 30;
		int aux_melhor[] = new int[loop + 1];

		// ler arquivo

		try {
			Scanner scanner = new Scanner(new File(nomeDoArquivo));
			nobj = scanner.nextInt();
			b = scanner.nextInt();

			beneficio = new int[nobj];
			peso = new int[nobj];

			for (int i = 0; i < nobj; i++) {
				String texto = scanner.next();
				String aux[] = texto.split(";");
				beneficio[i] = Integer.parseInt(aux[0]);
				peso[i] = Integer.parseInt(aux[1]);
			}

		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		for (int x = 0; x <= loop; x++) { // for 30 vezes

			// definir parametros
			float prob_recombinacao = 0.95f;
			float prob_mutacao = 0.1f;
			int numero_ind = 10;
			int alpha = 1;

			// vetor para fo
			int fo_peso[] = new int[numero_ind];
			int fo_custo[] = new int[numero_ind];
			int fo[] = new int[numero_ind];

			// inicializar populacao e avaliar
			int populacao[][] = new int[numero_ind][nobj];

			for (int i = 0; i < numero_ind; i++) {
				for (int j = 0; j < nobj; j++) {
					if (Math.random() < 0.5)
						populacao[i][j] = 0;
					else
						populacao[i][j] = 1;

					// System.out.print(populacao[i][j]);
				}
				// System.out.print("\n");
			}
			// gerando função objetiva
			for (int i = 0; i < numero_ind; i++) {
				int soma = 0;
				for (int j = 0; j < nobj; j++) {
					fo_peso[i] += populacao[i][j] * peso[j];
					fo_custo[i] += populacao[i][j] * beneficio[j];
				}

			}
			// penalidade
			for (int i = 0; i < numero_ind; i++) {
				fo[i] = fo_custo[i] - alpha
						* Math.abs(Math.min(0, b - fo_peso[i]));
				 System.out.println("PESO: "+fo_peso[i]+" BENEFICIO: "+fo_custo[i] + " DEPOIS: "+fo[i]);
			}

			// processo evolucionario (recombinacao, mutacao e selecao)
			
			// cruzamento / recombinação
			// random

			int aux;
			int aux_filho[][] = new int[numero_ind][nobj];

			for (int i = 0; i < numero_ind; i++) {
				// aux = nobj /2;
				aux = 5;
				for (i = 0; i < numero_ind; i++) {
					for (int j = 0; j < nobj; j++) {
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
				for (i = 0; i < numero_ind; i++) {
					for (int j = 0; j < nobj; j++) {
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

				for (i = 0; i < numero_ind; i++) {
					for (int j = 0; j < nobj; j++) {
						fo_peso[i] += aux_filho[i][j] * peso[j];
						fo_custo[i] += aux_filho[i][j] * beneficio[j];
					}
					// System.out.print("i: "+fo_peso[i]+"\n");
					if (fo_peso[i] <= b) {
						if (fo_peso[i] > maior) {
							maior = fo_peso[i];
						}
					}

				}
				aux_melhor[x] = maior;
			//	/System.out.print("geração " + x + " : ");
				//System.out.print("melhor: " + maior + "\n");
				// System.out.print(b);

				/*
				 * soma cada linha e depois multiplica pelo valor beneficio,
				 * gerar ranking dos melhores
				 */
				

			}

		}//fim do loop

		//gerando relatorio dos 30 individuos, o melhor de cada geração (elitismo)
		Arrays.sort(aux_melhor);
		int a;
		for (a=loop;a>=0;a--) {
			System.out.println("Melhor individuo: "+aux_melhor[a]);
		}
		
		
		// verificar se os parametros estao certos, peso e custo

	}

}
