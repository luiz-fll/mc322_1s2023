public class Cliente {
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;
	
	// Construtor
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
	}
	
	// Getters e setters
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCPF() {
		return cpf;
	}
	
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	// Devolve uma representação da classe Cliente em String
	public String toString() {
		return "Nome: " + this.nome + "\n"
			 + "CPF: " + this.cpf + "\n"
	  	     + "Data de Nascimento : " + this.dataNascimento + "\n"
		     + "Idade: " + Integer.toString(this.idade) + "\n"
		     + "Endereço: " + this.endereco + "\n";
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
}
