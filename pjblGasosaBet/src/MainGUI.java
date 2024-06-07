import javax.swing.*;
import java.awt.*;

public class MainGUI {
    private JFrame frame;
    private JTextField valorField;
    private JTextField corField;

    public MainGUI() {
        frame = new JFrame("Sistema de Apostas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        JButton apostaEsportivaButton = new JButton("Aposta Esportiva");
        JButton apostaRoletaButton = new JButton("Aposta em Roleta");
        JButton visualizarHistoricoButton = new JButton("Visualizar histórico de apostas");
        JButton sairButton = new JButton("Sair");

        apostaEsportivaButton.addActionListener(e -> mostrarCamposApostaEsportiva());
        apostaRoletaButton.addActionListener(e -> mostrarCamposApostaRoleta());
        visualizarHistoricoButton.addActionListener(e -> exibirHistorico());
        sairButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(apostaEsportivaButton);
        buttonPanel.add(apostaRoletaButton);
        buttonPanel.add(visualizarHistoricoButton);
        buttonPanel.add(sairButton);

        frame.add(buttonPanel, BorderLayout.WEST);

        JPanel apostaPanel = new JPanel();
        apostaPanel.setLayout(new GridLayout(3, 2));

        JLabel valorLabel = new JLabel("Valor da Aposta:");
        valorField = new JTextField();
        JLabel corLabel = new JLabel("Cor (Preto, Vermelho, Branco):");
        corField = new JTextField();

        JButton apostarButton = new JButton("Apostar");
        apostarButton.addActionListener(e -> realizarApostaRoleta());

        apostaPanel.add(valorLabel);
        apostaPanel.add(valorField);
        apostaPanel.add(corLabel);
        apostaPanel.add(corField);
        apostaPanel.add(new JLabel());
        apostaPanel.add(apostarButton);

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
            ApostaRoleta apostaRoleta = new ApostaRoleta();
            apostaRoleta.definirValorAposta(valor);
            String resultado = apostaRoleta.resultadoFinal(cor);
            JOptionPane.showMessageDialog(frame, resultado);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira um valor válido.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
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