import java.util.Date;

public class Credito extends Deposito {
    private String numeroCartao;
    private String nomeTitular;
    private Date validade;
    private int cvv;

    public Credito(int idDeposito, double valorDeposito, Date dataDeposito, Usuario usuario, String numeroCartao, String nomeTitular, Date validade, int cvv) {
        super(usuario, idDeposito, valorDeposito, dataDeposito);
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.cvv = cvv;
    }


    @Override
    public void processarPagamento() {
        System.out.println("Processando pagamento via Cartão de Crédito para o titular: " + nomeTitular);
        boolean pagamentoValido = validarPagamento();
        if (pagamentoValido) {
            double saldoAtual = usuario.getSaldoAtual();
            saldoAtual += valorDeposito;
            usuario.setSaldoAtual(saldoAtual);
            System.out.println("Pagamento via Cartão de Crédito processado com sucesso.");
        }
        else {
            System.out.println("Falha no processamento do pagamento via Cartão de Crédito.");
        }
    }

    private boolean validarPagamento() {
        //implementar validação de pagamento, por enquanto teste.
        return true;
    }

    @Override
    public void realizarDeposito(){
        System.out.println("Realizando depósito via Cartão de Crédito no valor de: " + valorDeposito);
        registrarDeposito();
        System.out.println("Depósito via Cartão de Crédito realizado com sucesso.");
    }
    private void registrarDeposito() {
        //futuro registro de deposito
        System.out.println("Depósito de" + valorDeposito + "registrado no sistema.");
    }
}