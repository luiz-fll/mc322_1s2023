import java.time.LocalDate;

public class Main {
	
	public static void main(String[] args) {
		Cliente clienteF = new ClientePF("Joao", "Campinas-SP", LocalDate.of(2022, 8, 13), "Ensino Superior", "Masculino", "BURGUÊS", null, LocalDate.of(2001, 9, 11));
		Cliente clienteJ = new ClientePJ("Empresa", "São Paulo-SP", "1121312", LocalDate.of(2002, 12, 3));

		clienteF.cadastrarVeiculo(new Veiculo("ABC-1234", "Honda", "Civic", 2013));
		clienteJ.cadastrarVeiculo(new Veiculo("CBA-4321", "Toyota", "Camry", 2017));

		Seguradora seg = new Seguradora("Seguradora", "4002-8922", "email@email.com", "São Paulo-SP");
		seg.cadastrarCliente(clienteF);
		seg.cadastrarCliente(clienteJ);

		seg.gerarSinistro(clienteJ, clienteJ.getListaVeiculos().get(0));
		seg.gerarSinistro(clienteF, clienteF.getListaVeiculos().get(0));
		seg.visualizarSinistro("Joao");
	}
}
