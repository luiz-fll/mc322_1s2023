import java.util.ArrayList;

import javax.naming.NameNotFoundException;

public class Seguradora {
	private final String CNPJ;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Seguro> listaSeguros;
	
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
