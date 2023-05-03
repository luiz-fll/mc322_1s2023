import java.time.LocalDate;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;

    // Construtor
    public ClientePJ(String nome, String endereco, String CNPJ, LocalDate dataFundacao, int qtdeFuncionarios) {
		super(nome, endereco);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
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

    public int getQtdeFuncionarios() {
        return this.qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
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

    public double calculaScore() {
        return CalcSeguro.VALOR_BASE.valor * (1 + (qtdeFuncionarios)) * listaVeiculos.size(); 
    }
}
