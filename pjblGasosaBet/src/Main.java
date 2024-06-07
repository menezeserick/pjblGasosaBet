import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Escolha o tipo de aposta: ");
            System.out.println("1. Aposta Esportiva");
            System.out.println("2. Aposta em Roleta");
            System.out.println("3. Visualizar histórico de apostas");
            System.out.println("4. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            if (escolha == 1) {
                ApostaEsportiva minhaAposta = new ApostaEsportiva();
                minhaAposta.definirValorAposta(scanner);
                minhaAposta.definirAposta(scanner);
                minhaAposta.verificarResultado();
            } else if (escolha == 2) {
                ApostaRoleta minhaAposta = new ApostaRoleta();
                minhaAposta.definirValorAposta(scanner);
                minhaAposta.resultadoFinal();
            } else if (escolha == 3) {
                // Leitura de histórico no arquivo (não implementado)
                System.out.println("Visualização de histórico ainda não implementada.");
            } else if (escolha == 4) {
                System.out.println("Encerrando o programa...");
                break; // Sai do loop
            } else {
                System.out.println("Escolha inválida.");
            }
        }
    }
}
