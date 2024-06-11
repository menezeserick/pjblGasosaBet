import java.util.Random;

public class ApostaEsportiva extends Aposta {
    private static final String TIME1 = "Real Madrid";
    private static final String TIME2 = "Barcelona";
    private static final double ODDS_VITORIA_TIME1 = 1.40;
    private static final double ODDS_VITORIA_TIME2 = 2.25;
    private static final double PROBABILIDADE_VITORIA_TIME1 = 0.65;

    private String timeEscolhido;
    private String timeVencedor;

    public ApostaEsportiva(double valorAposta, String timeEscolhido) {
        this.valorAposta = valorAposta;
        this.timeEscolhido = timeEscolhido;
        this.tipoAposta = "Aposta Esportiva";
    }

    @Override
    public void calcularPagamento() {
        if (timeEscolhido.equals(TIME1)) {
            setGanhoTotal(getValorAposta() * ODDS_VITORIA_TIME1);
        } else if (timeEscolhido.equals(TIME2)) {
            setGanhoTotal(getValorAposta() * ODDS_VITORIA_TIME2);
        }
    }

    public void verificarResultado() {
        Random random = new Random();
        double chance = random.nextDouble();

        if (chance < PROBABILIDADE_VITORIA_TIME1) {
            timeVencedor = TIME1;
        } else {
            timeVencedor = TIME2;
        }

        System.out.println("O vencedor é: " + timeVencedor);
        boolean jogadorGanhou = timeEscolhido.equals(timeVencedor);

        if (jogadorGanhou) {
            calcularPagamento();
            System.out.println("Você ganhou! Ganho Total: R$" + getGanhoTotal());
        } else {
            setGanhoTotal(0);
            System.out.println("Você perdeu! Ganho Total: R$" + getGanhoTotal());
        }
    }

    public String resultadoFinal() {
        verificarResultado();
        Aposta.adicionarApostaNoArquivo(this);

        return "Resultado da Aposta:\n" +
                "Valor da Aposta: R$ " + valorAposta + "\n" +
                "Time Escolhido: " + timeEscolhido + "\n" +
                "Time Vencedor: " + timeVencedor + "\n" +
                "Ganho Total: R$ " + getGanhoTotal();
    }

    public String getTimeVencedor() {
        return timeVencedor;
    }
}
