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

	// Remove um cliente e seus sinistros
	public boolean removerCliente(String cliente) {
		return listaClientes.removeIf(clienteEscolhido -> clienteEscolhido.getNome().equals(cliente)) && listaSinistros.removeIf(sinistrosDoCliente -> sinistrosDoCliente.getCliente().getNome().equals(cliente));
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

	// Operações com sinistros
	public boolean gerarSinistro(Cliente cliente, Veiculo veiculo, LocalDate data) {
		Sinistro sinistro = new Sinistro(data, endereco, this, cliente, veiculo);
		listaSinistros.add(sinistro);
		
		return true;
	}

	public boolean visualizarSinistro(String cliente) {
		int i = 0;
		for (Sinistro sinistro : this.listaSinistros) {
			if (sinistro.getCliente().getNome().equals(cliente)) {
				i++;
				System.out.println("### Sinistro " + i + " ###");
				System.out.println(sinistro.toString());
			}
		}
		return i != 0;
	}

	public List<Sinistro> listarSinistros() {
		return this.listaSinistros;
	}

	public double calcularPrecoSeguroCliente(Cliente cliente) {
		// Filtrando sinistros do cliente
		int qtdeSinistros = (int)listarSinistros().stream().filter(sinistro -> sinistro.getCliente() == cliente).count();

		return cliente.calculaScore() * (1 + qtdeSinistros);
	}

	public double calcularReceita() {
		double receita = 0;
		for (Cliente cliente : listaClientes) {
			receita += calcularPrecoSeguroCliente(cliente);
		}
		return receita;
	}
}
