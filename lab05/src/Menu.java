import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Opcao> opcoes = new ArrayList<Opcao>();
    private Opcao voltar = new Opcao("Voltar", 0, Operacao.VOLTAR);
    private String titulo;

    public Menu(String titulo) {
        this.titulo = "\n" + titulo;
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

    public static Menu selecaoSeguradora(ArrayList<Seguradora> seguradoras) {
        Menu menu = new Menu("Selecionar Seguradora");
        for (Seguradora seguradora : seguradoras) {
            menu.novaOpcao(seguradora.getNome(), Operacao.PAINEL_SEGURADORA);
        }
        menu.novaOpcao("<Criar Seguradora>", Operacao.CRIAR_SEGURADORA);

        return menu;
    }

    public static Menu painelSeguradora(Seguradora seguradora) {
        Menu menu = new Menu(seguradora.toString());
        
        menu.novaOpcao("Criar Cliente PF", Operacao.CRIAR_CLIENTE_PF);
        menu.novaOpcao("Criar Cliente PJ", Operacao.CRIAR_CLIENTE_PJ);
        if (!seguradora.getListaClientes().isEmpty()) {
            menu.novaOpcao("Painel Cliente", Operacao.PAINEL_CLIENTE);
        }

        return menu;
    }

    public static Menu painelClientePF(Seguradora seguradora, ClientePF cliente) {
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

    public static Menu painelClientePJ(Seguradora seguradora, ClientePJ cliente) {
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

    public static Menu selecaoSeguro(ArrayList<Seguro> seguros) {
        Menu menu = new Menu("Selecione o Seguro");
        for (Seguro seguro : seguros) {
            menu.novaOpcao(seguro.toString(), Operacao.SELECIONAR);
        }

        return menu;
    }
    
    public static Menu painelSeguro(Seguro seguro) {
        Menu menu = new Menu(seguro.toString());
        
        menu.novaOpcao("Condutores", Operacao.PAINEL_CONDUTOR);
        if (!seguro.getListaSinistros().isEmpty()) {
            menu.novaOpcao("Sinistros do Seguro", Operacao.PAINEL_SINISTRO);
        }
        if (!seguro.getListaCondutores().isEmpty()) {
            menu.novaOpcao("Gerar Sinistro", Operacao.GERAR_SINISTRO);
        }

        return menu;
    }

    public static Menu painelSinistro(Seguro seguro) {
        Menu menu = new Menu("Sinistros no Seguro de " + seguro.getCliente().getNome());
        for (Sinistro sinistro : seguro.getListaSinistros()) {
            menu.novaOpcao(sinistro.toString(), Operacao.VOLTAR);
        }

        return menu;
    }

    public static Menu painelSinistro(Cliente cliente, Seguradora seguradora) {
        Menu menu = new Menu("Sinistros do Cliente " + cliente.getNome());
        for (Sinistro sinistro : seguradora.getSinistrosPorCliente(cliente)) {
            menu.novaOpcao(sinistro.toString(), Operacao.VOLTAR);
        }

        return menu;
    }

    public static Menu painelSinistro(Condutor condutor) {
        Menu menu = new Menu("Sinistros do condutor " + condutor.getCPF());
        for (Sinistro sinistro : condutor.getListaSinistros()) {
            menu.novaOpcao(sinistro.toString(), Operacao.VOLTAR);
        }

        return menu;
    }

    public static Menu PainelCondutor(Seguro seguro) {
        String titulo;
        if (seguro.getListaCondutores().isEmpty()) {
            titulo = "Não há condutores no seguro " + seguro.getId() + " ainda. Deseja autorizar um?";
        } else {
            titulo = "Condutores do Seguro de " + seguro.getCliente().getNome() + ": ";
            for (Condutor condutor : seguro.getListaCondutores()) {
                titulo += "\n" + condutor;
            }
        }
        Menu menu = new Menu(titulo);
        
        menu.novaOpcao("Autorizar Condutor...", Operacao.AUTORIZAR);
        if (!seguro.getListaCondutores().isEmpty()) {
            menu.novaOpcao("Desautorizar Condutor...", Operacao.DESAUTORIZAR);
        }
        if (!seguro.getListaSinistros().isEmpty()) {
            menu.novaOpcao("Sinistros por Condutor", Operacao.PAINEL_SINISTRO);
        }

        return menu;
    }

    public static Menu selecaoCondutores(Seguro seguro, String titulo) {
        Menu menu = new Menu(titulo);
        for (Condutor condutor : seguro.getListaCondutores()) {
            menu.novaOpcao(condutor.toString(), Operacao.SELECIONAR);
        }

        return menu;
    }

    public static Menu selecaoFrota(ClientePJ cliente, String titulo) {
        Menu menu = new Menu(titulo);

        for (Frota frota : cliente.getListaFrota()) {
            menu.novaOpcao(frota.toString(), Operacao.SELECIONAR);
        }

        return menu;
    }

    public static Menu painelFrota(Frota frota, String titulo) {
        Menu menu = new Menu(titulo);
        for (Veiculo veiculo : frota.getListaVeiculos()) {
            menu.novaOpcao("Remover " + veiculo.toString(), Operacao.REMOVER_VEICULO);
        }
        menu.novaOpcao("Adicionar Veiculo", Operacao.CADASTRAR_VEICULO);
        
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