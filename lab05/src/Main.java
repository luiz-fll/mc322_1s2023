import javax.naming.NameAlreadyBoundException;

public class Main {
	public static void main(String args[]) throws NameAlreadyBoundException {
		ClientePJ c = new ClientePJ("A", "a", "null", "null", "null", null);
		c.cadastrarFrota(new Frota("a"));
		c.cadastrarFrota(new Frota("b"));
		System.out.println(c);
	}
}
