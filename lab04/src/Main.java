import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class Main {

	// Testa as funções de cada classe.
	public static void testarPrograma() {
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
	}

	// Faz a leitura de uma data, garantindo que a entrada do usuário é válida
	public static LocalDate lerLocalData(Scanner sc, String prompt) {
		LocalDate data = null;
		do {
			try {
				System.out.print(prompt);
				data = LocalDate.parse(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Formato inválido.");
			}
		} while (data == null);

		return data;
	}
	
	// Faz a leitura dos dados da seguradora a ser criada e retorna um objeto Seguradora
	public static Seguradora lerDadosSeguradora(Scanner sc) {
		System.out.println("Insira Nome, Telefone, E-mail e Endereco separados por 'Enter':");

		System.out.print("Nome: ");
		String nome = sc.nextLine();

		System.out.print("Endereço: ");
		String endereco = sc.nextLine();

		System.out.print("E-mail: ");
		String email = sc.nextLine();

		System.out.print("Telefone: ");
		String telefone = sc.nextLine();

		return new Seguradora(nome, telefone, email, endereco); 
	}

	// Lê os dados dos veículos de um cliente
	public static void lerDadosVeiculos(Scanner sc, Cliente cliente, int totalVeiculos) {
		for (int i = 0; i < totalVeiculos; i++) {
			int anoFabricacao = 0;
			System.out.println("Insira os dados do veiculo " + (i + 1) + ".");
			
			System.out.print("Placa: ");
			String placa = sc.nextLine();

			System.out.print("Marca: ");
			String marca = sc.nextLine();

			System.out.print("Modelo: ");
			String modelo = sc.nextLine();

			while (anoFabricacao == 0) {
				System.out.print("Ano de fabricação: ");
				try {
					anoFabricacao = Integer.parseInt(sc.nextLine());
				} catch (Exception e) {
					System.out.println("Número inválido.");
				}
			}
			
			cliente.cadastrarVeiculo(new Veiculo(placa, marca, modelo, anoFabricacao));
		}
	}

	// Lê os dados de um Cliente PJ ou PF e os cadastra em uma seguradora.
	public static void lerDadosCliente(Scanner sc, String tipoCliente, Seguradora seguradora) {
		String nome, endereco, educacao, genero, classeEconomica, CPF, CNPJ;
		LocalDate dataLicenca, dataNascimento, dataFundacao;
		Cliente cliente;
		int totalVeiculos = 0;
		switch (tipoCliente) {
			case "3":
				System.out.println("Insira os dados do Cliente.");

				System.out.print("Nome: ");
				nome = sc.nextLine();

				System.out.print("Endereço: ");
				endereco = sc.nextLine();

				dataLicenca = lerLocalData(sc, "Data da Licença (AAAA-MM-DD): ");

				System.out.print("Educação: ");
				educacao = sc.nextLine();

				System.out.print("Gênero: ");
				genero = sc.nextLine();

				System.out.print("Classse Econômica: ");
				classeEconomica = sc.nextLine();

				System.out.print("CPF: ");
				CPF = sc.nextLine();
				while (!ClientePF.validarCPF(CPF)) {
					System.out.println("CPF inválido, digite novamente.");
					System.out.print("CPF: ");
					CPF = sc.nextLine();
				}
				dataNascimento = lerLocalData(sc, "Data de Nascimento (AAAA-MM-DD):");

				cliente = new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, CPF, dataNascimento);
				break;
			case "4":
				System.out.println("Insira os dados do Cliente.");

				System.out.print("Nome: ");
				nome = sc.nextLine();

				System.out.print("Endereço: ");
				endereco = sc.nextLine();

				System.out.print("CNPJ: ");
				CNPJ = sc.nextLine();
				while (!ClientePJ.validarCNPJ(CNPJ)) {
					System.out.println("CNPJ inválido, digite novamente.");
					System.out.print("CNPJ: ");
					CNPJ = sc.nextLine();
				}

				dataFundacao = lerLocalData(sc, "Data de Fundação (AAAA-MM-DD): ");

				cliente = new ClientePJ(nome, endereco, CNPJ, dataFundacao);
				break;
			default:
				return;
		}

		while (totalVeiculos == 0) {
			System.out.print("Insira o total de veiculos do cliente: ");
			try {
				totalVeiculos = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Número inválido.");
			}
		}

		lerDadosVeiculos(sc, cliente, totalVeiculos);
		seguradora.cadastrarCliente(cliente);
		System.out.println("Cliente cadastrado!");
	}

	// Gera um sinistro com os dados informados.
	public static void lerDadosSinistro(Scanner sc, Seguradora seguradora) {
		Cliente cliente;

		System.out.print("Insira o nome do cliente: ");
		String nome = sc.nextLine();
		cliente = seguradora.listarClientes("").stream().filter(c -> c.getNome().equals(nome)).findFirst().orElse(null);
		if (cliente == null) {
			System.out.println("Cliente inválido.");
			return;
		}

		int placa = 0;
		while (true) {
			System.out.println("Selecione o veículo:");
			int i = 1;
			for (Veiculo v : cliente.listaVeiculos) {
				System.out.println(i + " - " + v.getPlaca());
				i++;
			}
			try {
				placa = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Número inválido.");
				continue;
			}
			if (placa > cliente.listaVeiculos.size() || placa <= 0) {
				System.out.println("Veículo inválido.");
				continue;
			}
			break;
		}

		seguradora.gerarSinistro(cliente, cliente.listaVeiculos.get(placa - 1));
		System.out.println("Sinistro gerado!");
	}

	// A main é meramente um menu de texto em loop. Através da variável input, nós recebemos a opção escolhida
	// e executamos a ação selecionada através de um switch case.
	public static void main(String[] args) {
		String input = new String();
		Scanner sc = new Scanner(System.in);
		Seguradora seguradora = null;
		int i;

		while (true) {
			System.out.println("### Programa Seguradora ###");
			System.out.println("Selecione uma das opcoes:");
			System.out.println("1 - Executar teste do programa");
			System.out.println("2 - Criar Seguradora");
			System.out.println("3 - Cadastrar Cliente Pessoa Física");
			System.out.println("4 - Cadastrar Cliente Pessoa Jurídica");
			System.out.println("5 - Gerar Sinistro");
			System.out.println("6 - Listar clientes");
			System.out.println("7 - Listar clientes pessoa física");
			System.out.println("8 - Listar clientes pessoa jurídica");
			System.out.println("9 - Listar Sinistros");
			System.out.println("10 - Remover Cliente");
			System.out.println("11 - Mostrar sinistros de algum cliente");
			
			System.out.println("Outras teclas - Sair");

			input = sc.nextLine();

			switch (input) {
				case "1":
					testarPrograma();
					break;
				case "2":
					if (seguradora != null) {
						System.out.println("Seguradora já foi criada!");
						break;
					}
					seguradora = lerDadosSeguradora(sc);
					System.out.println("Seguradora criada!");
					break;
				case "3":
				case "4":
					if (seguradora == null) {
						System.out.println("Não há seguradora!");
						break;
					}
					lerDadosCliente(sc, input, seguradora);
					break;
				case "5":
					if (seguradora == null) {
						System.out.println("Não há seguradora!");
						break;
					}
					if (seguradora.listarClientes("").isEmpty()) {
						System.out.println("Não há clientes!");
						break;
					}
					lerDadosSinistro(sc, seguradora);
					break;
				case "6":
				case "7":
				case "8":
					if (seguradora == null) {
						System.out.println("Não há seguradora!");
						break;
					}
					List<Cliente> listaClientes = seguradora.listarClientes(input);
					if (listaClientes.isEmpty()) {
						System.out.println("Não há clientes!");
						break;
					}
					
					i = 1;
					for (Cliente c : listaClientes) {
						System.out.println("### Cliente " + i + " ###");
						System.out.println(c);
						i++;
					}
					break;
				case "9":
					if (seguradora == null) {
						System.out.println("Não há seguradora!");
						break;
					}
					if (seguradora.listarClientes("").isEmpty()) {
						System.out.println("Não há clientes!");
						break;
					}
					List<Sinistro> listaSinistros = seguradora.listarSinistros();
					if (listaSinistros.isEmpty()) {
						System.out.println("Não há Sinistros!");
						break;
					}
					
					i = 1;
					for (Sinistro s : listaSinistros) {
						System.out.println("### Sinistro " + i + " ###");
						System.out.println(s);
						i++;
					}
					break;
				case "10":
					System.out.print("Insira o nome do cliente a ser removido:");
					input = sc.nextLine();
					if (seguradora.removerCliente(input)) {
						System.out.println("Cliente " + input + " removido.");
					} else {
						System.out.println("Cliente " + input + " não encontrado.");
					}
					break;
				case "11":
					System.out.print("Insira o nome do cliente: ");
					input = sc.nextLine();
					if (!seguradora.visualizarSinistro(input)) {
						System.out.println("Não há sinistros com cliente " + input + ".");
					}
					break;
				default:
					return;
			}	
		}
	}
}
