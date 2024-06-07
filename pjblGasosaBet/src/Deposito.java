import java.util.Date;

public abstract class Deposito {
    protected double idDeposito;
    protected double valorDeposito;
    protected Date dataDeposito;
    protected Usuario usuario;
    public Deposito(Usuario usuario, int idDeposito, double valorDeposito, Date dataDeposito) {
        this.idDeposito = idDeposito;
        this.valorDeposito = valorDeposito;
        this.dataDeposito = dataDeposito;
    }

    public abstract void processarPagamento();
    public abstract void realizarDeposito();

    public double getValorDeposito(){
        return valorDeposito;
    }
}

