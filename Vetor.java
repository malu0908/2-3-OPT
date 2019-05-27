public class Vetor {
	public int vetor[] = new int[58];
	public static Vetor vet = new Vetor();
	
	private Vetor() {
		for(int i = 0; i < vetor.length; i++) {
			vetor[i] = i;
		}
	}
	
	public int[] getVetor() {
		return vetor;
	}
	
	public static Vetor getVet() {
		return vet;
	}
	
	public void setVetor(int v[]) {
		vetor = v.clone();
	}
}