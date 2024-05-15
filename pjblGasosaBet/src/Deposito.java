import java.util.Date;

public abstract class Deposito {
    protected int idDeposito;
    protected double valorDeposito;
    protected Date dataDeposito;

    public void processarPagamento(){}

    public void realizarDeposito(){}
}
