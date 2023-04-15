import java.time.LocalDate;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;

    // Construtor
    public ClientePJ(String nome, String endereco, String CNPJ, LocalDate dataFundacao) {
		super(nome, endereco);
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

    // Recebe um CNPJ e retorna 'true' caso seja válido, caso contrário retorna 'false'
	public static boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            return false;
        }

        int[] tabelaPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int valorSomatorioVerificacao = 0;

        for (int i = 0; i < tabelaPrimeiroDigito.length; i++) {
            valorSomatorioVerificacao += tabelaPrimeiroDigito[i] * (cnpj.charAt(i) - '0');
        }

        int restoVerificacao = valorSomatorioVerificacao % 11;

        if (restoVerificacao < 2 && (cnpj.charAt(12) - '0') != 0) {
            return false;
        }
        else if (restoVerificacao >= 2 && (cnpj.charAt(12) - '0') != (11 - restoVerificacao))
        {
            return false;
        }
        int[] tabelaSegundoDigito = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        valorSomatorioVerificacao = 0;

        for (int i = 0; i < tabelaSegundoDigito.length; i++) {
            valorSomatorioVerificacao += tabelaSegundoDigito[i] * (cnpj.charAt(i) - '0');
        }
        restoVerificacao = valorSomatorioVerificacao % 11;

        if (restoVerificacao < 2 && (cnpj.charAt(13) - '0') != 0) {
            return false;
        }
        else if (restoVerificacao >= 2 && (cnpj.charAt(13) - '0') != (11 - restoVerificacao))
        {
            return false;
        }

        return true;
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
