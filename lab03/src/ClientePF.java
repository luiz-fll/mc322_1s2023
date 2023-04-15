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

    // Recebe um CPF e retorna 'true' caso seja válido, caso contrário retorna 'false'
	public static boolean validarCPF(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", ""); // Apaga caracteres não numéricos
		
		if (cpf.length() != 11) {
			return false;
		}
		
		switch(cpf) {
			case "00000000000":
			case "11111111111":
			case "22222222222":
			case "33333333333":
			case "44444444444":
			case "55555555555":
			case "66666666666":
			case "77777777777":
			case "88888888888":
			case "99999999999":
				return false;
			default: // Só para casos sem onze dígitos repitidos
				int somaPrimeiroVerificador = 0; 
				int somaSegundoVerificador = 0;
				int primeiroVerificador = Character.getNumericValue(cpf.charAt(9));
				int segundoVerificador = Character.getNumericValue(cpf.charAt(10));
				
				// Loop que realiza a soma dos dígitos conforme a regra do CPF
				for (int i = 2; i <= 11; i++) {
					if (i < 11) {
						somaPrimeiroVerificador += i * Character.getNumericValue(cpf.charAt(10 - i));
					}
					somaSegundoVerificador += i * Character.getNumericValue(cpf.charAt(11 - i));
				}

				return (
						(somaPrimeiroVerificador % 11 < 2 && primeiroVerificador == 0
						|| primeiroVerificador == 11 - somaPrimeiroVerificador % 11)
					&&
						(somaSegundoVerificador % 11 < 2 && segundoVerificador == 0
						|| segundoVerificador == 11 - somaSegundoVerificador % 11)
					);
		}
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
}
