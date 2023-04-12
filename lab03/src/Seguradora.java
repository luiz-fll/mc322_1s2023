import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

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
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSinistros = new ArrayList<Sinistro>();
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
		return listaClientes.removeIf(clienteEscolhido -> clienteEscolhido.getNome().equals(cliente)) && listaSinistros.removeIf(sinistrosDoCliente -> sinistrosDoCliente.getCliente().getNome().equals(cliente));
	}

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

	// Operações com sinistros
	public boolean gerarSinistro(Cliente cliente, Veiculo veiculo) {
		Sinistro sinistro = new Sinistro(LocalDate.now(), endereco, this, cliente, veiculo);
		listaSinistros.add(sinistro);
		
		return true;
	}

	public boolean visualizarSinistro(String cliente) {
		int i = 0;
		for (Sinistro sinistro : this.listaSinistros) {
			if (sinistro.getCliente().getNome() == cliente) {
				i++;
				System.out.println("### Sinistro " + i + " ###");
				sinistro.toString();
			}
		}
		return true;
	}

	public List<Sinistro> listarSinistros() {
		int i = 0;
		for (Sinistro sinistro : this.listaSinistros) {
			i++;
			System.out.println("### Sinistro " + i + " ###");
			sinistro.toString();
		}

		return this.listaSinistros;
	}
}
