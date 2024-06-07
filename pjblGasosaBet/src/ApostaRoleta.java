import java.util.Random;

public class ApostaRoleta extends Aposta {
    private static final int PRETO = 0;
    private static final int VERMELHO = 1;
    private static final int BRANCO = 2;

    private static final double PRETO_PROBABILIDADE = 0.495;
    private static final double VERMELHO_PROBABILIDADE = 0.495;
    private static final double BRANCO_PROBABILIDADE = 0.01;

    public static int obterCorAposta(String corString) {
        switch (corString.toLowerCase()) {
            case "preto":
                return PRETO;
            case "vermelho":
                return VERMELHO;
            case "branco":
                return BRANCO;
            default:
                throw new IllegalArgumentException("Cor inválida. Tente novamente.");
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

    public String resultadoFinal(String corString) {
        Random random = new Random();
        int corAposta = obterCorAposta(corString);
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
        Aposta.adicionarApostaNoArquivo(this);

        return "Resultado da Aposta:\n" +
                "Valor da Aposta: R$ " + valorAposta + "\n" +
                "Cor da Aposta: " + obterNomeCor(corAposta) + "\n" +
                "Cor Vencedora: " + obterNomeCor(corVencedora) + "\n" +
                "Jogador Ganhou: " + (jogadorGanhou ? "Sim" : "Não") + "\n" +
                "Ganho Total: R$ " + ganhoTotal;
    }

    @Override
    public void calcularPagamento() {
        // Implementar lógica de pagamento
    }
}