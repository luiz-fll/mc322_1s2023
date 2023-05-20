import java.util.List;

import javax.naming.NameNotFoundException;

import java.util.ArrayList;
import java.time.LocalDate;

public class Seguradora {
	private final String CNPJ;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private List<Cliente> listaClientes;
	private List<Seguro> listaSeguros;
	
	// Construtor
	public Seguradora (String CNPJ, String nome, String telefone, String email, String endereco) {
		this.CNPJ = CNPJ;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSeguros = new ArrayList<Seguro>();
	}
	
	// Getters e setters
	public String getNome () {
		return nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getTelefone () {
		return telefone;
	}
	
	public void setTelefone (String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getEndereco () {
		return endereco;
	}
	
	public void setEndereco (String endereco) {
		this.endereco = endereco;
	}

	// Lista os clientes, podendo ser todos, somente os PF ou somente os PJ. O tipoCliente vem do input da função main.
	public List<Cliente> listarClientes(String tipoCliente) {
		if (tipoCliente.equals("PF")) {
			List<Cliente> clientesPF = new ArrayList<Cliente>();
			for (Cliente cliente : this.listaClientes) {
				if (cliente.getClass() == ClientePF.class) {
					clientesPF.add(cliente);
				}
			}
			return clientesPF;
		}
		else if (tipoCliente.equals("PJ")) {
			List<Cliente> clientesPJ = new ArrayList<Cliente>();
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

	public boolean gerarSeguro() {
		return true;
	}

	public boolean cancelarSeguro() {
		return true;
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

	public ArrayList<Seguro> getSegurosPorCliente() {
		return null;
	}

	public ArrayList<Seguro> getSinistrosPorCliente() {
		return null;
	}

	public double calcularReceita() {
		return -1;
	}
}
