import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApostaRoleta minhaAposta = new ApostaRoleta();

        minhaAposta.definirValorAposta(scanner);
        minhaAposta.resultadoFinal();

        Aposta.exibirHistoricoApostas();
    }
}
