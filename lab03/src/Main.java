import java.time.LocalDate;

public class Main {
	
	public static void main(String[] args) {
		Cliente clienteF = new ClientePF("Joao", "Campinas-SP", LocalDate.of(2022, 8, 13), "Ensino Superior", "Masculino", "BURGUÊS", null, LocalDate.of(2001, 9, 11));

		System.out.println(clienteF);
	}
}
