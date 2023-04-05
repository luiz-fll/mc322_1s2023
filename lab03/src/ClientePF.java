import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente {
    private final String CPF;
    private Date dataNascimento;

    // Construtor
    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, List<Veiculo> listaVeiculos, String CPF, Date dataNascimento) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }    

    // getters e setters
    public String getCPF() {
		return CPF;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

    // Recebe um CPF e retorna 'true' caso seja válido, caso contrário retorna 'false'
	public boolean validarCPF(String cpf) {
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
			" listaVeiculos: '" + getListaVeiculos() + "'\n" +
            " CPF: '" + getCPF() + "'\n" +
            " dataNascimento: '" + getDataNascimento() + "'\n";
	}
}
