import java.time.LocalDate;
import java.util.ArrayList;

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

	public boolean cadastrarVeiculo() {
		return true;
	}

	public boolean removerVeiculo() {
		return true;
	}
}
