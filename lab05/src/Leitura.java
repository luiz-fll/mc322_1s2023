import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Leitura {
    public static String lerCNPJ(Scanner sc) 
    throws InputMismatchException {
        System.out.print("CNPJ: ");
        String cnpj = sc.nextLine();
        if (Validacao.validaCNPJ(cnpj)) {
            return cnpj;
        };

        throw new InputMismatchException("CNPJ inválido: " + cnpj);
    }

    public static String lerNome(Scanner sc) 
    throws InputMismatchException {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        if (Validacao.validaNome(nome)) {
            return nome;
        };

        throw new InputMismatchException("Nome inválido: " + nome);
    }

    public static String lerTelefone(Scanner sc) 
    throws InputMismatchException {
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        if (Validacao.validaTelefone(telefone)) {
            return telefone;
        };

        throw new InputMismatchException("Telefone inválido: " + telefone);
    }

    public static String lerString(Scanner sc, String prompt) {
        System.out.print(prompt + ": ");
        String string = sc.nextLine();

        return string;
    }

    public static String lerEmail(Scanner sc) 
    throws InputMismatchException {
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        if (Validacao.validaEmail(email)) {
            return email;
        };

        throw new InputMismatchException("E-mail inválido: " + email);
    }

    public static String lerCPF(Scanner sc) 
    throws InputMismatchException {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        if (Validacao.validaCPF(cpf)) {
            return cpf;
        };

        throw new InputMismatchException("CPF inválido: " + cpf);
    }

    public static LocalDate lerData(Scanner sc, String tipoData) 
    throws InputMismatchException {
        System.out.print(tipoData + ": ");
        LocalDate data = null;
        String dataString = sc.nextLine();
        if (Validacao.validaData(dataString, data)) {
            return data;
        };

        throw new InputMismatchException(tipoData + " inválida: " + dataString);
    }

    public static String lerPlaca(Scanner sc) 
    throws InputMismatchException {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        if (Validacao.validaPlaca(placa)) {
            return placa;
        };

        throw new InputMismatchException("Placa inválida: " + placa);
    }

    public static int lerInteiro(Scanner sc, String prompt) 
    throws NumberFormatException {
        System.out.print(prompt + ": ");
        String inteiro = sc.nextLine();
        
        return Integer.parseInt(inteiro);
    }
}
