import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainGUI {
    private JFrame frame;
    private JLabel saldoLabel;
    private Usuario usuarioAtual;

    public MainGUI() {
        frame = new JFrame("Sistema de Apostas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 400);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        JButton loginButton = new JButton("Login");
        JButton registroButton = new JButton("Registro");

        loginButton.addActionListener(e -> {
            LoginDialog loginDialog = new LoginDialog(frame);
            loginDialog.setVisible(true);
            usuarioAtual = loginDialog.getUsuario();
            if (usuarioAtual != null) {
                iniciarInterface();
            }
        });

        registroButton.addActionListener(e -> {
            RegistroDialog registroDialog = new RegistroDialog(frame);
            registroDialog.setVisible(true);
            usuarioAtual = registroDialog.getUsuario();
            if (usuarioAtual != null) {
                iniciarInterface();
            }
        });

        panel.add(loginButton);
        panel.add(registroButton);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void iniciarInterface() {
        frame.getContentPane().removeAll();

        JPanel buttonPanelLeft = new JPanel();
        buttonPanelLeft.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanelLeft.setBorder(BorderFactory.createTitledBorder("Opções de Aposta"));

        JButton apostaEsportivaButton = new JButton("Aposta Esportiva");
        JButton apostaRoletaButton = new JButton("Aposta em Roleta");
        JButton visualizarHistoricoButton = new JButton("Visualizar Histórico de Apostas");

        apostaEsportivaButton.addActionListener(e -> new ApostaEsportivaDiag(frame, usuarioAtual, this::atualizarSaldo));
        apostaRoletaButton.addActionListener(e -> new ApostaRoletaDialog(frame, usuarioAtual, this::atualizarSaldo));
        visualizarHistoricoButton.addActionListener(e -> exibirHistorico());

        buttonPanelLeft.add(apostaEsportivaButton);
        buttonPanelLeft.add(apostaRoletaButton);
        buttonPanelLeft.add(visualizarHistoricoButton);
        buttonPanelLeft.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(buttonPanelLeft, BorderLayout.WEST);

        JPanel buttonPanelRight = new JPanel();
        buttonPanelRight.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanelRight.setBorder(BorderFactory.createTitledBorder("Gerenciamento de Conta"));

        JButton depositarButton = new JButton("Depositar");
        JButton sacarButton = new JButton("Sacar");
        JButton sairButton = new JButton("Sair");

        depositarButton.addActionListener(e -> mostrarDialogoDeposito());
        sacarButton.addActionListener(e -> mostrarDialogoSaque());
        sairButton.addActionListener(e -> System.exit(0));

        buttonPanelRight.add(depositarButton);
        buttonPanelRight.add(sacarButton);
        buttonPanelRight.add(sairButton);
        buttonPanelRight.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(buttonPanelRight, BorderLayout.EAST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Informações do Usuário"));

        JLabel nomeLabel = new JLabel("Nome: " + usuarioAtual.getNome());
        JLabel cpfLabel = new JLabel("CPF: " + usuarioAtual.getCpf());
        JLabel emailLabel = new JLabel("E-mail: " + usuarioAtual.getEmail());
        saldoLabel = new JLabel("Saldo Atual: R$ " + usuarioAtual.getSaldoAtual());

        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cpfLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        saldoLabel.setForeground(Color.BLUE);

        infoPanel.add(nomeLabel);
        infoPanel.add(cpfLabel);
        infoPanel.add(emailLabel);
        infoPanel.add(saldoLabel);
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.add(infoPanel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    private void mostrarDialogoDeposito() {
        String valor = JOptionPane.showInputDialog(frame, "Digite o valor do depósito:", "Depositar", JOptionPane.PLAIN_MESSAGE);
        if (valor != null) {
            try {
                double valorDeposito = Double.parseDouble(valor);
                Deposito deposito = new Deposito(valorDeposito);
                usuarioAtual.depositar(deposito);
                JOptionPane.showMessageDialog(frame, "Depósito realizado com sucesso!");
                atualizarSaldo(); 
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira um valor numérico válido.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
        }
    }

    private void mostrarDialogoSaque() {
        String valor = JOptionPane.showInputDialog(frame, "Digite o valor do saque:", "Sacar", JOptionPane.PLAIN_MESSAGE);
        if (valor != null) {
            try {
                double valorSaque = Double.parseDouble(valor);
                if (valorSaque > usuarioAtual.getSaldoAtual()) {
                    JOptionPane.showMessageDialog(frame, "Saldo insuficiente para realizar o saque.");
                } else {
                    Saque saque = new Saque(valorSaque);
                    usuarioAtual.sacar(saque);
                    JOptionPane.showMessageDialog(frame, "Saque realizado com sucesso!");
                    atualizarSaldo();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira um valor numérico válido.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
        }
    }

    private void exibirHistorico() {
        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        for (Aposta aposta : Aposta.getHistoricoApostas()) {
            historyArea.append("ID: " + aposta.getIdAposta() + "\n");
            historyArea.append("Valor: R$ " + aposta.getValorAposta() + "\n");
            historyArea.append("Data: " + aposta.getDataAposta() + "\n");
            historyArea.append("Tipo: " + aposta.getTipoAposta() + "\n");
            historyArea.append("Ganho Total: R$ " + aposta.getGanhoTotal() + "\n");
            historyArea.append("-----------------------------------\n");
        }
        JScrollPane scrollPane = new JScrollPane(historyArea);
        JOptionPane.showMessageDialog(frame, scrollPane, "Histórico de Apostas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void atualizarSaldo() {
        saldoLabel.setText("Saldo Atual: R$ " + usuarioAtual.getSaldoAtual());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
