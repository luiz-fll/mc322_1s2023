import java.time.LocalDate;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;

    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, int valorMensal, Veiculo veiculo, ClientePF clientePF) {
        super(dataInicio, dataFim, seguradora, valorMensal, clientePF);
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setveiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
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
