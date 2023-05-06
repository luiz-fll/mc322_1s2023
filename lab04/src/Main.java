import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int input;
		
		execucao:
        while (true) {
            System.out.println("### App Seguradora ###");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Listar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Gerar Sinistro");
            System.out.println("5 - Transferir Seguro");
            System.out.println("6 - Calcular Receita Seguradora");
            System.out.println("0 - Sair");

            try {
                input = Integer.parseInt(sc.nextLine());
                switch (MenuOperacoes.getOperacao(input)) {
                    case CADASTROS:
                        break;
                    case LISTAR:
                        break;
                    case EXCLUIR:
                        break;
                    case GERAR_SINISTRO:
                        break;
                    case TRANSFERIR_SEGURO:
                        break;
                    case CALCULAR_RECEITA_SEGURADORA:
                        break;
                    case SAIR:
                        break execucao;
                    default:
						System.out.println("Insira um número válido!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Insira um número válido!");
            }
        }

		sc.close();
	}
}
