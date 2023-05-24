import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.NameNotFoundException;

public abstract class Seguro {
    private final int id = gerarID();
    private LocalDate dataInicio;
    private LocalDate dataFim;
    protected Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
    protected ArrayList<Condutor> listaCondutores = new ArrayList<Condutor>();
    private double valorMensal;


    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.valorMensal = calcularValor();
    }

    public int getId() {
        return this.id;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return this.listaCondutores;
    }

    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }

    public double getValorMensal() {
        return this.valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public abstract Cliente getCliente();

    public boolean desautorizarCondutor() {
        return true;
    }

    public boolean autorizarCondutor() {
        return true;
    }

    public abstract double calcularValor();

    public Condutor procurarCondutor(String CPF) 
    throws NameNotFoundException {
        return listaCondutores
        .stream()
        .filter(condutor -> condutor.getCPF().equals(CPF))
        .findAny()
        .orElseThrow(() -> new NameNotFoundException("Condutor não encontrado: " + CPF));
    }

    public void gerarSinistro(LocalDate data, String endereco, String condutorCPF) 
    throws NameNotFoundException {
        Condutor condutor = procurarCondutor(condutorCPF);
        Sinistro sinistro = new Sinistro(data, endereco, condutor, this);
        listaSinistros.add(sinistro);
        condutor.adicionarSinistro(sinistro);
    }

    // Gera uma ID baseada no tempo em milissegundos. Gerar IDs em menos de 1ms provavelmente gerará duplicatas.
    private static int gerarID() {
		long tempoMilissegundos1970 = System.currentTimeMillis(); // Sempre positivo (ACHO)
		
		// Os long positivo possuem o mesmo tanto de bit que os int (ACHO)
		// Logo, eu posso fazer casting a partir do mínimo int sem risco de overflow (ACHO)
		return (int)(Integer.MIN_VALUE + tempoMilissegundos1970); 
	}
}