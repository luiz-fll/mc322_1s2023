import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String lerString(String prompt, Scanner sc) {
        System.out.print(prompt);
        String dado = sc.nextLine();

        return dado;
    }

    public static Seguradora seguradoraCadastrada = null;

    // Faz a leitura de uma data, garantindo que a entrada do usuário é válida
	public static LocalDate lerLocalData(Scanner sc, String prompt) {
        System.out.println(prompt);
		String dataString = sc.nextLine();
        LocalDate dataLocalDate = null;

        while (Validacao.validaData(dataString, dataLocalDate) == false) {
            System.out.println("Formato inválido.");
            System.out.println(prompt);
		    dataString = sc.nextLine();
        }

		return dataLocalDate;
	}
	
	// Faz a leitura dos dados da seguradora a ser criada e retorna um objeto Seguradora
	public static Seguradora lerDadosSeguradora(Scanner sc) {
		System.out.println("Insira Nome, Telefone, E-mail e Endereco separados por 'Enter':");

        String nome = lerString("Nome: ", sc);
		String endereco = lerString("Endereço: ", sc);

        String email = lerString("E-mail: ", sc);
        while (Validacao.validaEmail(email) == false) {
            System.out.println("Formato inválido.");
            email = lerString("E-mail: ", sc);
        }

		String telefone = lerString("Telefone: ", sc);
        while (Validacao.validaTelefone(telefone) == false) {
            System.out.println("Formato inválido.");
            System.out.print("Telefone: ");
		    telefone = sc.nextLine();
        }

		return new Seguradora(nome, telefone, email, endereco); 
	}

	// Lê os dados dos veículos de um cliente
	public static void lerDadosVeiculo(Scanner sc) {
        System.out.println("Insira os dados do veiculo");

        String clienteNome = lerString("Cliente proprietário:", sc); 
        Cliente cliente = seguradoraCadastrada.listarClientes("").stream().filter(c -> c.getNome().equals(clienteNome)).findFirst().orElse(null);
        if (cliente == null) {
            System.out.println("Cliente não existe.");
            return;
        }
			
		String placa = lerString("Placa: ", sc);
        while (Validacao.validaPlaca(placa) == false) {
            System.out.println("Formato inválido.");
		    placa = lerString("Placa: ", sc);
        }


        String marca = lerString("Marca: ", sc);
        String modelo = lerString("Modelo: ", sc);

        int anoFabricacaoInt = -1;
		String anoFabricacaoString = lerString("Ano de Fabriacação: ", sc);
        while (Validacao.validaAno(anoFabricacaoString, anoFabricacaoInt) == false) {
            System.out.println("Formato inválido.");
            anoFabricacaoString = lerString("Ano de Fabriacação: ", sc);
        }
        
        cliente.cadastrarVeiculo(new Veiculo(placa, marca, modelo, anoFabricacaoInt));
	}

	// Lê os dados de um Cliente PJ ou PF e os cadastra em uma seguradora.
	public static void lerDadosCliente(Scanner sc) {
		String nome, endereco, educacao, genero, classeEconomica, CPF, CNPJ, qtdeFuncionariosString, tipoCliente;
		LocalDate dataLicenca, dataNascimento, dataFundacao;
		Cliente cliente;
		int qtdeFuncionariosInt = -1;
        
        tipoCliente = lerString("Tipo do cliente (PJ ou PF): ", sc);

		switch (tipoCliente) {
			case "PF":
				System.out.println("Insira os dados do Cliente.");

                nome = lerString("Nome: ", sc);
                while (!Validacao.validaNome(nome)) {
					System.out.println("Nome inválido, digite novamente.");
					nome = lerString("Nome: ", sc);
				}

				endereco = lerString("Endereço: ", sc);
				dataLicenca = lerLocalData(sc, "Data da Licença (AAAA-MM-DD): ");
				educacao = lerString("Educação: ", sc);
				genero = lerString("Gênero: ", sc);
                classeEconomica = lerString("Classe Econômica: ", sc);

				CPF = lerString("CPF: ", sc);
				while (!Validacao.validaCPF(CPF)) {
					System.out.println("CPF inválido, digite novamente.");
					CPF = lerString("CPF: ", sc);
				}

				dataNascimento = lerLocalData(sc, "Data de Nascimento (AAAA-MM-DD):");

				cliente = new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, CPF, dataNascimento);
				break;
			case "PJ":
				System.out.println("Insira os dados do Cliente.");

				nome = lerString("Nome: ", sc);
				endereco = lerString("Endereço: ", sc);

				CNPJ = lerString("CNPJ: ", sc);
				while (!Validacao.validaCNPJ(CNPJ)) {
					System.out.println("CNPJ inválido, digite novamente.");
					CNPJ = lerString("CNPJ: ", sc);
				}

				dataFundacao = lerLocalData(sc, "Data de Fundação (AAAA-MM-DD): ");

                System.out.print("Quantidade de Funcionários: ");
				qtdeFuncionariosString = lerString("Quantidade de Funcionários: ", sc);
				while (!Validacao.validaQtdeFuncionarios(qtdeFuncionariosString, qtdeFuncionariosInt)) {
					System.out.println("Formato Inválido.");
					qtdeFuncionariosString = lerString("Quantidade de Funcionários: ", sc);
				}

				cliente = new ClientePJ(nome, endereco, CNPJ, dataFundacao, qtdeFuncionariosInt);
				break;
			default:
                System.out.println("Tipo inválido.");
				return;
		}
		seguradoraCadastrada.cadastrarCliente(cliente);
		System.out.println("Cliente cadastrado!");
	}

	// Gera um sinistro com os dados informados.
	public static void lerDadosSinistro(Scanner sc) {
		Cliente cliente;

		String nome = lerString("Nome do cliente:", sc);
		cliente = seguradoraCadastrada.listarClientes("").stream().filter(c -> c.getNome().equals(nome)).findFirst().orElse(null);
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
        LocalDate dataSinistro = lerLocalData(sc, "Data do sinistro (AAAA-MM-DD): ");
		seguradoraCadastrada.gerarSinistro(cliente, cliente.listaVeiculos.get(placa - 1), dataSinistro);
		System.out.println("Sinistro gerado!");
	}

    public static void MenuCadastrar(Scanner sc) {
        int input;

        while (true) {
            System.out.println("11 - Cadastrar Cliente PF/PJ");
            System.out.println("12 - Cadastrar Veiculo");
            System.out.println("13 - Cadastrar Seguradora");
            System.out.println("10 - Voltar");

            try {
                input = Integer.parseInt(sc.nextLine());
                switch (MenuOperacoes.getOperacao(input)) {
                    case CADASTRAR_CLIENTE:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        lerDadosCliente(sc);
                        break;
                    case CADASTRAR_VEICULO:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        if (seguradoraCadastrada.listarClientes("").size() == 0) {
                            System.out.println("Não há clientes!");
                            break;
                        }
                        lerDadosVeiculo(sc);
                        break;
                    case CADASTRAR_SEGURADORA:
                        seguradoraCadastrada = lerDadosSeguradora(sc);
                        break;
                    case VOLTAR:
                        return;
                    default:
						System.out.println("Insira um número válido!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Insira um número válido!");
            }
        }
    }

    public static void MenuListar(Scanner sc) {
        int input;
        String nomeCliente;

        while (true) {
            System.out.println("21 - Listar Cliente (PF/PJ) por Seg.");
            System.out.println("22 - Listar Sinistros por Seguradora");
            System.out.println("23 - Listar Sinistro por Cliente");
            System.out.println("24 - Listar Veiculo por Cliente");
            System.out.println("25 - Listar Veiculo por Seguradora");
            System.out.println("10 - Voltar");

            try {
                input = Integer.parseInt(sc.nextLine());
                switch (MenuOperacoes.getOperacao(input)) {
                    case LISTAR_CLIENTES_POR_SEGURADORA:
                        System.out.print("Tipo do cliente (PJ ou PJ ou ambos): ");
                        String tipoCliente = sc.nextLine();
                        seguradoraCadastrada.visualizarClientes(tipoCliente);
                        break;
                    case LISTAR_SINISTROS_POR_SEGURADORA:
                        for (Cliente cliente : seguradoraCadastrada.listarClientes("")) {
                            System.out.println("Cliente " + cliente.nome + ":");
                            seguradoraCadastrada.visualizarSinistro(cliente.nome);
                        }
                        break;
                    case LISTAR_SINISTROS_POR_CLIENTE:
                        System.out.print("Insira o nome do cliente: ");
                        nomeCliente = sc.nextLine();
                        seguradoraCadastrada.visualizarSinistro(nomeCliente);
                        break;
                    case LISTAR_VEICULOS_POR_CLIENTE:
                        System.out.print("Insira o nome do cliente: ");
                        nomeCliente = sc.nextLine();
                        seguradoraCadastrada.visualizarVeiculo(nomeCliente);
                        break;
                    case LISTAR_VEICULOS_POR_SEGURADORA:
                        seguradoraCadastrada.visualizarVeiculo();
                        break;
                    case VOLTAR:
                        return;
                    default:
						System.out.println("Insira um número válido!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Insira um número válido!");
            }
        }
    }

    public static void MenuExcluir(Scanner sc) {
        int input, indiceSinistro, indiceVeiculo;
        String nomeCliente;

        if (seguradoraCadastrada == null) {
            System.out.println("Não há seguradora!");
            return;
        }

        while (true) {
            System.out.println("31 - Excluir Cliente");
            System.out.println("32 - Excluir Veiculo");
            System.out.println("33 - Excluir Sinistro");
            System.out.println("10 - Voltar");

            try {
                input = Integer.parseInt(sc.nextLine());
                switch (MenuOperacoes.getOperacao(input)) {
                    case EXCLUIR_CLIENTE:
                        System.out.print("Insira o nome do cliente: ");
                        nomeCliente = sc.nextLine();
                        seguradoraCadastrada.removerCliente(nomeCliente);
                        break;
                    case EXCLUIR_VEICULO:
                        System.out.print("Insira o nome do cliente: ");
                        Cliente cliente = seguradoraCadastrada.listarClientes("").stream().filter(c -> c.getNome().equals(sc.nextLine())).findFirst().orElse(null);
                        if (cliente == null) {
                            System.out.println("Cliente inválido.");
                            break;
                        }
                        System.out.print("Selecione o número do sinistro: ");
                        seguradoraCadastrada.visualizarSinistro(cliente.getNome());
                        try {
                            indiceVeiculo = Integer.parseInt(sc.nextLine()) - 1;
                        } catch (Exception e) {
                            System.out.println("Valor inválido.");
                            break;
                        }
                        seguradoraCadastrada.removerVeiculo(cliente, indiceVeiculo);
                        break;
                    case EXCLUIR_SINISTRO:
                        System.out.print("Selecione o número do sinistro: ");
                        seguradoraCadastrada.visualizarSinistro();

                        try {
                            indiceSinistro = Integer.parseInt(sc.nextLine()) - 1;
                        } catch (Exception e) {
                            System.out.println("Valor inválido.");
                            break;
                        }
                        seguradoraCadastrada.removerSinistro(indiceSinistro);
                        break;
                    case VOLTAR:
                        return;
                    default:
						System.out.println("Insira um número válido!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Insira um número válido!");
            }
        }
    }

    public static void menuPrincipal(Scanner sc) {
        int input;

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
                        MenuCadastrar(sc);
                        break;
                    case LISTAR:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        if (seguradoraCadastrada.listarClientes("").size() == 0) {
                            System.out.println("Não há clientes!");
                            break;
                        }
                        MenuListar(sc);
                        break;
                    case EXCLUIR:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        if (seguradoraCadastrada.listarClientes("").size() == 0) {
                            System.out.println("Não há clientes!");
                            break;
                        }
                        MenuExcluir(sc);
                        break;
                    case GERAR_SINISTRO:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        if (seguradoraCadastrada.listarClientes("").size() == 0) {
                            System.out.println("Não há clientes!");
                            break;
                        }
                        lerDadosSinistro(sc);
                        break;
                    case TRANSFERIR_SEGURO:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        if (seguradoraCadastrada.listarClientes("").size() < 2) {
                            System.out.println("Não há clientes o suficiente!");
                            break;
                        }
                        System.out.print("Insira o nome do cliente com os veículos: ");
                        Cliente clienteVeiculos = seguradoraCadastrada.listarClientes("").stream().filter(c -> c.getNome().equals(sc.nextLine())).findFirst().orElse(null);
                        if (clienteVeiculos == null) {
                            System.out.println("Cliente inválido.");
                            break;
                        }
                        System.out.print("Insira o nome do cliente da transferência: ");
                        Cliente clienteTransferencia = seguradoraCadastrada.listarClientes("").stream().filter(c -> c.getNome().equals(sc.nextLine())).findFirst().orElse(null);
                        if (clienteTransferencia == null) {
                            System.out.println("Cliente inválido.");
                            break;
                        }
                        List<Veiculo> veiculos = clienteTransferencia.getListaVeiculos();
                        for (Veiculo veiculo : clienteVeiculos.listaVeiculos) {
                            veiculos.add(veiculo);
                        }
                        clienteVeiculos.setListaVeiculos(null);
                        break;
                    case CALCULAR_RECEITA_SEGURADORA:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        try {
                            System.out.println("Receita: R$ " + seguradoraCadastrada.calcularReceita());    
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        
                        break;
                    case SAIR:
                        return;
                    default:
						System.out.println("Insira um número válido!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Insira um número válido!");
            }
        }
    }

    public static void testarPrograma() {
        Seguradora seguradoraTeste = new Seguradora("TestSeguros", "(11)91111-2222", "email@example.com", "Lugar");
        ClientePF fisica = new ClientePF("pf", "aqui", LocalDate.of(2020, 12, 3), "pouca", "a", "r", "018.730.580-39", LocalDate.of(2000, 10, 10));
        ClientePJ juridica = new ClientePJ("pj", "lá", "20.868.879/0001-03", LocalDate.of(2002, 10, 10), 30);

        seguradoraTeste.cadastrarCliente(juridica);
        seguradoraTeste.cadastrarCliente(fisica);

        Veiculo hyundai = new Veiculo("AAA-1234", "hyundai", "i30", 2017);
        Veiculo honda = new Veiculo("ZZZ-9876", "Honda", "Civic", 2018);

        fisica.cadastrarVeiculo(hyundai);
        juridica.cadastrarVeiculo(honda);
        
        Sinistro sinistroFisica = new Sinistro(LocalDate.of(2022, 10, 12), "ali", seguradoraTeste, fisica, hyundai);
        Sinistro sinistroJuridica = new Sinistro(LocalDate.of(2022, 10, 12), "ali", seguradoraTeste, juridica, honda);

        seguradoraTeste.listarClientes("");
        seguradoraTeste.visualizarSinistro();
        seguradoraTeste.visualizarSinistro(fisica.getNome());
        seguradoraTeste.listarSinistros();
        
        System.out.println(seguradoraTeste.calcularReceita());
        seguradoraTeste.calcularPrecoSeguroCliente(juridica);
        seguradoraTeste.calcularPrecoSeguroCliente(fisica);
        
        
    }
	public static void main(String args[]) {
        testarPrograma();
        Scanner sc = new Scanner(System.in);
        menuPrincipal(sc);
		sc.close();
	}
}
