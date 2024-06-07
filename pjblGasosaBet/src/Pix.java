import java.util.Date;

public class Pix extends Deposito {
    private final String chavePix;

    public Pix(Usuario usuario, int idDeposito, double valorDeposito, Date dataDeposito, String chavePix) {
        super(usuario, idDeposito, valorDeposito, dataDeposito);
        this.chavePix = chavePix;
    }

    @Override
    public void processarPagamento() {
        System.out.println("Processando pagamento via Pix com a chave: " + chavePix);
        boolean pagamentoValido = validarPagamento();
        if (pagamentoValido) {
            double saldoAtual = usuario.getSaldoAtual();
            saldoAtual += valorDeposito;
            usuario.setSaldoAtual(saldoAtual);
            System.out.println("Pagamento via Pix processado com sucesso.");
        } else {
            System.out.println("Falha no processamento do pagamento via Pix.");
        }
    }

    private boolean validarPagamento() {
        //implementar validação de pagamento, por enquanto teste
        return true;
    }

    @Override
    public void realizarDeposito() {
        System.out.println("Realizando depósito via Pix no valor de: " + valorDeposito);
        registrarDeposito();
        System.out.println("Depósito via Pix realizado com sucesso.");
    }

    private void registrarDeposito() {
        //futuro registro em interface
        System.out.println("Depósito registrado no sistema.");
    }

}

