import java.util.Scanner;

public class ApostaEsportiva extends Aposta {
    private String esporte;
    private double oddsTime1;
    private double oddsTime2;
    private String tipoAposta;

    public void definirAposta(Scanner scanner) {
        System.out.print("Digite o esporte: ");
        esporte = scanner.nextLine();

        System.out.print("Digite as odds para o Time 1: ");
        oddsTime1 = scanner.nextDouble();

        System.out.print("Digite as odds para o Time 2: ");
        oddsTime2 = scanner.nextDouble();
        
        scanner.nextLine();

        System.out.print("Digite o tipo de aposta (ex: vitória, pontos): ");
        tipoAposta = scanner.nextLine();
    }

    @Override
    public void calcularPagamento() {
        // Implementar ainda
    }

    public void verificarResultado(String resultado) {
        // implementar ainda
    }

    public String getEsporte() {
        return esporte;
    }

    public double getOddsTime1() {
        return oddsTime1;
    }

    public double getOddsTime2() {
        return oddsTime2;
    }

    public String getTipoAposta() {
        return tipoAposta;
    }
}
