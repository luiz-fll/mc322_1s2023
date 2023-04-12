import java.time.LocalDate;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;

    // Construtor
    public ClientePJ(String nome, String endereco, LocalDate dataLicenca, String CNPJ, LocalDate dataFundacao) {
		super(nome, endereco, dataLicenca);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
	}

    // getters e setters
    public String getCNPJ() {
        return this.CNPJ;
    }

    public LocalDate getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
	public String toString() {
		return
			" nome: '" + getNome() + "'\n" +
			" endereco: '" + getEndereco() + "'\n" +
			" listaVeiculos: '" + getListaVeiculos() + "'\n" +
            " CNPJ: '" + getCNPJ() + "'\n" +
            " dataFundacao: '" + getDataFundacao() + "'\n";
	}
}
