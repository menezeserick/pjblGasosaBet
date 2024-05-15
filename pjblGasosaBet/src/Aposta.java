import java.util.Date;

public abstract class Aposta {
    protected int idAposta;
    protected double valorApostado;
    protected Date dataAposta;

    public abstract void calcularPagamento();
    public abstract void verificarResultado();
}
