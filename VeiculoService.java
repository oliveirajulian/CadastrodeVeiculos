import java.util.ArrayList;
import java.util.List;

public class VeiculoService {
    private List<Veiculo> veiculos = new ArrayList<>();


    // cadastrando veiculo sem repetir a placa
    public boolean cadastrarVeiculo(Veiculo v) {
        for (Veiculo veic : veiculos) {
            if (veic.getPlaca().equalsIgnoreCase(v.getPlaca())) {
                return false;
            }
        }
        veiculos.add(v);
        return true;
    }

    // Remover veículo por placa
    public boolean removerVeiculo(String placa) {
        return veiculos.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    public List<Veiculo> listarVeiculos() {
        return veiculos;
    }

    // Pesquisar por placa 
    public Veiculo pesquisarPorPlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    // Pesquisar por modelo, nome parcial também
    public List<Veiculo> pesquisarPorModelo(String modelo) {
        List<Veiculo> resultados = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v.getModelo().toLowerCase().contains(modelo.toLowerCase())) {
                resultados.add(v);
            }
        }
        return resultados;
    }
}
