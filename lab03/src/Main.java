import java.time.LocalDate;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		String input = new String();
		Scanner sc = new Scanner(System.in);

		System.out.println("### Programa Seguradora ###");
		System.out.println("Selecione uma das opcoes:");
		System.out.println("1 - Executar teste do programa");
		System.out.println("2 - Criar Seguradora");
		System.out.println("Outras teclas - Sair");

		input = sc.nextLine();

		switch (input) {
			case "1":
				// Instanciando clientes
				ClientePF clienteF = new ClientePF("Joao", "Campinas-SP", LocalDate.of(2022, 8, 13), "Ensino Superior", "Masculino", "BURGUÊS", "76293785", LocalDate.of(2001, 9, 11));
				ClientePJ clienteJ = new ClientePJ("Empresa", "São Paulo-SP", "91.629.063/0001-67", LocalDate.of(2002, 12, 3));

				// Validando CPF e CNPJ
				System.out.println("CPF é válido? " + ClientePF.validarCPF(clienteF.getCPF()));
				System.out.println("CNPJ é válido? " + ClientePJ.validarCNPJ(clienteJ.getCNPJ()));

				// Cadastrando Veículos
				clienteF.cadastrarVeiculo(new Veiculo("ABC-1234", "Honda", "Civic", 2013));
				clienteJ.cadastrarVeiculo(new Veiculo("CBA-4321", "Toyota", "Camry", 2017));

				// Instanciando Seguradora e cadastrando clientes
				Seguradora seg = new Seguradora("Seguradora", "4002-8922", "email@email.com", "São Paulo-SP");
				seg.cadastrarCliente(clienteF);
				seg.cadastrarCliente(clienteJ);

				// Usando métodos da Seguradora
				seg.gerarSinistro(clienteJ, clienteJ.getListaVeiculos().get(0));
				seg.gerarSinistro(clienteF, clienteF.getListaVeiculos().get(0));
				
				System.out.println("VISUALIZANDO SINISTRO");
				seg.visualizarSinistro("Joao");
				
				System.out.println("LISTANDO CLIENTES");
				System.out.println(seg.listarClientes("Ambos"));

				// toString das classes
				System.out.println("toString Cliente");
				System.out.println(clienteF);
				System.out.println("toString ClientePF");
				System.out.println(clienteF);
				System.out.println("toString ClientePJ");
				System.out.println(clienteJ);
				System.out.println("toString Veiculo");
				System.out.println(clienteF.getListaVeiculos().get(0));
				System.out.println("toString Sinistro");
				System.out.println(seg.listarSinistros().get(0)); // Aqui tem o método listarSinistros
				break;
			case "2":
				System.out.println("Insira Nome, Telefone, E-mail e Endereco separados por 'Enter':");
				String nome = sc.nextLine();
				String telefone = sc.nextLine();
				String email = sc.nextLine();
				String endereco = sc.nextLine();

				Seguradora novaSeguradora = new Seguradora(nome, telefone, email, endereco);

				System.out.println("Seguradora criada! Selecione as opcoes:");
				System.out.println("1 - Cadastrar Cliente Pessoa Física");
				System.out.println("2 - Cadastrar Cliente Pessoa Jurídica");
				System.out.println("Outras teclas - Sair");
				
				input = sc.nextLine();
				String nomeCliente, enderecoCliente, CNPJ, CPF, genero, educacao, classeEconomica, placa, marca, modelo;
				LocalDate dataNascimento, dataLicenca, dataFundacao;
				int anoFabricacao, totalVeiculos;
				switch (input) {
					case "1":
						System.out.println("Insira Nome, Endereco, Data da Licenca, Educacao, Genero, Classe Economica, CPF e Data de Nascimento do Cliente:");
						System.out.println("(Datas na forma AAAA-MM-DD)");
						nomeCliente = sc.nextLine();
						enderecoCliente = sc.nextLine();
						CPF = sc.nextLine();
						dataNascimento = LocalDate.parse(sc.nextLine());
						genero = sc.nextLine();
						educacao = sc.nextLine();
						classeEconomica = sc.nextLine();
						dataLicenca = LocalDate.parse(sc.nextLine());
						ClientePF novoClientePF = new ClientePF(nomeCliente, enderecoCliente, dataLicenca, educacao, genero, classeEconomica, CPF, dataNascimento);
						System.out.println("Insira o total de veiculos do cliente:");
						totalVeiculos = sc.nextInt(); 
						for (int i = 0; i < totalVeiculos; i++) {
							System.out.println("Insira a Placa, Marca, Modelo e Ano de fabricacao do veiculo " + (i + 1) + ":");
							placa = sc.nextLine();
							marca = sc.nextLine();
							modelo = sc.nextLine();
							anoFabricacao = sc.nextInt();
							novoClientePF.cadastrarVeiculo(new Veiculo(placa, marca, modelo, anoFabricacao));
						}
						novaSeguradora.cadastrarCliente(novoClientePF);
						break;
					case "2":
						System.out.println("Insira Nome, Endereco, CNPJ e Data de fundacao do Cliente:");
						System.out.println("(Datas na forma AAAA-MM-DD)");
						nomeCliente = sc.nextLine();
						enderecoCliente = sc.nextLine();
						CNPJ = sc.nextLine();
						dataFundacao = LocalDate.parse(sc.nextLine());
						ClientePJ novoClientePJ = new ClientePJ(nomeCliente, enderecoCliente, CNPJ, dataFundacao);
						System.out.println("Insira o total de veiculos do cliente:");
						totalVeiculos = sc.nextInt(); 
						for (int i = 0; i < totalVeiculos; i++) {
							System.out.println("Insira a Placa, Marca, Modelo e Ano de fabricacao do veiculo " + (i + 1) + ":");
							placa = sc.nextLine();
							marca = sc.nextLine();
							modelo = sc.nextLine();
							anoFabricacao = sc.nextInt();
							novoClientePJ.cadastrarVeiculo(new Veiculo(placa, marca, modelo, anoFabricacao));
						}
						novaSeguradora.cadastrarCliente(novoClientePJ);
						break;
					default:
						return;
				}
				while (true) {
					System.out.println("Cliente cadastrado! Selecione as opcoes:");
					System.out.println("1 - Cadastrar Cliente");
					System.out.println("2 - Gerar Sinistro");
					System.out.println("3 - Listar clientes");
					System.out.println("4 - Listar Sinistros");
				}
			default:
				break;
		}
	}
}
