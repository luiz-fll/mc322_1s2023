import java.time.LocalDate;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ clientePJ;

    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, int valorMensal, Frota frota, ClientePJ clientePJ) {
        super(dataInicio, dataFim, seguradora, valorMensal);
        this.frota = frota;
        this.clientePJ = clientePJ;
    }

    public Frota getFrota() {
        return this.frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getClientePJ() {
        return this.clientePJ;
    }

    public void setClientePJ(ClientePJ clientePJ) {
        this.clientePJ = clientePJ;
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
