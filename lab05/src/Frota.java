import java.util.ArrayList;

import javax.naming.NameNotFoundException;

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();


    public Frota(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public Veiculo procurarVeiculo(String placa) 
    throws NameNotFoundException {
        return listaVeiculos
        .stream()
        .filter(veiculo -> veiculo.getPlaca().equals(placa))
        .findAny()
        .orElseThrow(() -> new NameNotFoundException("Veículo não encontrado: " + placa));
    }

    @Override
    public String toString() {
        ArrayList<String> listaPlacas = new ArrayList<String>();
        for (Veiculo veiculo : getListaVeiculos()) {
            listaPlacas.add(veiculo.getPlaca());
        }

        return 
        "Frota '" + getCode() + "' com veículos: " + listaPlacas;
    }
}