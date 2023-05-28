import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Opcao> opcoes = new ArrayList<Opcao>();
    private Opcao voltar = new Opcao("Voltar", 0, Operacao.VOLTAR);
    private String titulo;

    public Menu(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Opcao> getOpcoes() {
        return this.opcoes;
    }

    public void setOpcoes(ArrayList<Opcao> opcoes) {
        this.opcoes = opcoes;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void novaOpcao(String descricao, Operacao operacao) {
        opcoes.add(new Opcao(descricao, opcoes.size() + 1, operacao));
    }

    public void mostrar() {
        System.out.println(titulo);
        for (Opcao opcao : opcoes) {
            opcao.mostrar();
        }
        System.out.println("\nPressione outras teclas para voltar...");
    }

    public void mostrar(Object info) {
        System.out.println(titulo);
        System.out.println(info.toString());
        for (Opcao opcao : opcoes) {
            opcao.mostrar();
        }
        System.out.println("\nPressione outras teclas para voltar...");
    }

    public static Menu MenuPrincipal(ArrayList<Seguradora> seguradoras) {
        Menu menu = new Menu("Selecionar Seguradora");
        for (Seguradora seguradora : seguradoras) {
            menu.novaOpcao(seguradora.getNome(), Operacao.PAINEL_SEGURADORA);
        }
        menu.novaOpcao("<Criar Seguradora>", Operacao.CRIAR_SEGURADORA);

        return menu;
    }

    public static Menu PainelSeguradora(Seguradora seguradora) {
        Menu menu = new Menu(seguradora.toString());
        
        menu.novaOpcao("Criar Cliente PF", Operacao.CRIAR_CLIENTE_PF);
        menu.novaOpcao("Criar Cliente PJ", Operacao.CRIAR_CLIENTE_PJ);
        if (!seguradora.getListaClientes().isEmpty()) {
            menu.novaOpcao("Painel Cliente", Operacao.PAINEL_CLIENTE);
        }

        return menu;
    }

    public static Menu PainelClientePF(Seguradora seguradora, ClientePF cliente) {
        Menu menu = new Menu(cliente.toString());
        
        menu.novaOpcao("Cadastrar Veículo", Operacao.CADASTRAR_VEICULO);

        if (!cliente.getListaVeiculos().isEmpty()) {
            menu.novaOpcao("Remover Veículo", Operacao.REMOVER_VEICULO);
        }

        menu.novaOpcao("Remover Cliente", Operacao.REMOVER_CLIENTE);
        
        menu.novaOpcao("Criar Seguro", Operacao.CRIAR_SEGURO_PF);
        
        if (!seguradora.getSegurosPorCliente(cliente).isEmpty()) {
            menu.novaOpcao("Painel Seguro", Operacao.PAINEL_SEGURO);
        }
        
        if (!seguradora.getSinistrosPorCliente(cliente).isEmpty()) {
            menu.novaOpcao("Sinistros do Cliente", Operacao.PAINEL_SINISTRO);
        }

        return menu;
    }

    public static Menu PainelClientePJ(Seguradora seguradora, ClientePJ cliente) {
        Menu menu = new Menu(cliente.toString());
        
        menu.novaOpcao("Cadastrar Frota", Operacao.CADASTRAR_FROTA);

        if (!cliente.getListaFrota().isEmpty()) {
            menu.novaOpcao("Remover Frota", Operacao.REMOVER_FROTA);
            menu.novaOpcao("Alterar Frota", Operacao.ALTERAR_FROTA);
        }

        menu.novaOpcao("Remover Cliente", Operacao.REMOVER_CLIENTE);

        menu.novaOpcao("Criar Seguro", Operacao.CRIAR_SEGURO_PJ);
        
        if (!seguradora.getSegurosPorCliente(cliente).isEmpty()) {
            menu.novaOpcao("Painel Seguro", Operacao.PAINEL_SEGURO);
        }

        if (!seguradora.getSinistrosPorCliente(cliente).isEmpty()) {
            menu.novaOpcao("Sinistros do Cliente", Operacao.PAINEL_SINISTRO);
        }

        return menu;
    }

    public static Menu SelecaoSeguro(ArrayList<Seguro> seguros) {
        Menu menu = new Menu("Selecione o Seguro");
        for (Seguro seguro : seguros) {
            menu.novaOpcao(seguro.toString(), Operacao.PAINEL_SEGURO);
        }

        return menu;
    }
    
    public static Menu PainelSeguro(Seguro seguro) {
        Menu menu = new Menu(seguro.toString());
        
        menu.novaOpcao("Condutores", Operacao.PAINEL_CONDUTOR);
        menu.novaOpcao("Sinistros do Seguro", Operacao.PAINEL_SINISTRO);
        menu.novaOpcao("Gerar Sinistro", Operacao.GERAR_SINISTRO);

        return menu;
    }

    public static Menu PainelSinistro(Seguro seguro) {
        Menu menu = new Menu("Sinistros");
        for (Sinistro sinistro : seguro.getListaSinistros()) {
            menu.novaOpcao(sinistro.toString(), Operacao.VOLTAR);
        }

        return menu;
    }

    public static Menu PainelSinistro(Cliente cliente, Seguradora seguradora) {
        Menu menu = new Menu("Sinistros");
        for (Sinistro sinistro : seguradora.getSinistrosPorCliente(cliente)) {
            menu.novaOpcao(sinistro.toString(), Operacao.VOLTAR);
        }

        return menu;
    }

    public static Menu PainelSinistro(Condutor condutor) {
        Menu menu = new Menu("Sinistros");
        for (Sinistro sinistro : condutor.getListaSinistros()) {
            menu.novaOpcao(sinistro.toString(), Operacao.VOLTAR);
        }

        return menu;
    }

    public static Menu PainelCondutor(Seguro seguro) {
        Menu menu = new Menu(seguro.getListaCondutores().toString());
        
        menu.novaOpcao("Autorizar Condutor...", Operacao.AUTORIZAR);
        menu.novaOpcao("Desautorizar Condutor...", Operacao.DESAUTORIZAR);
        menu.novaOpcao("Sinistros do Condutor", Operacao.PAINEL_SINISTRO);

        return menu;
    }

    public static Menu selecaoCondutores(Seguro seguro, String titulo) {
        Menu menu = new Menu(titulo);
        for (Condutor condutor : seguro.getListaCondutores()) {
            menu.novaOpcao(condutor.toString(), Operacao.VOLTAR);
        }

        return menu;
    }

    public Opcao selecionarOpcao(Scanner sc) {
        String input = sc.nextLine();
        try {
            int codigo = Integer.parseInt(input);
            return opcoes.get(codigo - 1);
        } catch (NumberFormatException e) {
            return voltar;
        } catch (IndexOutOfBoundsException e) {
            return voltar;
        }
    }
}