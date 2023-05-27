import java.util.ArrayList;
import java.util.Scanner;

import javax.naming.NameAlreadyBoundException;

public class Main {
	public static void main(String args[]) throws NameAlreadyBoundException {
		ClientePJ c = new ClientePJ("A", "a", "null", "null", "null", null);
		c.cadastrarFrota(new Frota("a"));
		c.cadastrarFrota(new Frota("b"));
		System.out.println(c);
	}

	public static void menu() {
		Scanner sc = new Scanner(System.in);
		ArrayList<Seguradora> seguradoras = new ArrayList<Seguradora>();
		Seguradora seguradoraSelecionada = null;
		Cliente clienteSelecionado = null;
		Seguro seguroSelecionado = null;
		Condutor condutorSelecionado = null;
		Menu menuAtual = null;
		Operacao operacaoAtual = Operacao.MENU_PRINCIPAL;

		while (true) {
			switch (operacaoAtual) {
				case MENU_PRINCIPAL:
					menuAtual = Menu.MenuPrincipal(seguradoras);
					menuAtual.mostrar();
					operacaoAtual = menuAtual.selecionarOperacao(sc);
					break;
				case CRIAR_SEGURADORA:
					System.out.println("Insira os dados da Seguradora...");
					try {
						String cnpj = Leitura.lerCNPJ(sc);
						String nome = Leitura.lerString(sc, "Nome");
						String telefone = Leitura.lerTelefone(sc);
						String endereco = Leitura.lerString(sc, "Endereço");
						String email = Leitura.lerEmail(sc);
						seguradoraSelecionada = new Seguradora(cnpj, nome, telefone, email, endereco);
						seguradoras.add(seguradoraSelecionada);
						operacaoAtual = Operacao.PAINEL_SEGURADORA;
					} catch (Exception e) {
						System.out.println(e);
						operacaoAtual = Operacao.MENU_PRINCIPAL;
					}
					break;
				case PAINEL_SEGURADORA:
					menuAtual = Menu.PainelSeguradora(seguradoraSelecionada);
					menuAtual.mostrar();
					operacaoAtual = menuAtual.selecionarOperacao(sc);
					break;
				case PAINEL_SEGURO:
				// Listar seguros e selecionar um
					menuAtual = Menu.PainelSeguro(seguroSelecionado);
					break;
				default:
					break;
			}
		}
	}
}
