import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o tipo de aposta: ");
        System.out.println("1. Aposta Esportiva");
        System.out.println("2. Aposta em Roleta");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 1) {
            ApostaEsportiva minhaAposta = new ApostaEsportiva();
            minhaAposta.definirValorAposta(scanner);
            minhaAposta.definirAposta(scanner);

        } else if (escolha == 2) {
            ApostaRoleta minhaAposta = new ApostaRoleta();
            minhaAposta.definirValorAposta(scanner);
            minhaAposta.resultadoFinal();
        } else {
            System.out.println("Escolha inválida.");
        }

        Aposta.exibirHistoricoApostas();
    }
}
