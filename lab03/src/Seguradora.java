import java.util.List;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Sinistro> listaSinistros;
	private List<Cliente> listaClientes;
	
	// Construtor
	public Seguradora (String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
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

	// Operações com clientes
	public boolean cadastrarCliente(Cliente cliente) {
		
		return listaClientes.add(cliente);
	}

	public boolean removerCliente(String cliente) {
		return listaClientes.removeIf(clienteEscolhido -> clienteEscolhido.getNome().equals(cliente));
	}

	public List<Cliente> listarClientes(String tipoCliente) {
		if (tipoCliente.equals("PF")) {
			List<Cliente> clientesPF = new List();
			for (Cliente cliente : listaClientes) {
				if (cliente.getClass() == ClientePF.class) {
					clientesPF.add(cliente);
				}
			}
			return clientesPF;
		}

	}

	// Operações com sinistros
	public boolean gerarSinistro() {

	}
}
