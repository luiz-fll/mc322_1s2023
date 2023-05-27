import java.util.ArrayList;
import java.util.Scanner;

public enum Operacao {
    MENU_PRINCIPAL,
    PAINEL_SEGURADORA,
    CRIAR_SEGURADORA,
    CRIAR_CLIENTE_PF,
    CRIAR_CLIENTE_PJ,
    CRIAR_SEGURO_PF,
    CRIAR_SEGURO_PJ,
    PAINEL_CLIENTE,
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
    VOLTAR,
    SAIR;

    public static Operacao executar(Operacao operacao, Scanner sc, ArrayList<Seguradora> seguradoras, Menu menu,
                                    Seguradora seguradoraSelecionada, Seguro seguroSelecionado,
                                    Condutor condutorSelecionado, Cliente clienteSelecionado) {
        switch (operacao) {
        case MENU_PRINCIPAL:
            menu = Menu.MenuPrincipal(seguradoras);
            menu.mostrar();
            return menu.selecionarOperacao(sc);
        case CRIAR_SEGURADORA:
            System.out.println("Insira os dados da Seguradora...");
            try {
                String cnpj = Leitura.lerCNPJ(sc);
                String nome = Leitura.lerString(sc, "Nome");
                String telefone = Leitura.lerTelefone(sc);
                String endereco = Leitura.lerString(sc, "Endereço");
                String email = Leitura.lerEmail(sc);
                seguradoras.add(new Seguradora(cnpj, nome, telefone, email, endereco));
                return PAINEL_SEGURADORA;
            } catch (Exception e) {
                System.out.println(e);
                return MENU_PRINCIPAL;
            }
        case PAINEL_SEGURADORA:
            
            menu = Menu.PainelSeguradora(null);
            break;
        default:
            break;
        }
        return SAIR;
    }
}
