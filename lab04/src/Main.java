import java.time.LocalDate;
import java.util.Scanner;

public class Main {
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

		System.out.print("Nome: ");
		String nome = sc.nextLine();

		System.out.print("Endereço: ");
		String endereco = sc.nextLine();

		System.out.print("E-mail: ");
		String email = sc.nextLine();
        while (Validacao.validaEmail(email) == false) {
            System.out.println("Formato inválido.");
            System.out.print("E-mail: ");
		    email = sc.nextLine();
        }

		System.out.print("Telefone: ");
		String telefone = sc.nextLine();
        while (Validacao.validaTelefone(telefone) == false) {
            System.out.println("Formato inválido.");
            System.out.print("E-mail: ");
		    telefone = sc.nextLine();
        }

		return new Seguradora(nome, telefone, email, endereco); 
	}

	// Lê os dados dos veículos de um cliente
	public static void lerDadosVeiculo(Scanner sc, Seguradora seguradoraCadastrada) {
        System.out.println("Insira os dados do veiculo");

        System.out.print("Cliente proprietário: ");
        String clienteNome = sc.nextLine();
        Cliente cliente = seguradoraCadastrada.listarClientes("").stream().filter(c -> c.getNome().equals(clienteNome)).findFirst().orElse(null);
        if (cliente == null) {
            System.out.println("Cliente não existe.");
            return;
        }
			
		System.out.print("Placa: ");
		String placa = sc.nextLine();
        while (Validacao.validaPlaca(placa) == false) {
            System.out.println("Formato inválido.");
            System.out.print("Placa: ");
		    placa = sc.nextLine();
        }

		System.out.print("Marca: ");
		String marca = sc.nextLine();

		System.out.print("Modelo: ");
		String modelo = sc.nextLine();

        int anoFabricacaoInt = -1;
        System.out.print("Ano de Fabriacação: ");
		String anoFabricacaoString = sc.nextLine();
        while (Validacao.validaAno(anoFabricacaoString, anoFabricacaoInt) == false) {
            System.out.println("Formato inválido.");
            System.out.print("Ano de Fabricação: ");
		    anoFabricacaoString = sc.nextLine();
        }
        
        cliente.cadastrarVeiculo(new Veiculo(placa, marca, modelo, anoFabricacaoInt));
	}

	// Lê os dados de um Cliente PJ ou PF e os cadastra em uma seguradora.
	public static void lerDadosCliente(Scanner sc, Seguradora seguradora) {
		String nome, endereco, educacao, genero, classeEconomica, CPF, CNPJ, qtdeFuncionariosString, tipoCliente;
		LocalDate dataLicenca, dataNascimento, dataFundacao;
		Cliente cliente;
		int qtdeFuncionariosInt = -1;
        
        System.out.print("Tipo do cliente (PJ ou PF): ");
		tipoCliente = sc.nextLine();

		switch (tipoCliente) {
			case "PF":
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
				while (!Validacao.validaCPF(CPF)) {
					System.out.println("CPF inválido, digite novamente.");
					System.out.print("CPF: ");
					CPF = sc.nextLine();
				}

				dataNascimento = lerLocalData(sc, "Data de Nascimento (AAAA-MM-DD):");

				cliente = new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, CPF, dataNascimento);
				break;
			case "PJ":
				System.out.println("Insira os dados do Cliente.");

				System.out.print("Nome: ");
				nome = sc.nextLine();

				System.out.print("Endereço: ");
				endereco = sc.nextLine();

				System.out.print("CNPJ: ");
				CNPJ = sc.nextLine();
				while (!Validacao.validaCNPJ(CNPJ)) {
					System.out.println("CNPJ inválido, digite novamente.");
					System.out.print("CNPJ: ");
					CNPJ = sc.nextLine();
				}

				dataFundacao = lerLocalData(sc, "Data de Fundação (AAAA-MM-DD): ");

                System.out.print("Quantidade de Funcionários: ");
				qtdeFuncionariosString = sc.nextLine();
				while (!Validacao.validaQtdeFuncionarios(qtdeFuncionariosString, qtdeFuncionariosInt)) {
					System.out.println("Formato Inválido.");
					System.out.print("Quantidade de Funcionários: ");
					qtdeFuncionariosString = sc.nextLine();
				}

				cliente = new ClientePJ(nome, endereco, CNPJ, dataFundacao, qtdeFuncionariosInt);
				break;
			default:
                System.out.println("Tipo inválido.");
				return;
		}
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
        LocalDate dataSinistro = lerLocalData(sc, "Data do sinistro (AAAA-MM-DD): ");
		seguradora.gerarSinistro(cliente, cliente.listaVeiculos.get(placa - 1), dataSinistro);
		System.out.println("Sinistro gerado!");
	}

    public static void MenuCadastrar(Seguradora seguradoraCadastrada, Scanner sc) {
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
                        lerDadosCliente(sc, seguradoraCadastrada);
                        break;
                    case CADASTRAR_VEICULO:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        lerDadosVeiculo(sc, seguradoraCadastrada);
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

    public static void MenuListar(Seguradora seguradoraCadastrada, Scanner sc) {
        int input;

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
                        seguradoraCadastrada.listarClientes(tipoCliente);
                        break;
                    case LISTAR_SINISTROS_POR_SEGURADORA:
                        seguradoraCadastrada.listarSinistros();
                        break;
                    case LISTAR_SINISTROS_POR_CLIENTE:
                        // chato
                        break;
                    case LISTAR_VEICULOS_POR_CLIENTE:
                        // chato
                        break;
                    case LISTAR_VEICULOS_POR_SEGURADORA:
                        // chato
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

    public static void MenuExcluir(Seguradora seguradoraCadastrada, Scanner sc) {
        int input;
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
                        break;
                    case EXCLUIR_VEICULO:
                        break;
                    case EXCLUIR_SINISTRO:
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

    public static void menuPrincipal(Seguradora seguradoraCadastrada, Scanner sc) {
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
                        MenuCadastrar(seguradoraCadastrada, sc);
                        break;
                    case LISTAR:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        MenuListar(seguradoraCadastrada, sc);
                        break;
                    case EXCLUIR:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        MenuExcluir(seguradoraCadastrada, sc);
                        break;
                    case GERAR_SINISTRO:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        lerDadosSinistro(sc, seguradoraCadastrada);
                        break;
                    case TRANSFERIR_SEGURO:
                        break;
                    case CALCULAR_RECEITA_SEGURADORA:
                        if (seguradoraCadastrada == null) {
                            System.out.println("Não há seguradora!");
                            break;
                        }
                        System.out.println("Receita: R$ " + seguradoraCadastrada.calcularReceita());
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
	public static void main(String args[]) {
		Seguradora seguradoraCadastrada = null;
        Scanner sc = new Scanner(System.in);
        menuPrincipal(seguradoraCadastrada, sc);
		sc.close();
	}
}
