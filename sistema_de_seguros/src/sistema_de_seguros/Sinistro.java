package sistema_de_seguros;

public class Sinistro {
	private static int contador = 0; // Conta quantos sinistros estão registrados no sistema
	private int id;
	private String data;
	private String endereco;
	
	// Construtor
	public Sinistro(String data, String endereco) {
		this.id = gerarID();
		this.data = data;
		this.endereco = endereco;
	}
	
	// Getters e setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	// Gera uma nova ID
	private int gerarID() {
		contador++;
		return contador;
	}
}
