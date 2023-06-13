public class Opcao {
    private String descricao;
    private int codigo;
    private Operacao operacao;

    public Opcao(String descricao, int codigo, Operacao operacao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.operacao = operacao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Operacao getOperacao() {
        return this.operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public void mostrar() {
        System.out.println(codigo + " - " + descricao);
    }
}
