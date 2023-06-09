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
        this.setValorMensal(calcularValor()); 
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

    public ClientePJ getClientePJ() {
        return this.cliente;
    }

    public void setClientePJ(ClientePJ cliente) {
        this.cliente = cliente;
    }

    public double calcularValor() {
        int AnosPosFundacao = Period.between(cliente.getDataFundacao(), LocalDate.now()).getYears();
        int quantidadeSinistrosCliente = getSeguradora().getSinistrosPorCliente(cliente).size();
        
        int quantidadeVeiculos = cliente.getListaFrota()
                                 .stream()
                                 .collect(Collectors.summingInt(frota -> frota.getListaVeiculos().size()));

        int quantidadeSinistrosCondutor = getListaCondutores()
                                          .stream()
                                          .collect(Collectors.summingInt(condutor -> condutor.getListaSinistros().size()));
        
        return (CalcSeguro.VALOR_BASE() * (1 + 1 / (quantidadeVeiculos + 2)) * (1 + 1 / ( AnosPosFundacao + 2) ) * (2 + quantidadeSinistrosCliente / 10) * (5 + quantidadeSinistrosCondutor / 10));
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Frota: " + frota.getCode() + "\n" +
               "Cliente: " + cliente.getCNPJ();
    }
}
