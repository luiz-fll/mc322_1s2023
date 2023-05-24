import java.time.LocalDate;
import java.time.Period;
import java.util.stream.Collectors;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota, ClientePJ clientePJ) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = clientePJ;
    }

    public Frota getFrota() {
        return this.frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public boolean autorizarCondutor() {
        return true;
    }

    public boolean desautorizarCondutor() {
        return true;
    }

    public void gerarSinistro() {

    }

    public double calcularValor() {
        int quantidadeVeiculos = cliente.getListaFrota()
                                 .stream()
                                 .collect(Collectors.summingInt(frota -> frota.getListaVeiculos().size()));
        int AnosPosFundacao = Period.between(cliente.getDataFundacao(), LocalDate.now()).getYears();
        int quantidadeSinistrosCondutor = listaCondutores
                                          .stream()
                                          .collect(Collectors.summingInt(condutor -> condutor.getListaSinistros().size()));
        int quantidadeSinistrosCliente = seguradora.getSinistrosPorCliente(cliente).size();
        
        return (CalcSeguro.VALOR_BASE() *
               (1 + 1 / (quantidadeVeiculos + 2)) *
               (1 + 1 / ( AnosPosFundacao +2) ) *
               (2 + quantidadeSinistrosCliente /10) *
               (5 + quantidadeSinistrosCondutor /10));
    }
}
