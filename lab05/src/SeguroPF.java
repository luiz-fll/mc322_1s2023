import java.time.LocalDate;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF clientePF;


    public SeguroPF(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, int valorMensal, Veiculo veiculo, ClientePF clientePF) {
        super(id, dataInicio, dataFim, seguradora, valorMensal);
        this.veiculo = veiculo;
        this.clientePF = clientePF;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setveiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getClientePF() {
        return this.clientePF;
    }

    public void setClientePJ(ClientePF clientePF) {
        this.clientePF = clientePF;
    }
}
