import java.time.LocalDate;

public class SeguroPJ extends Seguro {
    private Frota frota;

    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, int valorMensal, Frota frota, ClientePJ clientePJ) {
        super(dataInicio, dataFim, seguradora, valorMensal, clientePJ);
        this.frota = frota;
    }

    public Frota getFrota() {
        return this.frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
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
