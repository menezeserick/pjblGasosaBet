public abstract class Deposito {
    protected double valor;
    protected Usuario usuario;

    public Deposito(double valor, Usuario usuario) {
        this.valor = valor;
        this.usuario = usuario;
    }

    public abstract void processarPagamento();
    public abstract void realizarDeposito();
}
