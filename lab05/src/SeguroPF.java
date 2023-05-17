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

    public void setClientePF(ClientePF clientePF) {
        this.clientePF = clientePF;
    }

    public boolean autorizarCondutor() {
        return true;
    }

    public boolean desautorizarCondutor() {
        return true;
    }

    public void gerarSinistro() {

    }

    public void calcularValor() {
        
    }
}
