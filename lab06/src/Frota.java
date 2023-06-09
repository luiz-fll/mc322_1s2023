import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.naming.NameAlreadyBoundException;
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

    public boolean addVeiculo(String placa, String marca, String modelo, int anoFabricacao) 
    throws NameAlreadyBoundException {
        try {
            procurarVeiculo(placa);
            throw new NameAlreadyBoundException("Veículo já existe: " + placa);
        } catch (NameNotFoundException e) {
            Veiculo v = new Veiculo(placa, marca, modelo, anoFabricacao);
            return listaVeiculos.add(v);
        }
    }

    public boolean removeVeiculo(Veiculo veiculo) {
        return listaVeiculos.remove(veiculo);
    }

    @Override
    public String toString() {
        return "[Frota '" + getCode() + "' / Veículos '" + getListaVeiculos()
                                                .stream()
                                                .map(Veiculo::getPlaca)
                                                .collect(Collectors.toList()) + "']";
    }
}