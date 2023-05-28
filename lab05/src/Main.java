import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.InputMismatchException;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;

public class Main {
	public static void main(String args[]) throws NameAlreadyBoundException {
		ClientePJ c = new ClientePJ("A", "a", "null", "null", "null", null);
		c.cadastrarFrota(new Frota("a"));
		c.cadastrarFrota(new Frota("b"));
		System.out.println(c);
	}

	public static ClientePF criarClientePF(Scanner sc, Seguradora seguradora) 
	throws InputMismatchException {
		System.out.println("Insira os dados do Cliente...");
            try {
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
            } catch (Exception e) {
                throw new InputMismatchException(e.getMessage());
            }
	}
	
	public static ClientePJ criarClientePJ(Scanner sc, Seguradora seguradora) 
	throws InputMismatchException {
		System.out.println("Insira os dados do Cliente...");
		try {
			String cnpj = Leitura.lerCNPJ(sc);
			String nome = Leitura.lerNome(sc);
			String telefone = Leitura.lerTelefone(sc);
			String endereco = Leitura.lerString(sc, "Endereço");
			String email = Leitura.lerEmail(sc);
			LocalDate dataFund = Leitura.lerData(sc, "Data de Fundação");
			
			ClientePJ c = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFund);
			seguradora.cadastrarCliente(c);
			return c;
		} catch (Exception e) {
			throw new InputMismatchException(e.getMessage());
		}
	}
	
	public static SeguroPF criarSeguroPF(Scanner sc, Seguradora seguradora, ClientePF cliente) 
	throws InputMismatchException {
		System.out.println("Insira os dados do Seguro...");
			try {
				LocalDate dataInicio = Leitura.lerData(sc, "Data de Início");
				LocalDate dataFim = Leitura.lerData(sc, "Data de Fim");
				Veiculo veiculo = cliente.procurarVeiculo(Leitura.lerPlaca(sc));

				SeguroPF s = new SeguroPF(dataInicio, dataFim, seguradora, veiculo, cliente);
				return s;
			} catch (Exception e) {
				throw new InputMismatchException(e.getMessage());
			}
	}

	public static SeguroPJ criarSeguroPJ(Scanner sc, Seguradora seguradora, ClientePJ cliente) 
	throws InputMismatchException {
		System.out.println("Insira os dados do Seguro...");
            try {
				LocalDate dataInicio = Leitura.lerData(sc, "Data de Início");
				LocalDate dataFim = Leitura.lerData(sc, "Data de Fim");
				Frota frota = cliente.procurarFrota(Leitura.lerString(sc, "Code"));

				SeguroPJ s = new SeguroPJ(dataInicio, dataFim, seguradora, frota, cliente);
                return s;
            } catch (Exception e) {
                throw new InputMismatchException(e.getMessage());
            }
	}

	public static void cadastrarVeiculo(Scanner sc, ClientePF cliente) 
	throws InputMismatchException {
		System.out.println("Insira os dados do Veículo...");
            try {
                String placa = Leitura.lerPlaca(sc);
                String marca = Leitura.lerString(sc, "Marca");
                String modelo = Leitura.lerString(sc, "Modelo");
                int anoFabricacao = Leitura.lerInteiro(sc, "Ano de Fabricação");
				cliente.cadastrarVeiculo(placa, marca, modelo, anoFabricacao);
            } catch (Exception e) {
                throw new InputMismatchException(e.getMessage());
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
			if (cliente.atualizarFrota(code)) {
				System.out.println(code + "removida com sucesso.");
			} else {
				System.out.println("Não foi possível remover " + code);
			}
		} catch (NameNotFoundException e) {
			System.out.println(code + "Não encontrada");
		}
	}

	public static void cadastrarFrota(Scanner sc, ClientePJ cliente) 
	throws InputMismatchException {
		try {
				System.out.print("Insira o code da frota: ");
				String code = Leitura.lerString(sc, "Code");
				cliente.cadastrarFrota(new Frota(code));
            } catch (Exception e) {
                throw new InputMismatchException(e.getMessage());
            }
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
            try {
                String cnpj = Leitura.lerCNPJ(sc);
                String nome = Leitura.lerString(sc, "Nome");
                String telefone = Leitura.lerTelefone(sc);
                String endereco = Leitura.lerString(sc, "Endereço");
                String email = Leitura.lerEmail(sc);

				Seguradora s = new Seguradora(cnpj, nome, telefone, email, endereco);
                seguradoras.add(s);
                return s;
            } catch (Exception e) {
                throw new InputMismatchException(e.getMessage());
            }
	}

	public static Seguro selecionarSeguro(Scanner sc, Menu origem, Seguradora seguradora, Cliente cliente) {
		ArrayList<Seguro> segurosDoCliente = seguradora.getSegurosPorCliente(cliente);
		Menu menu = Menu.SelecaoSeguro(segurosDoCliente, origem);
		Opcao opcaoSelecionada = menu.selecionarOpcao(sc);
		for (Opcao opcao : menu.getOpcoes()) {
			if (opcao == opcaoSelecionada) {
				return segurosDoCliente.get(opcao.getCodigo() - 1);
			}
		}
		throw new InputMismatchException();
	}

	public static void desautorizarCondutor(Scanner sc, Seguro seguro) {
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

	public static void gerarSinistro(Scanner sc, Seguro seguro) 
	throws NameNotFoundException, InputMismatchException {
		Menu painel = Menu.selecaoCondutores(seguro, "Selecione o condutor responsável");
		painel.mostrar();
		Opcao opcaoSelecionada = painel.selecionarOpcao(sc);
		for (Opcao opcao : painel.getOpcoes()) {
			if (opcao == opcaoSelecionada) {
				Condutor condutor = seguro.getListaCondutores().get(opcao.getCodigo() - 1);
				LocalDate data = Leitura.lerData(sc, "Data");
				String endereco = Leitura.lerString(sc, "Endereço");
				seguro.gerarSinistro(data, endereco, condutor.getCPF());
			}
		}
	}

	public static void abrirPainelCondutores(Scanner sc, Seguro seguro) {
		Menu painel;
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
						System.out.println(e);
					}
					break;
				case DESAUTORIZAR:
					desautorizarCondutor(sc, seguro);
					break;
				default:
					return;
			}
		}
	}

	public static void abrirPainelSeguro(Scanner sc, Menu origem, Seguro seguro) {
		Menu painel, sinistros;
		Opcao opcaoSelecionada;
		while (true) {
			painel = Menu.PainelSeguro(seguro, origem);
			painel.mostrar(seguro);
			opcaoSelecionada = painel.selecionarOpcao(sc);
			switch(opcaoSelecionada.getOperacao()) {
				case PAINEL_CONDUTOR:
					abrirPainelCondutores(sc, seguro);
					break;
				case PAINEL_SINISTRO:
					sinistros = Menu.PainelSinistro(seguro);
					sinistros.mostrar(seguro.getListaSinistros());
					break;
				case GERAR_SINISTRO:
					try {
						gerarSinistro(sc, seguro);
					} catch (Exception e) {
						System.out.println(e);
					}
					break;
				default:
					return;
			}
		}
	}

	public static void abrirPainelCliente(Scanner sc, Menu origem, Seguradora seguradora, String identificacao) 
	throws InputMismatchException {
		Menu painel;
		Opcao opcaoSelecionada;
		Cliente cliente;
		try {
			while (true) {
				if (Validacao.validaCPF(identificacao)) {
					cliente = seguradora.procurarClientePF(identificacao);
					painel = Menu.PainelClientePF((ClientePF)cliente);
				}
				else if (Validacao.validaCNPJ(identificacao)) {
					cliente = seguradora.procurarClientePJ(identificacao);
					painel = Menu.PainelClientePJ((ClientePJ)cliente);
				}
				else {
					throw new InputMismatchException("Entrada inválida: " + identificacao);
				}
				painel.mostrar();
				opcaoSelecionada = painel.selecionarOpcao(sc);
				switch (opcaoSelecionada.getOperacao()) {
					case CADASTRAR_VEICULO:
						cadastrarVeiculo(sc, (ClientePF)cliente);
						break;
					case CADASTRAR_FROTA:
						cadastrarFrota(sc, (ClientePJ)cliente);
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
							abrirPainelSeguro(sc, painel, s);
						} catch (InputMismatchException e) {
							System.out.println(e);
						}
						break;
					case CRIAR_SEGURO_PJ:
						try {
							SeguroPJ s = criarSeguroPJ(sc, seguradora, (ClientePJ)cliente);
							abrirPainelSeguro(sc, painel, s);
						} catch (InputMismatchException e) {
							System.out.println(e);
						}
						break;
					case PAINEL_SEGURO:
						try {
							Seguro s = selecionarSeguro(sc, painel, seguradora, cliente);
							abrirPainelSeguro(sc, painel, s);
						} catch (InputMismatchException e) {
							System.out.println(e);
						}
						break;
					default:
						return;
				}
			}
		} catch (NameNotFoundException e) {
			System.out.println(e);
			return;
		}
	}

	public static void abrirPainelSeguradora(Scanner sc, Menu origem, Seguradora seguradora) {
		Menu painel = Menu.PainelSeguradora(seguradora, origem);
		while (true) {
			painel.mostrar(seguradora);
			Opcao opcaoSelecionada = painel.selecionarOpcao(sc);
			switch (opcaoSelecionada.getOperacao()) {
				case CRIAR_CLIENTE_PF:
					try {
						ClientePF c = criarClientePF(sc, seguradora);
						abrirPainelCliente(sc, painel, seguradora, c.getCPF());
					} catch (InputMismatchException e) {
						System.out.println(e);
					}
					break;
				case CRIAR_CLIENTE_PJ:
				try {
					ClientePJ c = criarClientePJ(sc, seguradora);
					abrirPainelCliente(sc, painel, seguradora, c.getCNPJ());
				} catch (InputMismatchException e) {
					System.out.println(e);
				}
				case PAINEL_CLIENTE:
					System.out.print("Insira o CPF/CNPJ: ");
					String identificacao = sc.nextLine();
					abrirPainelCliente(sc, painel, seguradora, identificacao);
				default:
					return;
			}
		}
	}

	public static void executarMenuInterativo() {
		Scanner sc = new Scanner(System.in);
		ArrayList<Seguradora> seguradoras = new ArrayList<Seguradora>();
		Opcao opcaoSelecionada;
		
		execucao:
		while (true) {
			Menu menuPrincipal = Menu.MenuPrincipal(seguradoras);
			menuPrincipal.mostrar();
			opcaoSelecionada = menuPrincipal.selecionarOpcao(sc);
			for (Opcao opcao : menuPrincipal.getOpcoes()) {
				if (opcao == opcaoSelecionada) {
					if (opcao.getOperacao() == Operacao.CRIAR_SEGURADORA) {
						try {
							Seguradora s = criarSeguradora(sc, seguradoras);
							abrirPainelSeguradora(sc, menuPrincipal, s);
						} catch (InputMismatchException e) {
							System.out.println(e);
						}
						continue execucao;
					}
					else if (opcao.getOperacao() == Operacao.PAINEL_SEGURADORA) {
						Seguradora s = seguradoras.get(opcao.getCodigo() - 1);
						abrirPainelSeguradora(sc, menuPrincipal, s);
						continue execucao;
					}
				}
			}
			break execucao;
		}
	}
}
