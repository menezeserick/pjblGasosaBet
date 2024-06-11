import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Aposta {
    protected int idAposta;
    protected double valorAposta;
    protected Date dataAposta;
    protected double ganhoTotal;
    protected String tipoAposta;
    private static List<Aposta> historicoApostas = new ArrayList<>();

    public Aposta() {
        this.idAposta = gerarIdUnico();
        this.dataAposta = new Date();
    }

    private int gerarIdUnico() {
        UUID uuid = UUID.randomUUID();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        return (int) (leastSignificantBits & Integer.MAX_VALUE);
    }

    public abstract void calcularPagamento();

    public void definirValorAposta(double valorAposta) {
        if (valorAposta <= 0) {
            throw new IllegalArgumentException("Valor da aposta deve ser positivo.");
        }
        this.valorAposta = valorAposta;
        this.dataAposta = new Date();
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

    public double getGanhoTotal() {
        return ganhoTotal;
    }

    public void setGanhoTotal(double ganhoTotal) {
        this.ganhoTotal = ganhoTotal;
    }

    public String getTipoAposta() {
        return tipoAposta;
    }

    public void setTipoAposta(String tipoAposta) {
        this.tipoAposta = tipoAposta;
    }

    public static List<Aposta> getHistoricoApostas() {
        return historicoApostas;
    }

    public static void exibirHistoricoApostas() {
        System.out.println("Histórico de apostas:");
        for (Aposta aposta : historicoApostas) {
            System.out.println("ID: " + aposta.getIdAposta());
            System.out.println("Valor: R$ " + aposta.getValorAposta());
            System.out.println("Data: " + aposta.getDataAposta());
            System.out.println("Tipo: " + aposta.getTipoAposta());  // Mostrar tipo de aposta
            System.out.println("Ganho Total: R$ " + aposta.getGanhoTotal());
            System.out.println("-----------------------------------");
        }
    }

    public static void salvarApostaNoArquivo(Aposta aposta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("historico_apostas.txt", true))) {
            writer.write("ID: " + aposta.getIdAposta());
            writer.newLine();
            writer.write("Valor: R$ " + aposta.getValorAposta());
            writer.newLine();
            writer.write("Data: " + aposta.getDataAposta());
            writer.newLine();
            writer.write("Tipo: " + aposta.getTipoAposta());
            writer.newLine();
            writer.write("Ganho Total: R$ " + aposta.getGanhoTotal());
            writer.newLine();
            writer.write("-----------------------------------");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    public static void adicionarApostaNoArquivo(Aposta aposta) {
        historicoApostas.add(aposta);
        salvarApostaNoArquivo(aposta);
    }
}