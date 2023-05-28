import java.time.LocalDate;

public class Sinistro {
	private final int id = gerarID();
	private LocalDate data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;
	
	// Construtor
	public Sinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro) {
		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
	}
	
	// getters e setters
	public int getId() {
		return this.id;
	}

	public LocalDate getData() {
		return this.data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Condutor getCondutor() {
		return this.condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public Seguro getSeguro() {
		return this.seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	// Gera uma ID baseada no tempo em milissegundos. Gerar IDs em menos de 1ms provavelmente gerará duplicatas.
	private static int gerarID() {
		long tempoMilissegundos1970 = System.currentTimeMillis(); // Sempre positivo (ACHO)
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			return (int)(Integer.MIN_VALUE + tempoMilissegundos1970 + 1); 
		}
		
		// Os long positivo possuem o mesmo tanto de bit que os int (ACHO)
		// Logo, eu posso fazer casting a partir do mínimo int sem risco de overflow (ACHO)
		return (int)(Integer.MIN_VALUE + tempoMilissegundos1970); 
	}

	@Override
	public String toString() {
		return "[Sinistro " + getId() + 
			   " / " + getData() +
			   " / " + getEndereco() + 
			   " / Condutor " + condutor.getCPF() +
			   " / Seguro id " + seguro.getId() + "]";
	}
}
