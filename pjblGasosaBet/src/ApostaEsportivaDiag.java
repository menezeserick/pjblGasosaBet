import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ApostaEsportivaDiag extends JDialog {
    private JTextField valorApostaField;
    private JComboBox<String> timeApostaCombo;
    private Usuario usuarioAtual;
    private Runnable atualizarSaldoCallback;

    public ApostaEsportivaDiag(JFrame parent, Usuario usuario, Runnable atualizarSaldoCallback) {
        super(parent, "Aposta Esportiva", true);
        this.usuarioAtual = usuario;
        this.atualizarSaldoCallback = atualizarSaldoCallback;
        setLayout(new GridLayout(3, 2));
        setSize(300, 150);

        JLabel valorApostaLabel = new JLabel("Valor da Aposta:");
        valorApostaField = new JTextField();

        JLabel timeApostaLabel = new JLabel("Time da Aposta:");
        String[] times = {"Real Madrid", "Barcelona"};
        timeApostaCombo = new JComboBox<>(times);

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener(new ConfirmarButtonListener());

        add(valorApostaLabel);
        add(valorApostaField);
        add(timeApostaLabel);
        add(timeApostaCombo);
        add(new JLabel());
        add(confirmarButton);

        setVisible(true);
    }

    private class ConfirmarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double valorAposta = Double.parseDouble(valorApostaField.getText());
                String timeAposta = (String) timeApostaCombo.getSelectedItem();

                if (usuarioAtual.getSaldoAtual() < valorAposta) {
                    JOptionPane.showMessageDialog(ApostaEsportivaDiag.this, "Saldo insuficiente para realizar a aposta.");
                    return;
                }

                usuarioAtual.setSaldoAtual(usuarioAtual.getSaldoAtual() - valorAposta);
                ApostaEsportiva minhaAposta = new ApostaEsportiva(valorAposta, timeAposta);
                String resultado = minhaAposta.resultadoFinal();

                double ganhoTotal = minhaAposta.getGanhoTotal();
                if (ganhoTotal > 0) {
                    usuarioAtual.setSaldoAtual(usuarioAtual.getSaldoAtual() + ganhoTotal + valorAposta);
                }

                JOptionPane.showMessageDialog(ApostaEsportivaDiag.this, resultado + "\nSeu saldo atual: R$ " + usuarioAtual.getSaldoAtual());
                atualizarSaldoCallback.run(); // Atualiza o saldo na GUI principal
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ApostaEsportivaDiag.this, "Valor da aposta inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(ApostaEsportivaDiag.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
