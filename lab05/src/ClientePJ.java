import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;
    private ArrayList<Frota> listaFrota = new ArrayList<Frota>();

    // Construtor

    public ClientePJ(String nome, String telefone, String endereco, String email, String CNPJ, LocalDate dataFundacao) {
        super(nome, telefone, endereco, email);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
    }

    // getters e setters
    public String getCNPJ() {
        return this.CNPJ;
    }

    public LocalDate getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public ArrayList<Frota> getListaFrota() {
        return this.listaFrota;
    }

    public void setListaFrota(ArrayList<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }
    
    public boolean cadastrarFrota() {
        return true;
    }

    public boolean atualizarFrota() {
        return true;
    }

    public boolean getVeiculosporFrota() {
        return true;
    }
}
