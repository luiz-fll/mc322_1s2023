import java.util.ArrayList;

public class Menu {
    private ArrayList<Opcao> opcoes = new ArrayList<Opcao>();
    private String titulo;
    private Menu origem;

    public Menu(String titulo) {
        this.titulo = titulo;
        this.origem = null;
    }

    public Menu(String titulo, Menu origem) {
        this.titulo = titulo;
        this.origem = origem;
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

    public Menu getOrigem() {
        return this.origem;
    }

    public void setOrigem(Menu origem) {
        this.origem = origem;
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
        System.out.println(info);
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
}