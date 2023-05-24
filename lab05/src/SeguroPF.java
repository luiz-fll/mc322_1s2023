import java.time.LocalDate;
import java.time.Period;
import java.util.stream.Collectors;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF clientePF) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = clientePF;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setveiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
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
        int idade = Period.between(cliente.getDataNascimento(), LocalDate.now()).getYears();
        int quantidadeSinistrosCliente = seguradora.getSinistrosPorCliente(cliente).size();
        
        int quantidadeSinistrosCondutor = listaCondutores
                                          .stream()
                                          .collect(Collectors.summingInt(condutor -> condutor.getListaSinistros().size()));

        return (CalcSeguro.VALOR_BASE() * 
                CalcSeguro.FATOR_IDADE(idade) * 
                (1 + 1 / (cliente.getListaVeiculos().size() + 2)) *
                (2 + quantidadeSinistrosCliente / 10) *
                (5 + quantidadeSinistrosCondutor /10));
    }
}
