import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public abstract class Aposta {
    protected int idAposta;
    protected double valorAposta;
    protected Date dataAposta;
    private static List<Aposta> historicoApostas = new ArrayList<>();

    public Aposta() {
        // gerar id de aposta
        this.idAposta = gerarIdUnico();
        // atribui data atual apra cada valor de aposta inserido
        this.dataAposta = new Date();
        // atribui aposta a um historico
        historicoApostas.add(this);
    }

    private int gerarIdUnico() {
        UUID uuid = UUID.randomUUID();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        int id = (int) (leastSignificantBits & Integer.MAX_VALUE);
        return id;
    }

    public abstract void calcularPagamento();

    public void definirValorAposta(Scanner scanner) {
        System.out.print("Digite o valor da sua aposta: R$ ");
        valorAposta = scanner.nextDouble();

        while (valorAposta <= 0) {
            System.out.println("Valor da aposta inválido. Digite um valor positivo:");
            valorAposta = scanner.nextDouble();
        }
        // atualizar data sempre que método é chamado
        dataAposta = new Date();
    }

    public double getValorAposta() {
        return valorAposta;
    }
    public int getIdAposta() {
        return idAposta;
    }
    public Date getDataAposta() {
        return dataAposta;
    }
    public static void exibirHistoricoApostas() {
        System.out.println("Histórico de apostas:");
        for (Aposta aposta : historicoApostas) {
            System.out.println("ID: " + aposta.getIdAposta());
            System.out.println("Valor: R$ " + aposta.getValorAposta());
            System.out.println("Data: " + aposta.getDataAposta());
            System.out.println("-----------------------------------");
        }
    }

}