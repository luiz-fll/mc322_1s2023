import java.util.List;
import java.util.ArrayList;

public class Cliente {
	protected String nome;
	protected String endereco;
	protected List<Veiculo> listaVeiculos;
	
	// Construtor
	public Cliente(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}
	
	// Getters e setters
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	// Operações sobre os veículos do cliente
	public List<Veiculo> getListaVeiculos() {
		return this.listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public boolean cadastrarVeiculo(Veiculo veiculo) {
		return listaVeiculos.add(veiculo);
	}

	public boolean removerVeiculo(String placaVeiculo) {
		return listaVeiculos.removeIf(veiculo -> veiculo.getPlaca().equals(placaVeiculo));
	}
	
	// Devolve uma representação da classe Cliente em String
	@Override
	public String toString() {
		return
			" nome: '" + getNome() + "'\n" +
			" endereco: '" + getEndereco() + "'\n" +
			" listaVeiculos: '" + getListaVeiculos() + "'\n";
	}
}
