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

    // Remove a frota a partir do code
    public boolean atualizarFrota(String code) 
    throws NameNotFoundException {
        Frota frota = procurarFrota(code);
        return listaFrota.remove(frota);
    }

    // Seleciona uma frota a partir do code, se o veículo informado estiver na frota ele é removido, se não tiver ele é adicionado
    public boolean atualizarFrota(String code, Veiculo veiculo) 
    throws NameNotFoundException {
        Frota frota = procurarFrota(code);
        ArrayList<Veiculo> listaVeiculosFrota = frota.getListaVeiculos();
        
        if (listaVeiculosFrota.contains(veiculo)) {
            return listaVeiculosFrota.remove(veiculo);  
        }
        else {
            return listaVeiculosFrota.add(veiculo);
        }
    }

    // Seleciona uma frota e recebe uma lista de veículos. Os veículos que estiverem na frota
    // serão removidos e os que não estiverem serão adicionados
    public boolean atualizarFrota(String code, ArrayList<Veiculo> listaVeiculosAtualizados) 
    throws NameNotFoundException {
        for (Veiculo veiculo : listaVeiculosAtualizados) {
            if (!atualizarFrota(code, veiculo)) {
                return false;
            }
        }
        return true;
    }

    public boolean getVeiculosporFrota(String code) 
    throws NameNotFoundException {
        Frota frota = procurarFrota(code);
        ArrayList<Veiculo> listaVeiculos = frota.getListaVeiculos();
        int i = 0;

        if (listaVeiculos.isEmpty()) {
            return false;
        }

        System.out.println("Veículos da frota '" + code + "':");
        for (Veiculo veiculo : listaVeiculos) {
            i++;
            System.out.println(i + ": " + veiculo);
        }

        return true;
    }

    @Override
    public String toString() {
        ArrayList<String> listaCode = new ArrayList<String>();
        for (Frota frota : getListaFrota()) {
            listaCode.add(frota.getCode());
        }
        
        return super.toString() +
        "-> CNPJ: '" + getCNPJ() + "'\n" +
        "-> Data de Fundação: '" + getDataFundacao() + "'\n" +
        "-> Frotas: " + listaCode;
    }
}