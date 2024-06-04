public class Credito extends Deposito {
    private String numeroCartao;
    private String nomeTitular;
    private String validade;
    private int cvv;

    public Credito(double valor, Usuario usuario, String numeroCartao, String nomeTitular, String validade, int cvv) {
        super(valor, usuario);
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.cvv = cvv;
    }

    @Override
    public void processarPagamento() {
        // Implementação do método para Cartão de Crédito
        double saldoAtual = usuario.getSaldoAtual();
        saldoAtual += valor;
        usuario.setSaldoAtual(saldoAtual);
    }

    @Override
    public void realizarDeposito(){
        processarPagamento();
    }
}