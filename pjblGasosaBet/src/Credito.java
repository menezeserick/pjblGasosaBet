import java.util.Date;

public class Credito extends Deposito{
    private int numeroCartao;
    private String nomeTitular;
    private Date validade;
    private int cvv;


    @Override
    public void processarPagamento() {
    }

    @Override
    public void realizarDeposito() {
    }

}
