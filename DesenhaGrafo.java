

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class DesenhaGrafo extends JPanel implements Observer {
	public int[] rota; // rota completa para imprimir

	public DesenhaGrafo() {
		this.rota = Vetor.getVet().vetor; // inicializacao recebendo a rota
	}

	@Override
	public void paintComponent(Graphics grafo) {
		super.paintComponent(grafo);
		this.setBackground(Color.black);

		int tamanho = 10;
		int j = 1, i = 0;
		grafo.setColor(Color.cyan);
		grafo.fillOval((int) (Matrizes.getMatriz()[this.rota[i]][0] * 5) - tamanho/2, (int) (Matrizes.getMatriz()[this.rota[i]][1] * 5) - tamanho/2, tamanho, tamanho);
		for (i = 1; i < rota.length; i++) { // desenha as cidades
			if (i % 2 == 0)
				grafo.setColor(Color.red); // senao colore de vermelho
			else
				grafo.setColor(Color.blue); // senao colore de vermelho
			grafo.fillOval((int) (Matrizes.getMatriz()[this.rota[i]][0] * 5) - tamanho/2, (int) (Matrizes.getMatriz()[this.rota[i]][1] * 5) - tamanho/2, tamanho, tamanho);
		}
		for (i = 0; i < rota.length && j < rota.length; i++) {
			grafo.setColor(Color.white); // colore as arestas de preto
			grafo.drawLine((int) (Matrizes.getMatriz()[this.rota[i]][0] * 5), (int) (Matrizes.getMatriz()[this.rota[i]][1] * 5),
					(int) (Matrizes.getMatriz()[rota[j]][0] * 5), (int) (Matrizes.getMatriz()[this.rota[j]][1] * 5));
			j++;
		}

		// colore o ultimo vertice de magenta
		grafo.setColor(Color.MAGENTA);
		grafo.fillOval((int) (Matrizes.getMatriz()[this.rota[j - 1]][0] * 5) - tamanho/2, (int) (Matrizes.getMatriz()[this.rota[j - 1]][1] * 5) - tamanho/2, tamanho,
				tamanho);

		// colore o vertice que interliga o primeiro vertice com o ultimo de amarelo
		grafo.setColor(Color.yellow);
		grafo.drawLine((int) (Matrizes.getMatriz()[this.rota[j - 1]][0] * 5), (int) (Matrizes.getMatriz()[this.rota[j - 1]][1] * 5),
				(int) (Matrizes.getMatriz()[this.rota[0]][0] * 5), (int) (Matrizes.getMatriz()[this.rota[0]][1] * 5));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.repaint();
		try {Thread.sleep(100);} catch (InterruptedException ex) {}
	}
}
