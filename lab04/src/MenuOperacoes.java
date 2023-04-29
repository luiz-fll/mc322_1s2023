public enum MenuOperacoes {
    // Menu Principal
    CADASTROS(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    TRANSFERIR_SEGURO(5),
    CALCULAR_RECEITA_SEGURADORA(6),

    // Cadastrar
    CADASTRAR_CLIENTE(11),
    CADASTRAR_VEICULO(12),
    CADASTRAR_SEGURADORA(13),

    // Listar
    LISTAR_CLIENTES_POR_SEGURADORA(21),
    LISTAR_SINISTROS_POR_SEGURADORA(22),
    LISTAR_SINISTROS_POR_CLIENTE(23),
    LISTAR_VEICULOS_POR_CLIENTE(24),
    LISTAR_VEICULOS_POR_SEGURADORA(25),

    // Excluir
    EXCLUIR_CLIENTE(31),
    EXCLUIR_VEICULO(32),
    EXCLUIR_SINISTRO(33),

    // Navegação
    VOLTAR(10),
    SAIR(0);

    public final int operacao;

    MenuOperacoes(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return this.operacao;
    }
}