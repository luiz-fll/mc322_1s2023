import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;

public class ClientePF extends Cliente {
    private final String CPF;
	private String genero;
	private String educacao;
    private LocalDate dataNascimento;
	private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    // Construtor

	public ClientePF(String nome, String telefone, String endereco, String email, String CPF, String genero, String educacao, LocalDate dataNascimento) {
		super(nome, telefone, endereco, email);
		this.CPF = CPF;
		this.genero = genero;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
	}
        
    // getters e setters
	public String getCPF() {
		return this.CPF;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEducacao() {
		return this.educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return this.listaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public Veiculo procurarVeiculo(String placa) 
	throws NameNotFoundException {
		return listaVeiculos
		.stream()
		.filter(veiculo -> veiculo.getPlaca().equals(placa))
		.findAny()
		.orElseThrow(() -> new NameNotFoundException("Veículo não encontrado: " + placa));
	}

	public boolean cadastrarVeiculo(String placa, String marca, String modelo, int anoFabricacao) 
	throws NameAlreadyBoundException {
		try {
			procurarVeiculo(placa);
			throw new NameAlreadyBoundException("Veículo já  existe: " + placa);
		} catch (NameNotFoundException e) {
			return listaVeiculos.add(new Veiculo(placa, marca, modelo, anoFabricacao));
		}
	}

	public boolean removerVeiculo(String placa) {
		return listaVeiculos
		.removeIf(veiculo -> veiculo.getPlaca().equals(placa));
	}

	@Override
	public String toString() {
		return super.toString() + "\n" +
			   "CPF: " + getCPF() + "\n" +
			   "Educação: " + getEducacao() + "\n" +
			   "Gênero: " + getGenero() + "\n" +
			   "Data de Nascimento: " + getDataNascimento() + "\n" +
			   "Veículos: " + getListaVeiculos().stream().map(Veiculo::getPlaca).collect(Collectors.toList());
	}
}