import java.time.LocalDate;
import java.util.ArrayList;

public class Condutor {
    private final String CPF;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNasc;
    private LocalDate validadeCNH;
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();

    public Condutor(String CPF, String nome, String telefone, String endereco, String email, LocalDate dataNasc, LocalDate validadeCNH) {
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNasc = dataNasc;
        this.validadeCNH = validadeCNH;
    }

    public String getCPF() {
        return this.CPF;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public LocalDate getValidadeCNH() {
        return this.validadeCNH;
    }

    public void setValidadeCNH(LocalDate validadeCNH) {
        this.validadeCNH = validadeCNH;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public void adicionarSinistro(Sinistro sinistro) {
        listaSinistros.add(sinistro);
    }

    @Override
    public String toString() {
        return "[Condutor '" + getNome() + "']\n" +
			   "CPF: " + getCPF() + "\n" +
			   "Telefone: " + getTelefone() + "\n" +
			   "E-mail: " + getEmail() + "\n" +
			   "Endereço: " + getEndereco() + "\n" +
			   "Data de Nascimento: " + getDataNasc() + "\n" +
			   "Validade da CNH: " + getValidadeCNH();
    }
}