import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int input = -1;
		
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
		} catch (Exception e) {
			System.out.println("Insira um número válido!");
		}

		switch (input) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			default:
				break;
		}

		sc.close();
	}
}
