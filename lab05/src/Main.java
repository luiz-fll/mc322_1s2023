import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.InputMismatchException;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;

public class Main {
	/*
	 * Está muito difícil navegar neste arquivo. Abaixo temos a main, que só escolhe que tipo de teste executar.
	 * Depois temos uma série de funções relacionadas ao menu interativo:
	 * -> Primeiro vem as funções de cadastro, geração de sinistros, criação de classes, etc.
	 * -> Depois vem as funções de remoção, desautorização...
	 * -> Depois vem as funções do menu de seleção para escolher o objeto de uma operação
	 * -> Enfim, temos os paineis, que é onde escolhemos as operações a executar
	 * A última função é o teste de classe, onde eu instancio um monte de coisa conforme orientado no lab05.
	 * 
	 * Leia o README para ver uma explicação sobre o menu interativo.
	 */
	public static void main(String args[]) 
	throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("1 - Executar teste de classes");
		System.out.println("2 - Executar teste automatizado do menu interativo");
		System.out.println("3 - Executar menu interativo");
		System.out.println("Demais teclas - sair");
		System.out.println(new File(".").getAbsolutePath());
		String input = sc.nextLine();

		switch (input) {
			case "1":
				sc.close();
				testeClasses();
				break;
			case "2":
				sc.close();
				executarMenuInterativo(new Scanner(new FileInputStream("auto.txt")));
			break;
			case "3":
				executarMenuInterativo(sc);
				sc.close();
				break;
			default:
				sc.close();
				break;
		}
	}

	public static ClientePF criarClientePF(Scanner sc, Seguradora seguradora) 
	throws InputMismatchException {
		System.out.println("Insira os dados do Cliente...");
		String cpf = Leitura.lerCPF(sc);
		String nome = Leitura.lerNome(sc);
		String telefone = Leitura.lerTelefone(sc);
		String endereco = Leitura.lerString(sc, "Endereço");
		String genero = Leitura.lerString(sc, "Gênero");
		String email = Leitura.lerEmail(sc);
		String educacao = Leitura.lerString(sc, "Educação");
		LocalDate dataNasc = Leitura.lerData(sc, "Data de Nascimento");

		ClientePF c = new ClientePF(nome, telefone, endereco, email, cpf, genero, educacao, dataNasc);
		seguradora.cadastrarCliente(c);

		return c;
	}
	
	public static ClientePJ criarClientePJ(Scanner sc, Seguradora seguradora) 
	throws InputMismatchException {
		System.out.println("Insira os dados do Cliente...");
		String cnpj = Leitura.lerCNPJ(sc);
		String nome = Leitura.lerNome(sc);
		String telefone = Leitura.lerTelefone(sc);
		String endereco = Leitura.lerString(sc, "Endereço");
		String email = Leitura.lerEmail(sc);
		LocalDate dataFund = Leitura.lerData(sc, "Data de Fundação");
		
		ClientePJ c = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFund);
		seguradora.cadastrarCliente(c);

		return c;
	}
	
	public static SeguroPF criarSeguroPF(Scanner sc, Seguradora seguradora, ClientePF cliente) 
	throws InputMismatchException, NameNotFoundException {
		System.out.println("Insira os dados do Seguro...");
		LocalDate dataInicio = Leitura.lerData(sc, "Data de Início");
		LocalDate dataFim = Leitura.lerData(sc, "Data de Fim");
		Veiculo veiculo = cliente.procurarVeiculo(Leitura.lerPlaca(sc));

		SeguroPF s = new SeguroPF(dataInicio, dataFim, seguradora, veiculo, cliente);
		return s;
	}

	public static SeguroPJ criarSeguroPJ(Scanner sc, Seguradora seguradora, ClientePJ cliente) 
	throws InputMismatchException, NameNotFoundException {
		System.out.println("Insira os dados do Seguro...");
		LocalDate dataInicio = Leitura.lerData(sc, "Data de Início");
		LocalDate dataFim = Leitura.lerData(sc, "Data de Fim");
		Frota frota = cliente.procurarFrota(Leitura.lerString(sc, "Code"));

		SeguroPJ s = new SeguroPJ(dataInicio, dataFim, seguradora, frota, cliente);
		
		return s;
	}

	public static void cadastrarVeiculo(Scanner sc, ClientePF cliente) 
	throws InputMismatchException, NameAlreadyBoundException {
		System.out.println("Insira os dados do Veículo...");
            try {
                String placa = Leitura.lerPlaca(sc);
                String marca = Leitura.lerString(sc, "Marca");
                String modelo = Leitura.lerString(sc, "Modelo");
                int anoFabricacao = Leitura.lerInteiro(sc, "Ano de Fabricação");
				cliente.cadastrarVeiculo(placa, marca, modelo, anoFabricacao);
            } catch (NumberFormatException e) {
                throw new InputMismatchException(e.getMessage());
            }
	}

	public static void cadastrarVeiculo(Scanner sc, Frota frota) 
	throws InputMismatchException, NameAlreadyBoundException {
		System.out.println("Insira os dados do Veículo...");
            try {
                String placa = Leitura.lerPlaca(sc);
                String marca = Leitura.lerString(sc, "Marca");
                String modelo = Leitura.lerString(sc, "Modelo");
                int anoFabricacao = Leitura.lerInteiro(sc, "Ano de Fabricação");
				frota.addVeiculo(placa, marca, modelo, anoFabricacao);
            } catch (NumberFormatException e) {
                throw new InputMismatchException(e.getMessage());
            }
	}
	
	public static void cadastrarFrota(Scanner sc, ClientePJ cliente) 
	throws InputMismatchException, NameAlreadyBoundException {
		String code = Leitura.lerString(sc, "Code da frota");
		cliente.cadastrarFrota(new Frota(code));
	}

	public static void autorizarCondutor(Scanner sc, Seguro seguro) 
	throws InputMismatchException, NameAlreadyBoundException {
		String CPF = Leitura.lerCPF(sc);
		String nome = Leitura.lerNome(sc);
		String telefone = Leitura.lerTelefone(sc);
		String endereco = Leitura.lerString(sc, "Endereco");
		String email = Leitura.lerEmail(sc);
		LocalDate DataNasc = Leitura.lerData(sc, "Data de Nascimento");
		LocalDate ValidadeCNH = Leitura.lerData(sc, "Data de Validade CNH");

		if (ValidadeCNH.isBefore(LocalDate.now())) {
			System.out.println("Condutor não autorizado: CNH está vencida");
			return;
		}

		Condutor c = new Condutor(CPF, nome, telefone, endereco, email, DataNasc, ValidadeCNH);
		seguro.autorizarCondutor(c);
	}

	public static Seguradora criarSeguradora(Scanner sc, ArrayList<Seguradora> seguradoras) 
	throws InputMismatchException {
		System.out.println("Insira os dados da Seguradora...");
		String cnpj = Leitura.lerCNPJ(sc);
		String nome = Leitura.lerString(sc, "Nome");
		String telefone = Leitura.lerTelefone(sc);
		String endereco = Leitura.lerString(sc, "Endereço");
		String email = Leitura.lerEmail(sc);

		Seguradora s = new Seguradora(cnpj, nome, telefone, email, endereco);
		seguradoras.add(s);

		return s;
	}

	public static void gerarSinistro(Scanner sc, Seguro seguro) 
	throws NameNotFoundException, InputMismatchException, IndexOutOfBoundsException {
		Menu painel = Menu.selecaoCondutores(seguro, "Selecione o condutor responsável");
		painel.mostrar();
		Opcao opcaoSelecionada = painel.selecionarOpcao(sc);
		for (Opcao opcao : painel.getOpcoes()) {
			if (opcao == opcaoSelecionada) {
				Condutor condutor = seguro.getListaCondutores().get(opcao.getCodigo() - 1);
				LocalDate data = Leitura.lerData(sc, "Data");
				String endereco = Leitura.lerString(sc, "Endereço");
				seguro.gerarSinistro(data, endereco, condutor.getCPF());
				return;
			}
		}
	}
	
	public static void removerVeiculo(Scanner sc, ClientePF cliente) {
		System.out.print("Placa do veículo: ");
		String placa = sc.nextLine();
		if (cliente.removerVeiculo(placa)) {
			System.out.println(placa + "removido com sucesso.");
		} else {
			System.out.println("Não foi possível remover " + placa);
		}
	}

	public static void removerFrota(Scanner sc, ClientePJ cliente) {
		System.out.print("Code da frota: ");
		String code = sc.nextLine();
		try {
			cliente.atualizarFrota(code);
		} catch (NameNotFoundException e) {
			System.out.println(code + "Não encontrada");
		}
	}
	
	public static void desautorizarCondutor(Scanner sc, Seguro seguro) 
	throws IndexOutOfBoundsException {
		Menu painel = Menu.selecaoCondutores(seguro, "Selecione o condutor a ser desautorizado");
		painel.mostrar();
		Opcao opcaoSelecionada = painel.selecionarOpcao(sc);
		for (Opcao opcao : painel.getOpcoes()) {
			if (opcao == opcaoSelecionada) {
				seguro.desautorizarCondutor(seguro.getListaCondutores().get(opcao.getCodigo() - 1));
				return;
			}
		}
	}

	public static Seguro selecionarSeguro(Scanner sc, Seguradora seguradora, Cliente cliente) 
	throws IndexOutOfBoundsException {
		ArrayList<Seguro> segurosDoCliente = seguradora.getSegurosPorCliente(cliente);
		Menu menu = Menu.selecaoSeguro(segurosDoCliente);
		menu.mostrar();
		Opcao opcaoSelecionada = menu.selecionarOpcao(sc);
		for (Opcao opcao : menu.getOpcoes()) {
			if (opcao == opcaoSelecionada) {
				return segurosDoCliente.get(opcao.getCodigo() - 1);
			}
		}
		throw new IndexOutOfBoundsException();
	}

	public static Frota selecionarFrota(Scanner sc, ClientePJ cliente) 
	throws IndexOutOfBoundsException {
		Menu painel = Menu.selecaoFrota(cliente, "Selecione a frota");
		painel.mostrar();
		Opcao opcaoSelecionada = painel.selecionarOpcao(sc);
		for (Opcao opcao : painel.getOpcoes()) {
			if (opcao == opcaoSelecionada) {
				Frota frota = cliente.getListaFrota().get(opcao.getCodigo() - 1);
				return frota;
			}
		}

		throw new IndexOutOfBoundsException();
	}

	public static Condutor selecionarCondutor(Scanner sc, Seguro seguro) 
	throws IndexOutOfBoundsException {
		Menu painel = Menu.selecaoCondutores(seguro, "Selecione o condutor");
		painel.mostrar();
		Opcao opcaoSelecionada = painel.selecionarOpcao(sc);
		for (Opcao opcao : painel.getOpcoes()) {
			if (opcao == opcaoSelecionada) {
				Condutor c = seguro.getListaCondutores().get(opcao.getCodigo() - 1);
				return c;
			}
		}
		throw new IndexOutOfBoundsException();
	}

	public static void abrirPainelFrota(Scanner sc, Frota frota) {
		Menu painel;
		Opcao opcaoSelecionada;
		while (true) {
			painel = Menu.painelFrota(frota);
			painel.mostrar();
			opcaoSelecionada = painel.selecionarOpcao(sc);
			switch(opcaoSelecionada.getOperacao()) {
				case REMOVER_VEICULO:
					frota.removeVeiculo(frota.getListaVeiculos().get(opcaoSelecionada.getCodigo() - 1));
					break;
				case CADASTRAR_VEICULO:
					try {
						cadastrarVeiculo(sc, frota);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					return;
			}
		}

	}

	public static void abrirPainelCondutores(Scanner sc, Seguro seguro) {
		Menu painel, sinistros;
		Opcao opcaoSelecionada;
		while (true) {
			painel = Menu.PainelCondutor(seguro);
			painel.mostrar();
			opcaoSelecionada = painel.selecionarOpcao(sc);
			switch(opcaoSelecionada.getOperacao()) {
				case AUTORIZAR:
					try {
						autorizarCondutor(sc, seguro);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case DESAUTORIZAR:
				try {
					desautorizarCondutor(sc, seguro);
				} catch (IndexOutOfBoundsException e) {
					
				}
					break;
				case PAINEL_SINISTRO:
					try {
						Condutor c = selecionarCondutor(sc, seguro);
						sinistros = Menu.painelSinistro(c);
						sinistros.mostrar();
						sc.nextLine();
					} catch (IndexOutOfBoundsException e) {
						
					}
					break;
				default:
					return;
			}
		}
	}

	public static void abrirPainelSeguro(Scanner sc, Seguro seguro) {
		Menu painel, sinistros;
		Opcao opcaoSelecionada;
		while (true) {
			painel = Menu.painelSeguro(seguro);
			painel.mostrar();
			opcaoSelecionada = painel.selecionarOpcao(sc);
			switch(opcaoSelecionada.getOperacao()) {
				case PAINEL_CONDUTOR:
					abrirPainelCondutores(sc, seguro);
					break;
				case PAINEL_SINISTRO:
					sinistros = Menu.painelSinistro(seguro);
					sinistros.mostrar();
					break;
				case GERAR_SINISTRO:
					try {
						gerarSinistro(sc, seguro);
					} catch (IndexOutOfBoundsException e) {
						
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					return;
			}
		}
	}

	public static void abrirPainelCliente(Scanner sc, Seguradora seguradora, String identificacao) 
	throws InputMismatchException {
		Menu painel, sinistros;
		Opcao opcaoSelecionada;
		Cliente cliente;
		try {
			while (true) {
				if (Validacao.validaCPF(identificacao)) {
					cliente = seguradora.procurarClientePF(identificacao);
					painel = Menu.painelClientePF(seguradora, (ClientePF)cliente);
				}
				else if (Validacao.validaCNPJ(identificacao)) {
					cliente = seguradora.procurarClientePJ(identificacao);
					painel = Menu.painelClientePJ(seguradora, (ClientePJ)cliente);
				}
				else {
					throw new InputMismatchException("Entrada inválida: " + identificacao);
				}
				painel.mostrar();
				opcaoSelecionada = painel.selecionarOpcao(sc);
				switch (opcaoSelecionada.getOperacao()) {
					case CADASTRAR_VEICULO:
						try {
							cadastrarVeiculo(sc, (ClientePF)cliente);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case CADASTRAR_FROTA:
						try {
							cadastrarFrota(sc, (ClientePJ)cliente);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case REMOVER_VEICULO:
						removerVeiculo(sc, (ClientePF)cliente);
						break;
					case REMOVER_FROTA:
						removerFrota(sc, (ClientePJ)cliente);
						break;
					case CRIAR_SEGURO_PF:
						try {
							SeguroPF s = criarSeguroPF(sc, seguradora, (ClientePF)cliente);
							abrirPainelSeguro(sc, s);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case CRIAR_SEGURO_PJ:
						try {
							SeguroPJ s = criarSeguroPJ(sc, seguradora, (ClientePJ)cliente);
							abrirPainelSeguro(sc, s);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case PAINEL_SEGURO:
						try {
							Seguro s = selecionarSeguro(sc, seguradora, cliente);
							abrirPainelSeguro(sc, s);
						} catch (IndexOutOfBoundsException e) {
							
						}
						break;
					case PAINEL_SINISTRO:
						sinistros = Menu.painelSinistro(cliente, seguradora);
						sinistros.mostrar();
						break;
					case ALTERAR_FROTA:
						Frota f = selecionarFrota(sc, (ClientePJ)cliente);
						abrirPainelFrota(sc, f);
						break;
					case REMOVER_CLIENTE:
						seguradora.removerCliente(cliente);
						return;
					default:
						return;
				}
			}
		} catch (NameNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	public static void abrirPainelSeguradora(Scanner sc, Seguradora seguradora) {
		Menu painel;
		Opcao opcaoSelecionada;
		while (true) {
			painel = Menu.painelSeguradora(seguradora);
			painel.mostrar();
			opcaoSelecionada = painel.selecionarOpcao(sc);
			switch (opcaoSelecionada.getOperacao()) {
				case CRIAR_CLIENTE_PF:
					try {
						ClientePF c = criarClientePF(sc, seguradora);
						abrirPainelCliente(sc, seguradora, c.getCPF());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					break;
				case CRIAR_CLIENTE_PJ:
					try {
						ClientePJ c = criarClientePJ(sc, seguradora);
						abrirPainelCliente(sc, seguradora, c.getCNPJ());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					break;
				case PAINEL_CLIENTE:
					System.out.print("Insira o CPF/CNPJ: ");
					String identificacao = sc.nextLine();
					abrirPainelCliente(sc, seguradora, identificacao);
					break;
				default:
					return;
			}
		}
	}

	public static void executarMenuInterativo(Scanner sc) {
		ArrayList<Seguradora> seguradoras = new ArrayList<Seguradora>();
		Opcao opcaoSelecionada;
		Menu menu;
		
		execucao:
		while (true) {
			menu = Menu.selecaoSeguradora(seguradoras);
			menu.mostrar();
			opcaoSelecionada = menu.selecionarOpcao(sc);
			for (Opcao opcao : menu.getOpcoes()) {
				if (opcao == opcaoSelecionada) {
					if (opcao.getOperacao() == Operacao.CRIAR_SEGURADORA) {
						try {
							Seguradora s = criarSeguradora(sc, seguradoras);
							abrirPainelSeguradora(sc, s);
						} catch (InputMismatchException e) {
							System.out.println(e.getMessage());
						}
						continue execucao;
					}
					else if (opcao.getOperacao() == Operacao.PAINEL_SEGURADORA) {
						Seguradora s = seguradoras.get(opcao.getCodigo() - 1);
						abrirPainelSeguradora(sc, s);
						continue execucao;
					}
				}
			}
			break execucao;
		}

		sc.close();
	}

	public static void testeClasses() {
		String telefone = "853.161.830-45";
		String email = "email@email.com";
		String marca = "Honda";
		String modelo = "Civic";

		// Seguradora
		Seguradora seguradora = new Seguradora("73.913.193/0001-45", "Teste Seguradora", 
										   "(11)1234-5678", email, "Campinas");

		// Clientes
		ClientePF carlos = new ClientePF("Carlos", "(11)1234-5678", 
				  "São Paulo", email, "873.990.571-33", "Masculino", 
		          "Ensino Médio", LocalDate.of(2001, 10, 10));

		ClientePF jose = new ClientePF("José", "(11)1234-5678", 
				"Curitiba", email, "853.161.830-45", "Masculino", 
				"Ensino Superior", LocalDate.of(2000, 9, 10));

		ClientePJ empresa = new ClientePJ("Empresa Teste", telefone, "Manaus", 
							email, "64.261.346/0001-26", LocalDate.of(2004, 12, 12));

		ClientePJ grupo = new ClientePJ("Grupo Teste", telefone, "Belém", 
						  email, "81.031.364/0001-30", LocalDate.of(2012, 4, 9));

		seguradora.cadastrarCliente(carlos);
		seguradora.cadastrarCliente(jose);									  
		seguradora.cadastrarCliente(empresa);
		seguradora.cadastrarCliente(grupo);

		// Veículos e frotas
		Frota[] frotas = {new Frota("empresaCarros"), new Frota("empresaMotos"),
				   		  new Frota("grupoCarros"), new Frota("grupoMotos")};

		try {
			frotas[0].addVeiculo("EEE-1234", marca, modelo, 2013);
			frotas[0].addVeiculo("FFF-1234", marca, modelo, 2014);
			frotas[1].addVeiculo("GGG-1234", marca, modelo, 2015);
			frotas[1].addVeiculo("HHH-1234", marca, modelo, 2016);
			frotas[2].addVeiculo("III-1234", marca, modelo, 2017);
			frotas[2].addVeiculo("JJJ-1234", marca, modelo, 2018);
			frotas[3].addVeiculo("KKK-1234", marca, modelo, 2019);
			frotas[3].addVeiculo("LLL-1234", marca, modelo, 2020);

			carlos.cadastrarVeiculo("AAA-1234", marca , modelo, 2009);
			carlos.cadastrarVeiculo("BBB-1234", marca, modelo, 2010);
			jose.cadastrarVeiculo("CCC-1234", marca, modelo, 2011);
			jose.cadastrarVeiculo("DDD-1234", marca, modelo, 2012);
			empresa.cadastrarFrota(frotas[0]);
			empresa.cadastrarFrota(frotas[1]);
			grupo.cadastrarFrota(frotas[2]);
			grupo.cadastrarFrota(frotas[3]);
		} catch (NameAlreadyBoundException e) {
			e.printStackTrace();
		}
		
		// Seguros
		try {
			seguradora.gerarSeguro(LocalDate.of(2017, 10, 3), 
								   LocalDate.of(2028, 1, 1), 
								   carlos.procurarVeiculo("AAA-1234"), carlos);
	
			seguradora.gerarSeguro(LocalDate.of(2017, 10, 3), 
								   LocalDate.of(2028, 1, 1), 
								   carlos.procurarVeiculo("BBB-1234"), carlos);
	
			seguradora.gerarSeguro(LocalDate.of(2017, 10, 3), 
								   LocalDate.of(2028, 1, 1), 
								   jose.procurarVeiculo("CCC-1234"), jose);
			seguradora.gerarSeguro(LocalDate.of(2017, 10, 3), 
								   LocalDate.of(2028, 1, 1), 
								   jose.procurarVeiculo("DDD-1234"), jose);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		seguradora.gerarSeguro(LocalDate.of(2017, 10, 3), 
							   LocalDate.of(2028, 1, 1), 
							   frotas[0], empresa);

		seguradora.gerarSeguro(LocalDate.of(2017, 10, 3), 
							   LocalDate.of(2028, 1, 1), 
							   frotas[1], empresa);

		seguradora.gerarSeguro(LocalDate.of(2017, 10, 3), 
							   LocalDate.of(2028, 1, 1), 
							   frotas[2], grupo);

		seguradora.gerarSeguro(LocalDate.of(2017, 10, 3), 
							   LocalDate.of(2028, 1, 1), 
							   frotas[3], grupo);

		// Condutores e Sinistros
		Condutor[] condutores = {new Condutor("748.053.318-79", "filhoCarlos", telefone, "Santos", email, 
								 LocalDate.of(2003, 10, 10), 
								 LocalDate.of(2030, 10, 10)), 
								 new Condutor("828.535.401-62", "maeCarlos", telefone, "Santos", email, 
								 LocalDate.of(2003, 10, 10), 
								 LocalDate.of(2030, 10, 10)),
								 new Condutor("213.368.795-56", "paiCarlos", telefone, "Santos", email, 
								 LocalDate.of(2003, 10, 10), 
								 LocalDate.of(2030, 10, 10)),
								 new Condutor("243.243.453-68", "irmaCarlos", telefone, "Santos", email, 
								 LocalDate.of(2003, 10, 10), 
								 LocalDate.of(2030, 10, 10)),};
		
		try {
			ArrayList<Seguro> segurosCarlos = seguradora.getSegurosPorCliente(carlos);
			segurosCarlos.get(0).autorizarCondutor(condutores[0]);
			segurosCarlos.get(0).autorizarCondutor(condutores[1]);
			segurosCarlos.get(1).autorizarCondutor(condutores[2]);
			segurosCarlos.get(1).autorizarCondutor(condutores[3]);
			
			segurosCarlos.get(1).gerarSinistro(LocalDate.of(2019, 10, 10), "Porto Alegre", condutores[3].getCPF());
			segurosCarlos.get(0).gerarSinistro(LocalDate.of(2019, 10, 10), "Porto Alegre", condutores[1].getCPF());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// toString()
		System.out.println("\n\n##### INFO SEGURADORA #####\n");
		System.out.println(seguradora);
		System.out.println("\n\n##### INFO CLIENTES #####\n");
		seguradora.getListaClientes().forEach(System.out::println);
		System.out.println("\n\n##### INFO SEGUROS #####\n");
		seguradora.getListaSeguros().forEach(System.out::println);
		System.out.println("\n\n##### INFO VEÍCULOS CARLOS #####\n");
		carlos.getListaVeiculos().forEach(System.out::println);
		System.out.println("\n\n##### INFO FROTAS EMPRESA #####\n");
		empresa.getListaFrota().forEach(System.out::println);
		System.out.println("\n\n##### INFO SINISTROS CARLOS #####\n");
		seguradora.getSinistrosPorCliente(carlos).forEach(System.out::println);
		System.out.println("\n\n##### INFO CONDUTOR #####\n");
		System.out.println(seguradora.getSegurosPorCliente(carlos).get(0).getListaCondutores().get(0));
	}
}
