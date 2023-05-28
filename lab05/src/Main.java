import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.InputMismatchException;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;

public class Main {
	public static void main(String args[]) throws NameAlreadyBoundException {
		executarMenuInterativo();
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

	public static void executarMenuInterativo() {
		try {
			Scanner sc = new Scanner(new FileReader("auto.txt"));
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
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
}
