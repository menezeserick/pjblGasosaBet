import java.util.Random;
import java.util.Scanner;

public class ApostaEsportiva extends Aposta {
    private static final String TIME1 = "Real Madrid";
    private static final String TIME2 = "Barcelona";
    private static final double ODDS_VITORIA_TIME1 = 1.40;
    private static final double ODDS_VITORIA_TIME2 = 2.25;
    private static final double PROBABILIDADE_VITORIA_TIME1 = 0.65;
    private static final double PROBABILIDADE_VITORIA_TIME2 = 0.35;

    private String tipoAposta;
    private String timeEscolhido;
    private int quantidadeGols;

    public void definirAposta(Scanner scanner) {
        System.out.println("Escolha o tipo de aposta: ");
        System.out.println("1. Vitória de um time");
        System.out.println("2. Quantidade de gols de um time");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        if (escolha == 1) {
            tipoAposta = "vitória";
            System.out.println("Escolha o time: ");
            System.out.println("1. Real Madrid");
            System.out.println("2. Barcelona");
            int escolhaTime = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            if (escolhaTime == 1) {
                timeEscolhido = TIME1;
            } else if (escolhaTime == 2) {
                timeEscolhido = TIME2;
            } else {
                System.out.println("Escolha inválida.");
                definirAposta(scanner);
            }
        } else if (escolha == 2) {
            tipoAposta = "gols";
            System.out.println("Escolha o time: ");
            System.out.println("1. Real Madrid");
            System.out.println("2. Barcelona");
            int escolhaTime = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            if (escolhaTime == 1) {
                timeEscolhido = TIME1;
            } else if (escolhaTime == 2) {
                timeEscolhido = TIME2;
            } else {
                System.out.println("Escolha inválida.");
                definirAposta(scanner);
            }

            System.out.print("Digite a quantidade de gols: ");
            quantidadeGols = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
        } else {
            System.out.println("Escolha inválida.");
            definirAposta(scanner);
        }
    }

    @Override
    public void calcularPagamento() {
        if ("vitória".equals(tipoAposta)) {
            if (timeEscolhido.equals(TIME1)) {
                setGanhoTotal(getValorAposta() * ODDS_VITORIA_TIME1);
            } else if (timeEscolhido.equals(TIME2)) {
                setGanhoTotal(getValorAposta() * ODDS_VITORIA_TIME2);
            }
        } else if ("gols".equals(tipoAposta)) {
            setGanhoTotal(getValorAposta() * (1.50 * quantidadeGols));
        }
    }

    public void verificarResultado() {
        Random random = new Random();
        double chance = random.nextDouble();
        String resultado;

        if (chance < PROBABILIDADE_VITORIA_TIME1) {
            resultado = TIME1;
        } else {
            resultado = TIME2;
        }

        System.out.println("O vencedor é: " + resultado);
        boolean jogadorGanhou = false;
        if ("vitória".equals(tipoAposta)) {
            jogadorGanhou = timeEscolhido.equals(resultado);
        } else if ("gols".equals(tipoAposta)) {
            // Implementar a lógica de vitória para a quantidade de gols
            // Para simplificação, assumimos que a vitória depende apenas do time
            jogadorGanhou = true;
        }

        if (jogadorGanhou) {
            calcularPagamento();
            System.out.println("Você ganhou! Ganho Total: R$" + getGanhoTotal());
        } else {
            setGanhoTotal(0);
            System.out.println("Você perdeu! Ganho Total: R$" + getGanhoTotal());
        }
    }

    public String getTimeEscolhido() {
        return timeEscolhido;
    }

    public String getTipoAposta() {
        return tipoAposta;
    }

    public int getQuantidadeGols() {
        return quantidadeGols;
    }
}
