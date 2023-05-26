import java.util.ArrayList;
import java.util.Scanner;

public enum Operacao {
    CRIAR_SEGURADORA,
    PAINEL_SEGURADORA,
    CRIAR_CLIENTE_PF,
    CRIAR_CLIENTE_PJ,
    CRIAR_SEGURO_PF,
    CRIAR_SEGURO_PJ,
    PAINEL_CLEINTE,
    PAINEL_SEGURO,
    CADASTRAR_VEICULO,
    CADASTRAR_FROTA,
    REMOVER_VEICULO,
    REMOVER_FROTA,
    ALTERAR_FROTA,
    REMOVER_CLIENTE,
    PAINEL_CONDUTOR,
    AUTORIZAR,
    DESAUTORIZAR,
    PAINEL_SINISTRO,
    VOLTAR;

    public static boolean executar(Operacao operacao, Scanner sc, ArrayList<Seguradora> seguradoras) {
        switch (operacao) {
        case CRIAR_SEGURADORA:
            System.out.println("Insira os dados da Seguradora...");
            try {
                String cnpj = Leitura.lerCNPJ(sc);
                String nome = Leitura.lerString(sc, "Nome");
                String telefone = Leitura.lerTelefone(sc);
                String endereco = Leitura.lerString(sc, "Endereço");
                String email = Leitura.lerEmail(sc);
                seguradoras.add(new Seguradora(cnpj, nome, telefone, email, endereco));
            } catch (Exception e) {
                // TODO: handle exception
            }
            break;
        case PAINEL_SEGURADORA:
            
            break;
        default:
            break;
        }
        return true;
    }
}
