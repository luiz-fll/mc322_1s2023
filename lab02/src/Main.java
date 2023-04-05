public class Main {
	
	public static void main(String[] args) {
		// Teste construtores
		Cliente cliente = new Cliente("Jose", "000.000.000-00", "21/03/2003", 20, "Campinas-SP, Brasil");
		Sinistro sinistro1 = new Sinistro("10/10/2022", "Rio de Janeiro");
		Sinistro sinistro2 = new Sinistro("10/11/2022", "Paris");
		Sinistro sinistro3 = new Sinistro("10/12/2022", "Oceano Atlântico");
		Seguradora seguradora = new Seguradora("maisSeguro", "4002-8922", "maisseguro@yahoo.com", "Berlim");
		Veiculo veiculo = new Veiculo("LOL-1337", "Honda", "Civic");
		
		System.out.println("Teste getters e setters de Cliente"); ///
		System.out.println(cliente.getNome());
		cliente.setNome("Carlos");
		System.out.println(cliente.getNome());
		
		System.out.println("Teste toString"); ///
		System.out.println(cliente.toString());
		
		System.out.println("Teste validarCPF"); ///
		System.out.println(cliente.validarCPF(cliente.getCPF()));
		System.out.println(cliente.validarCPF("286.103.370-30")); // CPF válido gerado aleatoriamente
		
		System.out.println("Teste gerarID e getters e setters de Sinistro"); ///
		System.out.println(sinistro1.getId());
		System.out.println(sinistro2.getId());
		System.out.println(sinistro3.getId());
		sinistro3.setId(8);
		System.out.println(sinistro3.getId());
		
		System.out.println("Teste getters e setters de Seguradora"); ///
		System.out.println(seguradora.getEndereco());
		seguradora.setEndereco("Pequim");
		System.out.println(seguradora.getEndereco());
		
		System.out.println("Teste getters e setters de Veiculo"); ///
		System.out.println(veiculo.getModelo());
		veiculo.setModelo("Accord");
		System.out.println(veiculo.getModelo());
	}
}
