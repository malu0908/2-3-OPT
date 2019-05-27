import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DesenhaTela extends JFrame {

	private static final long serialVersionUID = 1L;
	int tamanho = 58;
	private DesenhaGrafo grafo = new DesenhaGrafo();

	public DesenhaTela() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Caixeiro Viajante - 2-opt");
		
		getContentPane().add(grafo);
		this.setVisible(true);
	}

	public DesenhaGrafo getDesenhaGrafo() {
		return this.grafo;
	}
}