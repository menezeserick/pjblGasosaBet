import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Usuario implements Serializable {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private double saldoAtual;
    private static Map<String, Usuario> usuarios = new HashMap<>();

    private Usuario(String nome, String cpf, String email, String senha, double saldoInicial) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.saldoAtual = saldoInicial;
    }

    public void depositar(Deposito deposito){
        deposito.processarPagamento();
        saldoAtual += deposito.getValorDeposito();
        System.out.println("Depósito realizado com sucesso! Novo saldo: " + saldoAtual);
        salvarUsuarios();
    }

    public void sacar(Saque saque) {
        double valorSaque = saque.getValorSaque();
        if (saldoAtual >= valorSaque) {
            saque.solicitarSaque();
            saldoAtual -= valorSaque;
            saque.enviarComprovante();
            System.out.println("Saque realizado com sucesso! Novo saldo: " + saldoAtual);
            salvarUsuarios();
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public static Usuario criarUsuario(String nome, String cpf, String email, String senha, double saldoInicial) {
        carregarUsuarios();
        if (usuarios.containsKey(email)) {
            return null;
        }

        Usuario novoUsuario = new Usuario(nome, cpf, email, senha, saldoInicial);
        usuarios.put(email, novoUsuario);
        salvarUsuarios();
        return novoUsuario;
    }

    public static Usuario realizarLogin(String email, String senha) {
        carregarUsuarios();
        Usuario usuario = usuarios.get(email);

        if (usuario != null && usuario.senha.equals(senha)) {
            return usuario; // login sucesso
        }

        return null; // falha login
    }

    public static void salvarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.ser"))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public static void carregarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuarios.ser"))) {
            usuarios = (Map<String, Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
        salvarUsuarios();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}
