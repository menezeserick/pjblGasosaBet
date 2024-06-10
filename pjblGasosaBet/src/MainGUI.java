import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class MainGUI {
    private JFrame frame;
    private JTextField valorField;
    private JTextField corField;
    private JTextField depositoField;
    private JTextField saqueField;
    private JLabel saldoLabel;
    private Usuario usuarioAtual;

    public MainGUI() {
        usuarioAtual = Usuario.criarUsuario("Nome Exemplo", 123456789, new Date(), "email@exemplo.com", "senha123", "Endereço Exemplo", 1000.0);

        frame = new JFrame("Sistema de Apostas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));

        JButton apostaEsportivaButton = new JButton("Aposta Esportiva");
        JButton apostaRoletaButton = new JButton("Aposta em Roleta");
        JButton visualizarHistoricoButton = new JButton("Visualizar histórico de apostas");
        JButton depositarButton = new JButton("Depositar");
        JButton sacarButton = new JButton("Sacar");
        JButton sairButton = new JButton("Sair");

        apostaEsportivaButton.addActionListener(e -> mostrarCamposApostaEsportiva());
        apostaRoletaButton.addActionListener(e -> mostrarCamposApostaRoleta());
        visualizarHistoricoButton.addActionListener(e -> exibirHistorico());
        depositarButton.addActionListener(e -> realizarDeposito());
        sacarButton.addActionListener(e -> realizarSaque());
        sairButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(apostaEsportivaButton);
        buttonPanel.add(apostaRoletaButton);
        buttonPanel.add(visualizarHistoricoButton);
        buttonPanel.add(depositarButton);
        buttonPanel.add(sacarButton);
        buttonPanel.add(sairButton);

        frame.add(buttonPanel, BorderLayout.WEST);

        JPanel apostaPanel = new JPanel();
        apostaPanel.setLayout(new GridLayout(6, 2));

        JLabel saldoTituloLabel = new JLabel("Saldo Atual:");
        saldoLabel = new JLabel("R$ " + usuarioAtual.getSaldoAtual());
        JLabel valorLabel = new JLabel("Valor da Aposta:");
        valorField = new JTextField();
        JLabel corLabel = new JLabel("Cor (Preto, Vermelho, Branco):");
        corField = new JTextField();
        JLabel depositoLabel = new JLabel("Valor do Depósito:");
        depositoField = new JTextField();
        JLabel saqueLabel = new JLabel("Valor do Saque:");
        saqueField = new JTextField();

        JButton apostarButton = new JButton("Apostar");
        apostarButton.addActionListener(e -> realizarApostaRoleta());

        apostaPanel.add(saldoTituloLabel);
        apostaPanel.add(saldoLabel);
        apostaPanel.add(valorLabel);
        apostaPanel.add(valorField);
        apostaPanel.add(corLabel);
        apostaPanel.add(corField);
        apostaPanel.add(new JLabel());
        apostaPanel.add(apostarButton);
        apostaPanel.add(depositoLabel);
        apostaPanel.add(depositoField);
        apostaPanel.add(saqueLabel);
        apostaPanel.add(saqueField);

        frame.add(apostaPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void mostrarCamposApostaEsportiva() {
        JOptionPane.showMessageDialog(frame, "Funcionalidade de Aposta Esportiva ainda precisa implementar a interface");
    }

    private void mostrarCamposApostaRoleta() {
        valorField.setVisible(true);
        corField.setVisible(true);
    }

    private void realizarApostaRoleta() {
        try {
            double valor = Double.parseDouble(valorField.getText());
            String cor = corField.getText();

            if (usuarioAtual.getSaldoAtual() < valor) {
                JOptionPane.showMessageDialog(frame, "Saldo insuficiente para realizar a aposta.");
                return;
            }

            ApostaRoleta apostaRoleta = new ApostaRoleta(valor, cor);
            String resultado = apostaRoleta.resultadoFinal();
            usuarioAtual.setSaldoAtual(usuarioAtual.getSaldoAtual() + apostaRoleta.getGanhoTotal());
            atualizarSaldo(); // Atualiza o saldo exibido
            JOptionPane.showMessageDialog(frame, resultado + "\nSeu saldo atual: R$ " + usuarioAtual.getSaldoAtual());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira um valor válido.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    private void realizarDeposito() {
        try {
            double valor = Double.parseDouble(depositoField.getText());
            Deposito deposito = new Deposito(valor);
            usuarioAtual.depositar(deposito);
            JOptionPane.showMessageDialog(frame, "Depósito realizado com sucesso!");
            depositoField.setText("");
            atualizarSaldo(); // Atualiza o saldo exibido
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira um valor numérico válido.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    private void realizarSaque() {
        try {
            double valor = Double.parseDouble(saqueField.getText());
            if (valor > usuarioAtual.getSaldoAtual()) {
                JOptionPane.showMessageDialog(frame, "Saldo insuficiente para o saque.");
                return;
            }
            Saque saque = new Saque(valor);
            usuarioAtual.sacar(saque);
            JOptionPane.showMessageDialog(frame, "Saque realizado com sucesso!");
            saqueField.setText("");
            atualizarSaldo(); // Atualiza o saldo exibido
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira um valor numérico válido.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    private void atualizarSaldo() {
        saldoLabel.setText("R$ " + usuarioAtual.getSaldoAtual());
    }

    private void exibirHistorico() {
        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        for (Aposta aposta : Aposta.getHistoricoApostas()) {
            historyArea.append("ID: " + aposta.getIdAposta() + "\n");
            historyArea.append("Valor: R$ " + aposta.getValorAposta() + "\n");
            historyArea.append("Data: " + aposta.getDataAposta() + "\n");
            historyArea.append("Ganho Total: R$ " + aposta.getGanhoTotal() + "\n");
            historyArea.append("-----------------------------------\n");
        }
        JScrollPane scrollPane = new JScrollPane(historyArea);
        JOptionPane.showMessageDialog(frame, scrollPane, "Histórico de Apostas", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}