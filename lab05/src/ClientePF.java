import java.time.LocalDate;

public class ClientePF extends Cliente {
    private final String CPF;
    private LocalDate dataNascimento;
	private LocalDate dataLicenca;
	private String educacao;
	private String genero;
	private String classeEconomica;

    // Construtor
    public ClientePF(String nome, String endereco, LocalDate dataLicenca, String educacao, String genero, String classeEconomica, String CPF, LocalDate dataNascimento) {
        super(nome, endereco);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.educacao = educacao;
		this.classeEconomica = classeEconomica;
		this.dataLicenca = dataLicenca;
    }    

    // getters e setters
	public String getCPF() {
		return this.CPF;
	}

	public LocalDate getDataLicenca() {
		return this.dataLicenca;
	}

	public void setDataLicenca(LocalDate dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEducacao() {
		return this.educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClasseEconomica() {
		return this.classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}

    @Override
	public String toString() {
		return
			" nome: '" + getNome() + "'\n" +
			" endereco: '" + getEndereco() + "'\n" +
			" dataLicenca: '" + getDataLicenca() + "'\n" +
			" educacao: '" + getEducacao() + "'\n" +
			" genero: '" + getGenero() + "'\n" +
			" classeEconomica: '" + getClasseEconomica() + "'\n" +
            " CPF: '" + getCPF() + "'\n" +
			" listaVeiculos: '" + getListaVeiculos() + "'\n" +
            " dataNascimento: '" + getDataNascimento() + "'\n";
	}

	public double calculaScore() {
		int idadeCliente = LocalDate.now().getYear() - dataNascimento.getYear();
		
		if (18 <= idadeCliente && idadeCliente < 30) {
			return CalcSeguro.VALOR_BASE.valor * CalcSeguro.FATOR_18_30.valor * listaVeiculos.size();
		}
		if (30 <= idadeCliente && idadeCliente < 60) {
			return CalcSeguro.VALOR_BASE.valor * CalcSeguro.FATOR_30_60.valor * listaVeiculos.size();
		}
		if (60 <= idadeCliente && idadeCliente < 90) {
			return CalcSeguro.VALOR_BASE.valor * CalcSeguro.FATOR_60_90.valor * listaVeiculos.size();
		}
		return 0;
	}
}
