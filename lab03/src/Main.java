import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class Main {

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
	
	public static Seguradora lerDadosSeguradora(Scanner sc) {
		System.out.println("Insira Nome, Telefone, E-mail e Endereco separados por 'Enter':");
		String nome = sc.nextLine();
		String telefone = sc.nextLine();
		String email = sc.nextLine();
		String endereco = sc.nextLine();

		return new Seguradora(nome, telefone, email, endereco); 
	}

	public static void lerDadosVeiculos(Scanner sc, Cliente cliente, int totalVeiculos) {
		for (int i = 0; i < totalVeiculos; i++) {
			System.out.println("Insira a Placa, Marca, Modelo e Ano de fabricacao do veiculo " + (i + 1) + ":");
			String placa = sc.nextLine();
			String marca = sc.nextLine();
			String modelo = sc.nextLine();
			int anoFabricacao = Integer.parseInt(sc.nextLine());
			cliente.cadastrarVeiculo(new Veiculo(placa, marca, modelo, anoFabricacao));
		}
	}

	public static Cliente lerDadosCliente(Scanner sc, String tipoCliente) {
		String nome, endereco, educacao, genero, classeEconomica, CPF, CNPJ;
		LocalDate dataLicenca, dataNascimento, dataFundacao;
		Cliente cliente;
		switch (tipoCliente) {
			case "3":
				System.out.println("Insira Nome, Endereco, Data da Licenca, Educacao, Genero, Classe Economica, CPF e Data de Nascimento do Cliente:");
				System.out.println("(Datas na forma AAAA-MM-DD)");
				nome = sc.nextLine();
				endereco = sc.nextLine();
				dataLicenca = LocalDate.parse(sc.nextLine());
				educacao = sc.nextLine();
				genero = sc.nextLine();
				classeEconomica = sc.nextLine();
				CPF = sc.nextLine();
				dataNascimento = LocalDate.parse(sc.nextLine());
				cliente = new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, CPF, dataNascimento);
				break;
			case "4":
				System.out.println("Insira Nome, Endereco, CNPJ e Data de fundacao do Cliente:");
				System.out.println("(Datas na forma AAAA-MM-DD)");
				nome = sc.nextLine();
				endereco = sc.nextLine();
				CNPJ = sc.nextLine();
				dataFundacao = LocalDate.parse(sc.nextLine());
				cliente = new ClientePJ(nome, endereco, CNPJ, dataFundacao);
				break;
			default:
				return null;
		}
		System.out.println("Insira o total de veiculos do cliente:");
		int totalVeiculos = Integer.parseInt(sc.nextLine());
		lerDadosVeiculos(sc, cliente, totalVeiculos);

		return cliente;
	}

	public static void menuFinalLoop(Seguradora seguradora, Scanner sc, String input) {
		while (true) {
			System.out.println("1 - Cadastrar Cliente");
			System.out.println("2 - Gerar Sinistro");
			System.out.println("3 - Listar clientes");
			System.out.println("4 - Listar Sinistros");
			System.out.println("Outras teclas - Sair");

			input = sc.nextLine();

			switch (input) {
				case "1":
					System.out.println("Selecione as opcoes:");
					System.out.println("1 - Cadastrar Cliente Pessoa Física");
					System.out.println("2 - Cadastrar Cliente Pessoa Jurídica");
					System.out.println("Outras teclas - Sair");

					input = sc.nextLine();

					Cliente cliente = lerDadosCliente(sc, input);
					if (cliente == null) {
						break;
					}
					seguradora.cadastrarCliente(cliente);
					System.out.println("Cliente cadastrado! Selecione as opcoes:");
					break;
				case "2":
					System.out.println("kek");
					break;
				case "3":
					System.out.println("lul");
					break;
				case "4":
					System.out.println("pog");
					break;
				default:
					return;
			}
		}
	}
	public static void main(String[] args) {
		String input = new String();
		Scanner sc = new Scanner(System.in);
		Seguradora seguradora = null;

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

					Cliente cliente = lerDadosCliente(sc, input);
					seguradora.cadastrarCliente(cliente);
					System.out.println("Cliente cadastrado!");
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
					for (Cliente c : listaClientes) {
						System.out.println(c);
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
					for (Sinistro s : listaSinistros) {
						System.out.println(s);
					}
					break;
				case "10":
					break;
				case "11":
					break;
				default:
					break;
			}	
		}
	}
}
