import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        try (Scanner leia = new Scanner(System.in)) {
            int opcao;

            do {
                exibirMenu();
                opcao = lerInteiro(leia, "Escolha uma opção: ");

                switch (opcao) {
                    case 1 -> adicionarTarefa(leia, tarefas);
                    case 2 -> listarTarefas(tarefas);
                    case 3 -> concluirTarefa(leia, tarefas);
                    case 4 -> removerTarefa(leia, tarefas);
                    case 0 -> System.out.println("Até logo!");
                    default -> System.out.println("Opção inválida.");
                }
            } while (opcao != 0);
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Lista de Tarefas ===");
        System.out.println("1 - Cadastrar tarefa");
        System.out.println("2 - Listar tarefas");
        System.out.println("3 - Marcar como concluída");
        System.out.println("4 - Remover tarefa");
        System.out.println("0 - Sair");
    }

    private static int lerInteiro(Scanner leia, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(leia.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números.");
            }
        }
    }

    // Retorna a posição na lista
    private static int lerPosicao(Scanner leia, ArrayList<Tarefa> tarefas, String mensagem) {
        int numero = lerInteiro(leia, mensagem);
        int posicao = numero - 1;

        if (posicao < 0 || posicao >= tarefas.size()) {
            System.out.println("Número inválido.");
            return -1;
        }
        return posicao;
    }

    private static void adicionarTarefa(Scanner leia, ArrayList<Tarefa> tarefas) {
        System.out.print("Escreva a tarefa: ");
        String texto = leia.nextLine().trim();

        if (texto.isEmpty()) {
            System.out.println("O texto não pode ficar vazio!");
            return;
        }

        tarefas.add(new Tarefa(texto));
        System.out.println("Tarefa adicionada!");
    }

    private static void listarTarefas(ArrayList<Tarefa> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Não há tarefas cadastradas.");
            return;
        }

        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println((i + 1) + ". " + tarefas.get(i));
        }
    }

    private static void concluirTarefa(Scanner leia, ArrayList<Tarefa> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Não há tarefas cadastradas.");
            return;
        }

        listarTarefas(tarefas);
        int posicao = lerPosicao(leia, tarefas, "Qual tarefa deseja marcar como concluída? ");
        if (posicao == -1) {
            return;
        }

        Tarefa tarefa = tarefas.get(posicao);
        if (tarefa.isConcluida()) {
            System.out.println("Essa tarefa já estava concluída.");
        } else {
            tarefa.concluir();
            System.out.println("Tarefa concluída!");
        }
    }

    private static void removerTarefa(Scanner leia, ArrayList<Tarefa> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Não há tarefas cadastradas.");
            return;
        }

        listarTarefas(tarefas);
        int posicao = lerPosicao(leia, tarefas, "Qual tarefa deseja remover? ");
        if (posicao == -1) {
            return;
        }

        tarefas.remove(posicao);
        System.out.println("Tarefa removida!");
    }
}