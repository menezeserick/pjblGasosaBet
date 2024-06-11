import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String nome;
    private int cpf;
    private String email;
    private String senha;
    private double saldoAtual;
    private static Map<String, Usuario> usuarios = new HashMap<>();

    private Usuario(String nome, int cpf, String email, String senha, double saldoInicial) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.saldoAtual = saldoInicial;
    }

    public void apostar() {

    }

    public void depositar(Deposito deposito){
        deposito.processarPagamento();
        saldoAtual += deposito.getValorDeposito();
        System.out.println("Depósito realizado com sucesso! Novo saldo: " + saldoAtual);
    }

    public void sacar(Saque saque) {
        double valorSaque = saque.getValorSaque();
        if (saldoAtual >= valorSaque) {
            saque.solicitarSaque();
            saldoAtual -= valorSaque;
            saque.enviarComprovante();
            System.out.println("Saque realizado com sucesso! Novo saldo: " + saldoAtual);
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public static Usuario criarUsuario(String nome, int cpf, String email, String senha, double saldoAtual) {
        if (usuarios.containsKey(email)) {
            return null; // Usuário já existe
        }

        Usuario novoUsuario = new Usuario(nome, cpf, email, senha, saldoAtual);
        usuarios.put(email, novoUsuario);
        return novoUsuario; // Usuário criado com sucesso
    }

    public static Usuario realizarLogin(String email, String senha) {
        Usuario usuario = usuarios.get(email);

        if (usuario != null && usuario.senha.equals(senha)) {
            return usuario; // Login bem sucedido
        }

        return null; // Falha no login
    }

    public String getNome() {
        return nome;
    }

    public int getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }
}
