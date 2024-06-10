public class Deposito {
    private double valorDeposito;

    public Deposito(double valorDeposito) {
        if (valorDeposito <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }
        this.valorDeposito = valorDeposito;
    }

    public double getValorDeposito() {
        return valorDeposito;
    }

    public void processarPagamento() {
        // Lógica para processar o pagamento
    }
}
