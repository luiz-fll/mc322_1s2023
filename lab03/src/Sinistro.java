import java.time.LocalDate;

public class Sinistro {
	private static int contador = 0; // Conta quantos sinistros estão registrados no sistema
	private final int id;
	private LocalDate data;
	private String endereco;
	private Seguradora seguradora;
	private Cliente cliente;
	private Veiculo veiculo;
	
	// Construtor
	public Sinistro(LocalDate data, String endereco, Seguradora seguradora, Cliente cliente, Veiculo veiculo) {
		this.id = gerarID();
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.cliente = cliente;
		this.veiculo = veiculo;
	}
	
	// getters e setters
	public int getID() {
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

	public Seguradora getSeguradora() {
		return this.seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	// Gera uma nova ID
	private int gerarID() {
		contador++;
		return contador;
	}

	@Override
	public String toString() {
		return
			" id: '" + getID() + "'\n" +
			" cliente: '" + getCliente().getNome() + "'\n" +
			" endereco: '" + getEndereco() + "'\n" +
			" data: '" + getData() + "'\n" +
			" veiculo: '" + getVeiculo().getPlaca() + "'\n";
	}
}
