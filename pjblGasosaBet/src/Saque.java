public class Saque {
    private double valorSaque;

    public Saque(double valorSaque) {
        if (valorSaque <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        this.valorSaque = valorSaque;
    }

    public double getValorSaque() {
        return valorSaque;
    }

    public void solicitarSaque() {
        // Lógica para solicitar o saque
    }

    public void enviarComprovante() {
        // Lógica para enviar o comprovante
    }
}
