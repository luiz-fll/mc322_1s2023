import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;

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

    public Frota procurarFrota(String code) 
    throws NameNotFoundException {
        return listaFrota
        .stream()
        .filter(frota -> frota.getCode().equals(code))
        .findAny()
        .orElseThrow(() -> new NameNotFoundException("Frota não encontrada: " + code));
    }
    
    public boolean cadastrarFrota(Frota frota) 
    throws NameAlreadyBoundException {
        try {
            procurarFrota(frota.getCode());
            throw new NameAlreadyBoundException("Frota com mesmo code encontrada: " + frota.getCode());
        } catch (NameNotFoundException e) {
            return listaFrota.add(frota);
        }
    }

    public boolean atualizarFrota(Frota frota) {
        return true;
    }

    public boolean getVeiculosporFrota() {
        return true;
    }
}
