import java.time.LocalDate;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ clientePJ;


    public SeguroPJ(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, int valorMensal, Frota frota, ClientePJ clientePJ) {
        super(id, dataInicio, dataFim, seguradora, valorMensal);
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
}
