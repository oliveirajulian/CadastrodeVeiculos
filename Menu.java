import java.util.List;
import java.util.Scanner;

public class Menu {
    private VeiculoService service;
    private Scanner scan;

    public Menu() {
        service = new VeiculoService();
        scan = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;
        String menu = """
                ==== Bem vindo ao Controle de Frotas ====
                Escolha uma das opções abaixo:
                1 - Cadastro de Veículo
                2 - Listar Veículos
                3 - Excluir Veículo
                4 - Pesquisar Veículo
                0 - Sair
                """;

        do {
            System.out.println(menu);//opção da equipe utilizar arrow
            opcao = Input.scanInt("Escolha uma opção: ", scan);
            switch(opcao) {
                case 1 -> cadastrarVeiculo();
                case 2 -> listarVeiculos();
                case 3 -> excluirVeiculo();
                case 4 -> pesquisarVeiculo();
                case 0 -> System.out.println("Volte sempre!");
                default -> System.out.println("Opção inválida!");
            }
        } while(opcao != 0);
    }

    private void cadastrarVeiculo() {
        System.out.println("==== Cadastrando novo veículo ====");
        String marca = Input.scanString("Marca: ", scan);
        String modelo = Input.scanString("Modelo: ", scan);
        int ano = Input.scanInt("Ano: ", scan);
        String placa = Input.scanString("Placa: ", scan);

        Veiculo v = new Veiculo(marca, modelo, ano, placa);
        if (service.cadastrarVeiculo(v)) {
            System.out.println("Veículo cadastrado com sucesso!");
        } else {
            System.out.println("Erro: já existe um veículo com essa placa!");
        }
    }

    private void listarVeiculos() {
        System.out.println("==== Veículos cadastrados ====");
        List<Veiculo> veiculos = service.listarVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            int i = 1;
            for (Veiculo v : veiculos) {
                System.out.println("Veículo " + i++ + ": " + v);
            }
        }
    }

    private void excluirVeiculo() {
        String placa = Input.scanString("Informe a placa do veículo a ser removido: ", scan);
        if (service.removerVeiculo(placa)) {
            System.out.println("Veículo removido com sucesso!");
        } else {
            System.out.println("Veículo não encontrado!");
        }
    }

    private void pesquisarVeiculo() {
        System.out.println("Pesquisar por: 1 - Placa, 2 - Modelo");
        int escolha = Input.scanInt("Escolha: ", scan);
        if (escolha == 1) {
            String placa = Input.scanString("Digite a placa: ", scan);
            Veiculo v = service.pesquisarPorPlaca(placa);
            if (v != null) {
                System.out.println("Veículo encontrado: " + v);
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } else if (escolha == 2) {
            String modelo = Input.scanString("Digite parte do modelo: ", scan);
            List<Veiculo> resultados = service.pesquisarPorModelo(modelo);
            if (resultados.isEmpty()) {
                System.out.println("Nenhum veículo encontrado.");
            } else {
                System.out.println("Veículos encontrados:");
                resultados.forEach(System.out::println);
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }
}
