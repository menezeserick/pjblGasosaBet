import java.util.Random;
import java.util.Scanner;

public class ApostaRoleta extends Aposta {
    private static final int PRETO = 0;
    private static final int VERMELHO = 1;
    private static final int BRANCO = 2;

    private static final double PRETO_PROBABILIDADE = 0.495;
    private static final double VERMELHO_PROBABILIDADE = 0.495;
    private static final double BRANCO_PROBABILIDADE = 0.01;

    public static int obterCorAposta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a cor da sua aposta (preto, vermelho ou branco): ");
        String corString = scanner.nextLine();

        switch (corString.toLowerCase()) {
            case "preto":
                return PRETO;
            case "vermelho":
                return VERMELHO;
            case "branco":
                return BRANCO;
            default:
                System.out.println("Cor inválida. Tente novamente.");
                return obterCorAposta();
        }
    }

    public static int girarRoleta(Random random) {
        double[] probabilidades = {PRETO_PROBABILIDADE, VERMELHO_PROBABILIDADE, BRANCO_PROBABILIDADE};

        double valorRandom = random.nextDouble();
        int colorIndex = 0;
        for (int i = 0; i < probabilidades.length; i++) {
            if (valorRandom < probabilidades[i]) {
                colorIndex = i;
                break;
            }
            valorRandom -= probabilidades[i];
        }
        return colorIndex;
    }

    public static boolean verificarVitoria(int corAposta, int corVencedora) {
        return corAposta == corVencedora;
    }

    public static void exibirResultadoAposta(double valorAposta, int corAposta, int corVencedora, boolean jogadorGanhou, double ganhoTotal) {
        System.out.println("=====================================");
        System.out.println("Resultado da Aposta:");
        System.out.println("Valor da Aposta: R$" + valorAposta);
        System.out.println("Cor da Aposta: " + obterNomeCor(corAposta));
        System.out.println("Cor Vencedora: " + obterNomeCor(corVencedora));
        System.out.println("Jogador Ganhou: " + (jogadorGanhou ? "Sim" : "Não"));
        System.out.println("Ganho Total: R$" + ganhoTotal);
        System.out.println("=====================================");
    }

    public static String obterNomeCor(int cor) {
        switch (cor) {
            case PRETO:
                return "Preto";
            case VERMELHO:
                return "Vermelho";
            case BRANCO:
                return "Branco";
            default:
                return "Cor Inválida";
        }
    }

    public void resultadoFinal() {
        Random random = new Random();
        double valorAposta = getValorAposta();
        int corAposta = obterCorAposta();
        int corVencedora = girarRoleta(random);
        boolean jogadorGanhou = verificarVitoria(corAposta, corVencedora);

        double ganhoTotal = 0;
        if (jogadorGanhou) {
            if (corVencedora == PRETO || corVencedora == VERMELHO) {
                ganhoTotal = valorAposta * 2;
            } else if (corVencedora == BRANCO) {
                ganhoTotal = valorAposta * 14;
            }
        }

        setGanhoTotal(ganhoTotal);

        exibirResultadoAposta(valorAposta, corAposta, corVencedora, jogadorGanhou, ganhoTotal);
    }

    @Override
    public void calcularPagamento() {
        // tirar depois
    }
}
