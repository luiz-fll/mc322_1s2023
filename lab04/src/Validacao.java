public class Validacao {
    // Recebe um CPF e retorna 'true' caso seja válido, caso contrário retorna 'false'
	public static boolean validaCPF(String cpf) {
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

    // Recebe um CNPJ e retorna 'true' caso seja válido, caso contrário retorna 'false'
	public static boolean validaCNPJ(String cnpj) {
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

    public static boolean validaNome(String nome) {
        return !nome.matches("\\d");
    }
}
