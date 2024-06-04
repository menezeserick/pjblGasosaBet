public class Pix extends Deposito {
    private String chavePix;

    public Pix(double valor, Usuario usuario, String chavePix) {
        super(valor, usuario);
        this.chavePix = chavePix;
    }

    @Override
    public void processarPagamento() {
        double saldoAtual = usuario.getSaldoAtual();
        saldoAtual += valor;
        usuario.setSaldoAtual(saldoAtual);
    }

    @Override
    public void realizarDeposito() {
        processarPagamento();
    }
}

