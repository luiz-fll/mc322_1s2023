import java.time.LocalDate;

public class Main {
	
	public static void main(String[] args) {
		// Instanciando clientes
		ClientePF clienteF = new ClientePF("Joao", "Campinas-SP", LocalDate.of(2022, 8, 13), "Ensino Superior", "Masculino", "BURGUÊS", "76293785", LocalDate.of(2001, 9, 11));
		ClientePJ clienteJ = new ClientePJ("Empresa", "São Paulo-SP", "1121312", LocalDate.of(2002, 12, 3));

		// Validando CPF e CNPJ
		System.out.println("CPF é válido? " + clienteF.validarCPF(clienteF.getCPF()));

		// Cadastrando Veículos
		clienteF.cadastrarVeiculo(new Veiculo("ABC-1234", "Honda", "Civic", 2013));
		clienteJ.cadastrarVeiculo(new Veiculo("CBA-4321", "Toyota", "Camry", 2017));

		// Instanciando Seguradora e cadastrando clientes
		Seguradora seg = new Seguradora("Seguradora", "4002-8922", "email@email.com", "São Paulo-SP");
		seg.cadastrarCliente(clienteF);
		seg.cadastrarCliente(clienteJ);

		// Usando métodos da Seguradora
		seg.gerarSinistro(clienteJ, clienteJ.getListaVeiculos().get(0));
		seg.gerarSinistro(clienteF, clienteF.getListaVeiculos().get(0));
		
		System.out.println("VISUALIZANDO SINISTRO");
		seg.visualizarSinistro("Joao");
		
		System.out.println("LISTANDO CLIENTES");
		System.out.println(seg.listarClientes("Ambos"));
		
		System.out.println("LISTANDO SINISTROS");
		seg.listarSinistros();

		// toString das classes
	}
}
