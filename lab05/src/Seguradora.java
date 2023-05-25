import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.naming.NameNotFoundException;

public class Seguradora {
	private final String CNPJ;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();
	
	// Construtor
	public Seguradora(String CNPJ, String nome, String telefone, String email, String endereco) {
		this.CNPJ = CNPJ;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}
	
	// Getters e setters
	public String getCNPJ() {
		return this.CNPJ;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Cliente> getListaClientes() {
		return this.listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Seguro> getListaSeguros() {
		return this.listaSeguros;
	}

	public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	// Lista os clientes, podendo ser todos, somente os PF ou somente os PJ. O tipoCliente vem do input da função main.
	public ArrayList<Cliente> listarClientes(String tipoCliente) {
		if (tipoCliente.equals("PF")) {
			ArrayList<Cliente> clientesPF = new ArrayList<Cliente>();
			for (Cliente cliente : this.listaClientes) {
				if (cliente.getClass() == ClientePF.class) {
					clientesPF.add(cliente);
				}
			}
			return clientesPF;
		}
		else if (tipoCliente.equals("PJ")) {
			ArrayList<Cliente> clientesPJ = new ArrayList<Cliente>();
			for (Cliente cliente : this.listaClientes) {
				if (cliente.getClass() == ClientePJ.class) {
					clientesPJ.add(cliente);
				}
			}
			return clientesPJ;
		}
		else {
			return this.listaClientes;
		}
	}

	public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, Veiculo veiculo, ClientePF cliente) {
		if (!listaClientes.contains(cliente)) {
			listaClientes.add(cliente);
		}
		return listaSeguros.add(new SeguroPF(dataInicio, dataFim, this, veiculo, cliente));
	}

	public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, Frota frota, ClientePJ cliente) {
		if (!listaClientes.contains(cliente)) {
			listaClientes.add(cliente);
		}
		return listaSeguros.add(new SeguroPJ(dataInicio, dataFim, this, frota, cliente));
	}

	public boolean cancelarSeguro(int id) 
	throws NameNotFoundException {
		return listaSeguros.remove(procurarSeguro(id));
	}

	public Seguro procurarSeguro(int id) 
	throws NameNotFoundException {
		return listaSeguros
			   .stream()
			   .filter(seguro -> seguro.getId() == id)
			   .findAny()
			   .orElseThrow(() -> new NameNotFoundException("Seguro não encontrado: " + id));
	}

	// Operações com clientes
	public boolean cadastrarCliente(Cliente cliente) {
		return listaClientes.add(cliente);
	}

	// Remove um cliente e seus sinistros
	public boolean removerCliente(String cliente) {
		return false;
	}

	public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente) {
		return new ArrayList<Seguro>((listaSeguros
		                              .stream()
									  .filter(seguro -> seguro.getCliente().equals(cliente)))
									  .collect(Collectors.toList()));
	}

	public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente) {
		ArrayList<Seguro> segurosCliente = getSegurosPorCliente(cliente);
		ArrayList<Sinistro> sinistrosCliente = new ArrayList<Sinistro>();

		/*
		 * Stream API
		 * ArrayList<Sinistro> sinistrosCliente = new ArrayList<Sinistro>(segurosCliente
		 *										  .stream()
		 *										  .flatMap(seguro -> seguro.getListaSinistros().stream())
		 *										  .collect(Collectors.toList()));
		 */

		for (Seguro seguro : segurosCliente) {
			sinistrosCliente.addAll(seguro.getListaSinistros());
		}

		
		return sinistrosCliente;
	}

	public double calcularReceita() {
		return listaSeguros
			   .stream()
			   .collect(Collectors.summingDouble(seguro -> seguro.calcularValor()));
	}
}
