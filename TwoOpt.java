import java.io.*;
import java.util.*;
import javax.swing.JFrame;

public class TwoOpt extends Observable {
	public static int[] procuraMaiorOrigem(double m[][], int v[], boolean visitados[]) {
		double SomaAnt = 500000;
		double Soma = 0;
		int melhor[] = new int[v.length];
		for (int i = 0; i < m.length; i++) {
			v[0] = i;
			visitados[i] = true;
			vizinhoMaisProx(m, v, 1, i, visitados);

			// inicializando com false novamente
			for (int j = 0; j < visitados.length; j++) {
				visitados[j] = false;
			}

			Soma = calculaDistancia(m, v);

			if (Soma < SomaAnt) {
				SomaAnt = Soma;
				melhor = v.clone();
			}
		}
		return melhor;
	}

	public static double calculaDistancia(double m[][], int v[]) {
		double soma = 0;
		// soma as distancias das cidades consecutivas do vetor v
		for (int i = 0; i < m.length - 1; i++) {
			soma += m[v[i]][v[i + 1]];
		}
		// soma as distancias da primeira cidade com a ultima
		soma += m[v[0]][v[v.length - 1]];
		return soma;
	}

	public static int buscaMelhor(double m[][], int i, boolean visitados[]) {
		double menor = 10000.0;
		int j;
		int indice = 0;
		for (j = 0; j < m.length; j++) {
			// se o vertice nao tiver sido visitado
			if (visitados[j] == false)
				// se a distancia do vertice atual for menor que o menor anterior o menor recebe
				// ele e o indice recebe o vertice
				if (m[i][j] < menor && i != j) {
					menor = m[i][j];
					indice = j;
				}
		}
		return indice;
	}

	public static int[] vizinhoMaisProx(double m[][], int v[], int j, int w, boolean visitados[]) {
		for (int i = 1; i < m.length; i++) {
			// busca o vertice com menor distancia de determinado vertice
			int melhor = buscaMelhor(m, w, visitados);
			// vetor auxiliar visitados da posicao do vertice com menor distancia recebe
			// true
			visitados[melhor] = true;
			// v na posicao j recebe o vertice com menor posicao
			v[j] = melhor;
			j++;
			w = melhor;
		}
		return v;
	}

	public static int[] swap(int i, int j) {
		int v[] = new int[Vetor.getVet().vetor.length];

		for (int c = 0; c <= i - 1; c++) {
			v[c] = Vetor.getVet().vetor[c];
		}

		int x = j;
		for (int d = i; d <= j; d++) {
			v[d] = Vetor.getVet().vetor[x];
			x--;
		}

		for (int e = j + 1; e < Vetor.getVet().vetor.length; e++) {
			v[e] = Vetor.getVet().vetor[e];
		}

		return v;
	}

	public static void Topt(double somaAnt) {
		int count = 0;
		int temp[] = new int[Vetor.getVet().vetor.length];

		while (count < 100) {

			for (int i = 1; i < Vetor.getVet().vetor.length; i++) {
				for (int j = i + 1; j < Vetor.getVet().vetor.length; j++) {
					temp = swap(i, j);
					double novaDistancia = calculaDistancia(Matrizes.getMatrizAdj(), temp);

					if (novaDistancia < somaAnt) {
						somaAnt = novaDistancia;
						Vetor.getVet().vetor = temp;

					}

				}
			}
			count++;

		}
	}

	public static void main(String[] args) throws IOException {
		Matrizes.inicializa();

		boolean visitados[] = new boolean[58];

		Vetor.getVet().vetor = procuraMaiorOrigem(Matrizes.getMatrizAdj(), Vetor.getVet().vetor, visitados);

		System.out.println();

		double soma = calculaDistancia(Matrizes.getMatrizAdj(), Vetor.getVet().vetor);

		Topt(soma);

		soma = calculaDistancia(Matrizes.getMatrizAdj(), Vetor.getVet().vetor);

		System.out.println(soma);

		// printa o vetor v
		System.out.println();
		for (int s = 0; s < Vetor.getVet().vetor.length; s++) {
			System.out.print(Vetor.getVet().vetor[s] + ",");
		}
		System.out.println();

		DesenhaTela tela = new DesenhaTela();
	}

}
