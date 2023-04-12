import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Cliente {
	protected String nome;
	protected String endereco;
	protected LocalDate dataLicenca;
	protected List<Veiculo> listaVeiculos;
	
	// Construtor
	public Cliente(String nome, String endereco, LocalDate dataLicenca) {
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicenca = dataLicenca;
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

	public LocalDate getDataLicenca() {
		return this.dataLicenca;
	}

	public void setDataLicenca(LocalDate dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

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
			" dataLicenca: '" + getDataLicenca() + "'\n" +
			" listaVeiculos: '" + getListaVeiculos() + "'\n";
	}
}
